package com.fssm.requests;

import lombok.Data;

@Data
public class AddTachAdminRequest {
	

	private String key;             
	private String nomTache ;
	private String dep;	
	private String cin;
	private String year;
	private String session;
	private String nbrh;
}
