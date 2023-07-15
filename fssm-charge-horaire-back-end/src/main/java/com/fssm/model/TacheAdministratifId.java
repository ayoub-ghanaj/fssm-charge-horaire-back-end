package com.fssm.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class TacheAdministratifId implements Serializable {
	
	@ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
	@ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;
    private String session;
	private String anneeUniversitaire;
	
    public TacheAdministratifId(Utilisateur utilisateur, Departement departement, String session, String anneeUniversitaire) {
        this.utilisateur = utilisateur;
        this.departement = departement;
        this.session = session;
        this.anneeUniversitaire = anneeUniversitaire;
    }
}