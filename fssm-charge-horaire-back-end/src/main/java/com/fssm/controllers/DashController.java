package com.fssm.controllers;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fssm.model.Session;
import com.fssm.model.Utilisateur;
import com.fssm.repository.SessionRepository;
import com.fssm.repository.UtilisateurRepository;
import com.fssm.requests.AuthRequest;
import com.fssm.requests.LoginRequest;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class DashController {

	@Autowired UtilisateurRepository ur;
	@Autowired SessionRepository sr;
	
	@PostMapping("/charts")
	public String charts(@RequestBody AuthRequest data ,HttpServletRequest httpRequest) throws JsonProcessingException {
		
		 if(sr.existsById(data.getKey())) {
			 String ipAddress = httpRequest.getRemoteAddr();
			 ObjectMapper objectMapper = new ObjectMapper();
			 String jsoncercle = objectMapper.writeValueAsString(ur.cercleStat());
			 String jsonbars = objectMapper.writeValueAsString(ur.barStat());
		 
			 return "{\"cercle\" : "+jsoncercle+" , \"bars\" :"+jsonbars+" }";
		 }else {
			 return "{'error':1}";
		 }
	}
//	@PostMapping("/auth")
//	public String auth(@RequestBody AuthRequest data ,HttpServletRequest httpRequest) {
//		 String ipAddress = httpRequest.getRemoteAddr();
//		 if(sr.existsById(data.getKey())) {
//			 return "{\"auth\" : true , \"error\": 0 , \"ip\": \""+ipAddress+"\"}";
//		 }else {
//			 
//			 return "{\"auth\" : false , \"error\": 1}";
//		 } 
//	}
}
