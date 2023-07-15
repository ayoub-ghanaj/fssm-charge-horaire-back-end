package com.fssm.requests;


import lombok.Data;

@Data
public class DeleteRequest {
	
	private String key;
	private String[] cins;
	

}
