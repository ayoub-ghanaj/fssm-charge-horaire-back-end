package com.fssm.controllers;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fssm.model.Session;
import com.fssm.model.Utilisateur;
import com.fssm.repository.SessionRepository;
import com.fssm.repository.UtilisateurRepository;
import com.fssm.requests.AuthRequest;
import com.fssm.requests.LoginRequest;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class LoginConttroller {

	@Autowired UtilisateurRepository ur;
	@Autowired SessionRepository sr;
	
	@PostMapping("/login")
	public String logger(@RequestBody LoginRequest data ,HttpServletRequest httpRequest) {
		 String ipAddress = httpRequest.getRemoteAddr();
		 if(ur.existsById(data.getCin())) {
//			 Utilisateur user =  ur.findById(data.getCin()).get();
			Object[] oo = sr.login(data.getPassword(), data.getCin()); 
			 if(oo.length > 0){
				 int keyLength = 26; // length of key in bytes
		         SecureRandom random = new SecureRandom();
		         byte[] keyBytes = new byte[keyLength];
		         random.nextBytes(keyBytes);
		         System.out.println("LoginConttroller.logger()");
		         String key = Base64.getEncoder().encodeToString(keyBytes);
				 sr.insertSession(key, data.getCin());
				 return "{\"key\" : \""+key+"\" , \"error\": 0}";
			 }else {
				
				 return "{\"error\": 1 }";
			}
			 
		 }else {
			 
			 return "{\"error\": 2 }";
		 } 
	}
	@PostMapping("/auth")
	public String auth(@RequestBody AuthRequest data ,HttpServletRequest httpRequest) {
		 String ipAddress = httpRequest.getRemoteAddr();
		 if(sr.existsById(data.getKey())) {
//			 Utilisateur usr = sr.findById(data.getKey()).get().getUtilisateur();o
			 List<Object[]> oo = sr.auth(data.getKey());
			 System.out.println(oo.get(0)[1] + " " + oo.get(0)[2] );
			 return "{\"auth\" : true , \"error\": 0 ,\"admin\" : "+(((oo.get(0)[0]+"").equals("true") ) ?"true":"false")+" , \"fname\" : \""+oo.get(0)[1]+"\", \"lname\" : \""+oo.get(0)[2]+"\" }";
		 }else {
			 
		 } 
		 return "{\"auth\" : false , \"error\": 1}";
	}
}
