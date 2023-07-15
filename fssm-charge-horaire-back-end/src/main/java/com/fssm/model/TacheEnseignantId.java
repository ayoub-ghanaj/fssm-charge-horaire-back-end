package com.fssm.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

public class TacheEnseignantId implements Serializable {

	@ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
	@ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
	@Column(length=10)
	private String groupe;
	@Column(length=20)
	private String session;
	@Column(length=10)
	private String anneeUniversitaire;
	@Column(length=10)
	private String type;
	
	public TacheEnseignantId(Utilisateur utilisateur, Module module, String groupe, String session, String anneeUniversitaire, String type) {
        this.utilisateur = utilisateur;
        this.module = module;
        this.groupe = groupe;
        this.session = session;
        this.anneeUniversitaire = anneeUniversitaire;
        this.type = type;
    }
}
