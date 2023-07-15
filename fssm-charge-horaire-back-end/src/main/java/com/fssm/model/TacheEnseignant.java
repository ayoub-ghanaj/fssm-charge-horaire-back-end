package com.fssm.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity @Data
@IdClass(TacheEnseignantId.class)
public class TacheEnseignant {

	@Id
	@ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
	@Id
	@ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
	@Id
	@Column(length=10)
	private String groupe;
	@Id
	@Column(length=20)
	private String session;
	@Id
	@Column(length=10)
	private String anneeUniversitaire;
	@NotNull
	private String type;
	@NotNull
	private int nbrH;
	@NotNull
	private Date dateInsertion;
	
}
