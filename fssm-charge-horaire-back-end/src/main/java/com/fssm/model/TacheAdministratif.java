package com.fssm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity @Data
@IdClass(TacheAdministratifId.class)
public class TacheAdministratif {

	@Id
	@ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

	@Id
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;
	@Id
    private String session;
	@Id
	private String anneeUniversitaire;
	
	private String nomTache;
	private int nbrH;
	
}
