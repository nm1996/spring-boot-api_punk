package com.dib.challenge.service;

import java.util.List;

import com.dib.challenge.entity.Beer;

public interface BeerService {
	List<Beer> getAllBeers();

	Object getOneBeerById(long id);

	String deleteBeer(long id);

	String fillUpBeers();
}