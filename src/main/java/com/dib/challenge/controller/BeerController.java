package com.dib.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dib.challenge.entity.Beer;
import com.dib.challenge.service.BeerServiceImp;

@RestController
public class BeerController {

	private BeerServiceImp service;

	@Autowired
	public BeerController(BeerServiceImp service) {
		this.service = service;
	}

	@CrossOrigin
	@GetMapping("/beers")
	public List<Beer> getAllBeers() {
		return this.service.getAllBeers();
	}

	@CrossOrigin
	@GetMapping("/beer/{id}")
	public Object getBeerById(@PathVariable long id) {
		return this.service.getOneBeerById(id);
	}

	@CrossOrigin
	@PostMapping("/beer/fill")
	public String fillUpDatabase() {
		return this.service.fillUpBeers();
	}

	@CrossOrigin
	@DeleteMapping("/beer/{id}")
	public String deleteBeer(@PathVariable long id) {
		return this.service.deleteBeer(id);
	}

}
