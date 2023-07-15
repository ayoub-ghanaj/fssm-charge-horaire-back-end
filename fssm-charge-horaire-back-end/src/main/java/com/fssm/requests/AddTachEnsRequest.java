package com.fssm.requests;

import lombok.Data;

@Data
public class AddTachEnsRequest {
	
	
//	:year, :grp , :session, :type ,NULL, :nbrh, :modid, :cin
	private String key;
	private String cin;
	private String year;
	private String grp;
	private String session;
	private String type;
	private String nbrh;
	private String modid;
}
