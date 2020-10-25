package com.globits.sample.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.sample.domain.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query("select A from Employee A where A.code like %?1% or A.name like %?1% or A.id like %?1% or A.email like %?1% or A.phone like %?1% or A.age like %?1%")
	public List<Employee> searchEmployee(String key);
	
}

