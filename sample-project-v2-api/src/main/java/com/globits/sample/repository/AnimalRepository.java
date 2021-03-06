package com.globits.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.core.domain.Country;
import com.globits.sample.domain.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>  {
	@Query("select A from Animal A where A.name like %?1% or A.id like %?1% or A.code like %?1% or A.description like %?1%")
	public List<Animal> searchAnimal(String key);
}
