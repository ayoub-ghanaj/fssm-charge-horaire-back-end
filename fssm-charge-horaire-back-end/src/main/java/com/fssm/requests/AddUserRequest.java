package com.fssm.requests;

import lombok.Data;

@Data
public class AddUserRequest {
	
	private String key;
	private String cin;
	private String isAdmin;
	private String motPasse;
	private String nomFamille;
	private String nomUtilisateur;
	private String numeroTelephone;
	private String prenom;
	private String sexe;

}
