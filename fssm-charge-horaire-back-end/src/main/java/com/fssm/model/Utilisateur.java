package com.fssm.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Builder.Default;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter

@Getter

@NoArgsConstructor

@Entity 
public class Utilisateur {

	@Id
	@NotNull
	private String cin;
	@NotNull
	private String nomFamille;
	@NotNull
	private String prenom;
	private String nomUtilisateur;
	@NotNull
	private char sexe;
	private String numeroTelephone;
	@NotNull
	private String motPasse;
	private Date dateInscription;
	private Date dateModification;
	@NotNull
	private Boolean isAdmin;
	private boolean stayConnected;
	
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	private Set<Session> Sessions = new HashSet<Session>();
	
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private Set<TacheAdministratif> tacheAdministratifs = new HashSet<TacheAdministratif>();
	
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TacheEnseignant> tacheEnseignants = new HashSet<TacheEnseignant>();
	
}
