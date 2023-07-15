package com.fssm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fssm.model.TacheEnseignant;
import com.fssm.model.TacheEnseignantId;
import com.fssm.model.Utilisateur;

import jakarta.transaction.Transactional;

public interface TachEnsRepository extends JpaRepository<TacheEnseignant, TacheEnseignantId>{
	
	 	@Transactional
	    @Modifying
	    @Query(value="delete from  tache_administratif where  utilisateur_id = :cin" , nativeQuery = true)
		public void deleteAllAdminTaches(@Param("cin") String  cin) ;
	 	
	 	@Transactional
	    @Modifying
		@Query(value="delete from  tache_enseignant where  utilisateur_id = :cin" , nativeQuery = true)
		public void deleteAllEnsTaches(@Param("cin") String  cin) ;

	 	@Transactional
	 	@Modifying
	 	@Query(value="delete from  departement where  chef_departement = :cin" , nativeQuery = true)
	 	public void deleteAllCherfs(@Param("cin") String  cin) ;
		
	 	@Transactional
	 	@Modifying
	 	@Query(value="DELETE FROM formation WHERE departement IN (SELECT identifiant FROM departement WHERE chef_departement = :cin);" , nativeQuery = true)
	 	public void deleteAlldeps(@Param("cin") String  cin) ;
	 	
	 	@Transactional
	 	@Modifying
	 	@Query(value="DELETE FROM module WHERE formation IN (SELECT f.identifiant FROM formation  f inner join  departement  WHERE chef_departement = :cin);" , nativeQuery = true)
	 	public void deleteAllforms(@Param("cin") String  cin) ;
	 	
	 	
	 	@Transactional
	 	@Modifying
	 	@Query(value="insert into tache_enseignant values (:year, :grp , :session, :type ,sysdate(), :nbrh, :modid, :cin);" , nativeQuery = true)
	 	public void insertTachEns(@Param("cin") String  cin, @Param("year") String  year , @Param("grp") String  grp, @Param("session") String  session ,@Param("type") String  type ,@Param("nbrh") String  nbrh, @Param("modid") String  mod_id) ;
	 	
	 	@Transactional
	 	@Modifying
	 	@Query(value="insert into tache_administratif values (:year, :session, :nbrh, :nomt, :cin, :dep);" , nativeQuery = true)
	 	public void insertTachAdmin(@Param("cin") String  cin, @Param("year") String  year , @Param("dep") String  dep, @Param("session") String  session ,@Param("nomt") String  nomt,@Param("nbrh") String  nbrh) ;

		@Transactional
	 	@Modifying
	 	@Query(value="update utilisateur set is_admin = :admin , mot_passe = :pass , nom_famille = :nomf , nom_utilisateur = :nomu , numero_telephone = :tele , prenom = :prenom , sexe = :sexe where cin = :cin ;" , nativeQuery = true)
	 	public void updateUser(@Param("cin") String  cin, @Param("admin") int admin , @Param("pass") String  pass, @Param("nomf") String  nomf ,@Param("tele") String  tele ,@Param("nomu") String  nomu ,@Param("prenom") String  prenom,@Param("sexe") String  sexe) ;

	 	@Query(value="select tt.*, t.type, t.nbrh from tache_enseignant t join module m on t.module_id = m.identifiant join formation f on m.formation = f.identifiant join departement d on f.departement = d.identifiant, (select annee_universitaire, session, groupe, m.intitule as mnom, f.nom as fnom, d.nom as dnom, sum(nbrh) as total from tache_enseignant t join module m on t.module_id = m.identifiant join formation f on m.formation = f.identifiant join departement d on f.departement = d.identifiant group by annee_universitaire, session, groupe, m.identifiant, m.intitule, f.identifiant, f.nom, d.identifiant, d.nom) tt where t.annee_universitaire = tt.annee_universitaire and t.session = tt.session and t.groupe = tt.groupe and m.intitule = tt.mnom and d.nom = tt.dnom and f.nom = tt.fnom and t.utilisateur_id = :cin " , nativeQuery = true)
	 	public Object[] gettable(@Param("cin") String  cin) ;

}
