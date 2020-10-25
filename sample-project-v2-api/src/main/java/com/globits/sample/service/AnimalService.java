package com.globits.sample.service;

import java.util.List;

import com.globits.core.service.GenericService;
import com.globits.sample.domain.Animal;



public interface AnimalService extends GenericService<Animal, Long> {	 
	List<Animal> xxx();
	List<Animal> search(String key);
}