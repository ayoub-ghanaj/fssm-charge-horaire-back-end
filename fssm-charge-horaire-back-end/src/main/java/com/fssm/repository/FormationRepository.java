package com.fssm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fssm.model.Formation;
import com.fssm.model.Session;

public interface FormationRepository extends JpaRepository<Formation, Integer>{
	
	@Query(value="select * from formation;",nativeQuery = true)
	public Object[] getForms();
	

}
