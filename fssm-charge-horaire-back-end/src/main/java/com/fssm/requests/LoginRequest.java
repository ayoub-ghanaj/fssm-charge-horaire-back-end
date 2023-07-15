package com.fssm.requests;

import lombok.Data;

@Data
public class LoginRequest {
	
	private String cin;
	private String password;

}
