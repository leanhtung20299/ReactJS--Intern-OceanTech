package com.globits.sample.rest;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.globits.sample.domain.Animal;
import com.globits.sample.dto.AnimalById;
import com.globits.sample.dto.AnimalDto;
import com.globits.sample.dto.ExcelExporter;
import com.globits.core.Constants;
import com.globits.core.domain.Country;
import com.globits.core.service.CountryService;
import com.globits.sample.domain.Animal;
import com.globits.sample.service.AnimalService;


@RestController
@RequestMapping("/api/animal")
public class RestAnimalController implements Serializable {
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private AnimalService animalService;

	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public  AnimalDto getList() {
		AnimalDto animal = new AnimalDto();
		List<Animal> list = animalService.xxx();
		animal.setList(list);
//		animal.setDate(new Date());
		return animal;
	}

	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public  AnimalDto UpdateAnimal(@RequestBody Animal animal,@PathVariable("id") Long id) {
		AnimalDto animalresponse = new AnimalDto();
		animalService.save(animal);
		List<Animal> list = new ArrayList<>();
		list.add(animal);
		animalresponse.setList(list);
//		animalresponse.setDate(new Date());
		return animalresponse;
	}
	
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public  AnimalDto SaveAnimal(@RequestBody Animal animal) {
		AnimalDto animalresponse = new AnimalDto();
		animalService.save(animal);
		List<Animal> list = animalService.xxx();
		animalresponse.setList(list);
//		animalresponse.setDate(new Date());
		return animalresponse;
	}
	
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
	public AnimalDto deleteAnimal (@PathVariable("id") Long id) {
		AnimalDto animalresponse = new AnimalDto();
		animalService.delete(id);
		List<Animal> list = animalService.xxx();
		animalresponse.setList(list);
		return animalresponse;
	}
	
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public AnimalById getAnimalById (@PathVariable("id") Long id) {
		AnimalById animalresponse = new AnimalById();
		Animal animal = animalService.findById(id);
		animalresponse.setId(animal.getId());
		animalresponse.setName(animal.getName());
		animalresponse.setCode(animal.getCode());
		animalresponse.setDescription(animal.getDescription());
		return animalresponse;
	}
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/search/{key}" , method = RequestMethod.GET)
	public AnimalDto SearchAnimal (@PathVariable("key") String key) {
		AnimalDto animalresponse = new AnimalDto();
	    List<Animal> list = animalService.search(key);
	    animalresponse.setList(list);
		return animalresponse;
	}
	
//	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
//	@RequestMapping(value = "/Excel" , method = RequestMethod.GET)
//	public void ExportToExcel  (HttpServletResponse response) throws IOException {
//		response.setContentType("application/octet-stream");
//		String headerKey = "Content-Disposition";
//		String headerValue = "attachement; filename=excel.xlsx";
//		
//		response.setHeader(headerKey,headerValue);
//		List<Animal> list = animalService.xxx();
////		ExcelExporter exporter = new ExcelExporter(list);
//		exporter.Export(response);
//	}
//	
}
//	@Secured({Constants.ROLE_ADMIN,Constants.ROLE_USER})
//	@RequestMapping(value = "/{id}" , method = RequestMethod.POST)
//	public AnimalDto searchAnimal (@PathVariable("value") String value) {
//		AnimalDto animalresponse = new AnimalDto();
//		List<Animal> list = animalService.f
//		animalresp	onse.setList(list);
//		animalresponse.setDate(new Date());
//		return animalresponse;
//	}
	
//	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
//	@RequestMapping(value = "/{countryId}", method = RequestMethod.GET)
//	public Country getCountry(@PathVariable("countryId") String countryId) {
//		Country country = countryService.findById(UUID.fromString(countryId));
//		// building = new Building(building);
//		return country;
//	}
//
//	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
//	@RequestMapping(method = RequestMethod.POST)
//	public Country saveCountry(@RequestBody Country country) {
//		return countryService.save(country);
//	}
//
//	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
//	@RequestMapping(value = "/{countryId}", method = RequestMethod.PUT)
//	public Country updateCountry(@RequestBody Country Country, @PathVariable("countryId") Long CountryId) {
//		return countryService.save(Country);
//	}
//
//	@Secured({Constants.ROLE_ADMIN,"ROLE_STUDENT_MANAGERMENT"})
//	@RequestMapping(value = "/{countryId}", method = RequestMethod.DELETE)
//	public Country removeCountry(@PathVariable("countryId") String countryId) {
//		Country Country = countryService.delete(UUID.fromString(countryId));
//		return Country;
//	}
//	
//	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
//	@RequestMapping(value = "/checkCode/{code}",method = RequestMethod.GET)
//	public CountryDto checkDuplicateCode(@PathVariable("code") String code) {
//		return countryService.checkDuplicateCode(code);
//	}

