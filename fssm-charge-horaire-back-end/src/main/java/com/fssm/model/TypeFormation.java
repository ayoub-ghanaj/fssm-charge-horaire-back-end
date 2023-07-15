package com.fssm.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity @Data
public class TypeFormation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int identifiant;
	@NotNull
	private String nom;
	
	@OneToMany(mappedBy = "typeFormation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Formation> formation = new HashSet<Formation>();
}
