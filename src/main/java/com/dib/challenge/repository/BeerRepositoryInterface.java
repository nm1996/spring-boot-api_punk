package com.dib.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.dib.challenge.entity.Beer;

public interface BeerRepositoryInterface extends CrudRepository<Beer, Long> {
	Beer findByDescription(String description);

	Beer findByName(String name);
}
