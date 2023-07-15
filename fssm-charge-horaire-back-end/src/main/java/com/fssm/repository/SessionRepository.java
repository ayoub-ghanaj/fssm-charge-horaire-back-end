package com.fssm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fssm.model.Session;

import jakarta.transaction.Transactional;

public interface SessionRepository extends JpaRepository<Session, String>{
	
	
 	
 	@Transactional
 	@Modifying
 	@Query(value="insert into session values (:keycode, 1, NULL, NULL, :cin);" , nativeQuery = true)
 	public void insertSession(@Param("keycode") String  keycode, @Param("cin") String  cin  ) ;
 	
 	
 	@Query(value="select * from utilisateur where cin = :cin and mot_passe = :pass ;" , nativeQuery = true)
 	public Object[] login(@Param("pass") String  pass, @Param("cin") String  cin  ) ;

 	@Query(value="select is_admin ,prenom ,nom_famille from utilisateur where cin in (select utilisateur_id from session where key_code = :key ) ;" , nativeQuery = true)
 	public List<Object[]> auth( @Param("key") String  key  ) ;
 	
 	@Query(value="select utilisateur_id from session where key_code = :key ;" , nativeQuery = true)
 	public List<Object[]> getCin( @Param("key") String  key  ) ;
}
