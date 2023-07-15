package com.fssm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fssm.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{
	
	
	@Query("Select t.module.formation.departement.shortNom, sum(t.nbrH) from TacheEnseignant t group by (t.module.formation.departement, t.module.formation.departement.shortNom)")
	public Object[] cercleStat();
	
	@Query(value="Select m.short_nom, sum(t.nbrh) from tache_enseignant t INNER join module m on m.identifiant = t.module_id  group by (m.short_nom) order by sum(t.nbrh	) desc limit 0, 10", nativeQuery=true)
	public Object[] barStat();
	
	@Query(value  ="Select * from utilisateur u where u.cin like CONCAT('%',:nom,'%') OR   u.nom_famille like CONCAT('%',:nom,'%') OR u.prenom like CONCAT('%',:nom,'%') OR u.nom_utilisateur like CONCAT('%',:nom,'%') OR u.numero_telephone like CONCAT('%',:nom,'%')  LIMIT :page ,6" , nativeQuery = true)
	public  Object[] seek(@Param("nom") String nom ,@Param("page") int page );
	

}
