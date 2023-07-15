package com.fssm.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity @Data
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int identifiant;	
	@ManyToOne
    @JoinColumn(name ="formation", nullable = false)
    private Formation formation;
	@NotNull
	private String intitule;
	@NotNull
	private String shortNom;
	private int vh;
	
	@OneToMany(mappedBy = "module", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TacheEnseignant> tacheEnseignants = new HashSet<TacheEnseignant>();
}
