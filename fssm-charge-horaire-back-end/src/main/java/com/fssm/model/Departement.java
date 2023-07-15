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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity @Data
public class Departement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int identifiant;
	@NotNull
	private String nom;
	@NotNull
	private String shortNom;
	private String secrétariat;
	@OneToOne
    @JoinColumn(name ="chefDepartement", nullable = false)
    private Utilisateur chefDepartement;
	
	@OneToMany(mappedBy = "departement", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TacheAdministratif> tacheAdministratifs = new HashSet<TacheAdministratif>();
	
	@OneToMany(mappedBy = "departement", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<Formation> formation = new HashSet<Formation>();
	
}
