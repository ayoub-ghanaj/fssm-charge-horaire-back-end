package com.fssm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fssm.model.Departement;

public interface ModuleRepository extends JpaRepository<com.fssm.model.Module, Integer>{
	
	

	@Query(value="select * from module;",nativeQuery = true)
	public Object[] getModuls();
	
}
