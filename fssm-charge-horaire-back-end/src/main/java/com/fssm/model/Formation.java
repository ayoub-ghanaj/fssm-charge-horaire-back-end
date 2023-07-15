package com.fssm.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity @Data
public class Formation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int identifiant;
	@ManyToOne
    @JoinColumn(name ="typeFormation", nullable = false)
    private TypeFormation typeFormation;
	@ManyToOne
    @JoinColumn(name ="departement", nullable = false)
    private Departement departement;
	@NotNull
	private String nom;
	@NotNull
	private String shortNom;
	private String type;
	
	@OneToMany(mappedBy = "formation", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<Module> modules = new HashSet<Module>();
	
}
