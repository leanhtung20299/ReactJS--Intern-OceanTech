package com.globits.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.sample.domain.Animal;
import com.globits.sample.repository.AnimalRepository;
import com.globits.sample.service.AnimalService;

@Transactional
@Service
public class AnimalServiceImpl extends GenericServiceImpl<Animal, Long> implements AnimalService{
	@Autowired
    AnimalRepository animalRepository;
	public List<Animal> xxx (){
		return animalRepository.findAll();	
	}
	public List<Animal> search(String key) {
		return animalRepository.searchAnimal(key);
	}
}
