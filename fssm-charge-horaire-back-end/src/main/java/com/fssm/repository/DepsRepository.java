package com.fssm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fssm.model.Departement;

public interface DepsRepository extends JpaRepository<Departement, Integer>{
	
	
	@Query(value="select * from departement;",nativeQuery = true)
	public Object[] getDeps();

}
