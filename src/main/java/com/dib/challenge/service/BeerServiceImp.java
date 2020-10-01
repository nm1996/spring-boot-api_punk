package com.dib.challenge.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.dib.challenge.entity.Beer;
import com.dib.challenge.repository.BeerRepositoryInterface;

@Service
public class BeerServiceImp implements BeerService {

	private BeerRepositoryInterface repository;

	@Autowired
	public BeerServiceImp(BeerRepositoryInterface repository) {
		this.repository = repository;
	}

	@Override
	public List<Beer> getAllBeers() {
		return (List<Beer>) repository.findAll();
	}

	@Override
	public Object getOneBeerById(long id) {
		Beer beer = repository.findById(id).orElse(null);
		if (beer == null) {
			return "There is no records in database for that id.";
		}
		return beer;
	}

	@Override
	public String fillUpBeers() {
		if(this.countBeers()>=10) {
			return "Database is already filled.";
		}
		int countOfBeersToFill = 10 - this.countBeers();
		int inserted = this.insertUniqueBeer(countOfBeersToFill);
		return "Inserted " + inserted + " fields";
	}

	@Override
	public String deleteBeer(long id) {
		repository.deleteById(id);
		return "Record successfully deleted.";
	}

	private boolean checkIfBeerExsist(Beer beer) {
		if (this.getBeerByName(beer.getName())) {
			if (this.getBeerByDesc(beer.getDescription())) {
				return true;
			}
			return false;
		}
		return false;
	}

	private Beer storeBeer(Beer beer) {
		return repository.save(beer);
	}

	private int countBeers() {
		return (int) repository.count();
	}

	private boolean getBeerByName(String name) {
		Beer beer = repository.findByName(name);
		if (beer == null) {
			return false;
		}
		return true;
	}

	private boolean getBeerByDesc(String desc) {
		Beer beer = repository.findByDescription(desc);
		if (beer == null) {
			return false;
		}
		return true;
	}

	private Object getJsonBeer() {
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "Application");
		HttpEntity<String> entity = new HttpEntity<>(headers);

		Object beer = rest.exchange("https://api.punkapi.com/v2/beers/random", HttpMethod.GET, entity, Object.class)
				.getBody();
		return beer;
	}

	private Beer getBeerFromJson() {
		List list = (ArrayList) this.getJsonBeer();

		Beer beer = new Beer();

		for (int i = 0; i < list.size(); i++) {
			HashMap attributes = (LinkedHashMap) list.get(i);

			beer.setName(attributes.get("name").toString());
			beer.setDescription(attributes.get("description").toString());

			HashMap methods = (LinkedHashMap) attributes.get("method");
			List temperature = (ArrayList) methods.get("mash_temp");

			for (int j = 0; j < temperature.size(); j++) {
				HashMap temperatureList = (LinkedHashMap) temperature.get(i);
				HashMap temp = (LinkedHashMap) temperatureList.get("temp");
				beer.setMeshTemp((int) temp.get("value"));
			}

		}

		return beer;
	}

	private int insertUniqueBeer(int count) {
		int i = 0;
		while (i < count) {
			Beer beer = this.getBeerFromJson();
			if (this.checkIfBeerExsist(beer) == false) {
				this.storeBeer(beer);
				i++;
			}
		}
		return i;
	}

}
