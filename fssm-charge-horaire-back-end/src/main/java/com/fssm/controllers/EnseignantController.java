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
import com.fssm.model.TacheEnseignant;
import com.fssm.model.TacheEnseignantId;
import com.fssm.model.Utilisateur;
import com.fssm.repository.DepsRepository;
import com.fssm.repository.FormationRepository;
import com.fssm.repository.ModuleRepository;
import com.fssm.repository.SessionRepository;
import com.fssm.repository.TachEnsRepository;
import com.fssm.repository.UtilisateurRepository;
import com.fssm.requests.AddTachAdminRequest;
import com.fssm.requests.AddTachEnsRequest;
import com.fssm.requests.AddUserRequest;
import com.fssm.requests.AuthRequest;
import com.fssm.requests.DeleteRequest;
import com.fssm.requests.LoginRequest;
import com.fssm.requests.PointRequest;
import com.fssm.requests.seekRequest;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class EnseignantController {

	@Autowired UtilisateurRepository ur;
	@Autowired TachEnsRepository ter;
	@Autowired SessionRepository sr;
	
	@PostMapping("/users")
	public String charts(@RequestBody seekRequest data ,HttpServletRequest httpRequest) throws JsonProcessingException {
		
		 if(sr.existsById(data.getKey())) {
			 String ipAddress = httpRequest.getRemoteAddr();
			 ObjectMapper objectMapper = new ObjectMapper();
			 String users = objectMapper.writeValueAsString(ur.seek(data.getName(),(data.getPage()*6)));
		 
			 return "{\"users\" : "+users+"}";
		 }else {
			 return "{'error':1}";
		 }
	}
	
	@PostMapping("/delete")
	public String wipe(@RequestBody DeleteRequest data ,HttpServletRequest httpRequest) throws JsonProcessingException {
		 if(sr.existsById(data.getKey())) {
			 for(String s : data.getCins()) {
				 ter.deleteAllforms(s);
				 ter.deleteAlldeps(s);
				 ter.deleteAllEnsTaches(s);
				 ter.deleteAllAdminTaches(s);
				 ter.deleteAllCherfs(s);
				 ur.deleteById(s);
			 }
			return "{\"error\" : 0}";
		 }else {
			 return "{'error':1}";
		 }
	}
	
	@PostMapping("/adduser")
	public String add(@RequestBody AddUserRequest data ,HttpServletRequest httpRequest) throws JsonProcessingException {
		 if(sr.existsById(data.getKey())) {
			 Utilisateur usr = new Utilisateur();
			 usr.setCin(data.getCin());
			 usr.setIsAdmin(data.getIsAdmin().equals("A"));
			 usr.setMotPasse(data.getMotPasse());
			 usr.setNomFamille(data.getNomFamille());
			 usr.setNomUtilisateur(data.getNomUtilisateur());
			 usr.setNumeroTelephone(data.getNumeroTelephone());
			 usr.setPrenom(data.getPrenom());
			 usr.setSexe(data.getSexe().charAt(0));
			 ur.save(usr);
			 return "{\"error\" : 0}";
		 }else {
			 return "{'error':1}";
		 }
	}
	
	
	@PostMapping("/editeuser")
	public String mod(@RequestBody AddUserRequest data ,HttpServletRequest httpRequest) throws JsonProcessingException {
		 if(sr.existsById(data.getKey())) {
			 ter.updateUser(data.getCin(),(data.getIsAdmin().equals("A"))?1:0, data.getMotPasse(), data.getNomFamille(), data.getNumeroTelephone(), data.getNomUtilisateur(), data.getPrenom(), data.getSexe());
//			 Utilisateur usr = ur.findById(data.getCin()).get();
//			 usr.setIsAdmin(data.isAdmin());
//			 usr.setMotPasse(data.getMotPasse());
//			 usr.setNomFamille(data.getNomFamille());
//			 usr.setNomUtilisateur(data.getNomUtilisateur());
//			 usr.setNumeroTelephone(data.getNumeroTelephone());
//			 usr.setPrenom(data.getPrenom());
//			 usr.setSexe(data.getSexe().charAt(0));
//			 ur.save(usr);
			 return "{\"error\" : 0}";
		 }else {
			 return "{'error':1}";
		 }
	}
	
	
	@Autowired DepsRepository dr;
	@Autowired FormationRepository fr;
	@Autowired ModuleRepository mr;
	
	@PostMapping("/addata")
	public String mod(@RequestBody AuthRequest data ,HttpServletRequest httpRequest) throws JsonProcessingException {		
		 if(sr.existsById(data.getKey())) {
			
			 ObjectMapper objectMapper = new ObjectMapper();
			 String deps = objectMapper.writeValueAsString(dr.getDeps());
			 String formation = objectMapper.writeValueAsString(fr.getForms());
			 String module = objectMapper.writeValueAsString(mr.getModuls());
 
			 return "{\"deps\" : "+deps+" , \"formation\" : "+formation+" , \"module\" : "+module+"}";
		 }else {
			 return "{'error':1}";
		 }
	}
	
	
	@PostMapping("/addTEns")
	public String addtens(@RequestBody AddTachEnsRequest data ,HttpServletRequest httpRequest) throws JsonProcessingException {		
		 if(sr.existsById(data.getKey())) {
//			 :year, :grp , :session, :type ,NULL, :nbrh, :modid, :cin
			try {
				
				ter.insertTachEns(data.getCin(), data.getYear(),data.getGrp(), data.getSession(), data.getType(), data.getNbrh(), data.getModid());
			}catch(Exception e){				
				return "{\"error\":0}";
			}
			return "{\"error\":0}";
		 }else {
			 return "{\"error\":1}";
		 }
	}
	@PostMapping("/addTAdmin")
	public String addtens(@RequestBody AddTachAdminRequest data ,HttpServletRequest httpRequest) throws JsonProcessingException {		
		if(sr.existsById(data.getKey())) {
//			 :year, :grp , :session, :type ,NULL, :nbrh, :modid, :cin
			try {

				ter.insertTachAdmin(data.getCin(), data.getYear(), data.getDep(), data.getSession(), data.getNomTache(), data.getNbrh());
			}catch(Exception e){				
				return "{\"error\":0}";
			}
			return "{\"error\":0}";
		}else {
			return "{\"error\":1}";
		}
	}
	
	@PostMapping("/gettab")
	public String addtens(@RequestBody PointRequest data ,HttpServletRequest httpRequest) throws JsonProcessingException {		
		if(sr.existsById(data.getKey())) {
			 ObjectMapper objectMapper = new ObjectMapper();
			 String tab = objectMapper.writeValueAsString(ter.gettable(data.getCin()));
			return "{\"table\": "+tab+"}";
		}else {
			return "{\"error\":1}";
		}
	}
	@PostMapping("/getmetab")
	public String addtens(@RequestBody AuthRequest data ,HttpServletRequest httpRequest) throws JsonProcessingException {		
		if(sr.existsById(data.getKey())) {
			 ObjectMapper objectMapper = new ObjectMapper();
			 String tab = objectMapper.writeValueAsString(ter.gettable(sr.getCin(data.getKey()).get(0)[0].toString()));
			return "{\"table\": "+tab+"}";
		}else {
			return "{\"error\":1}";
		}
	}
//	@PostMapping("/auth")
//	public String auth(@RequestBody AuthRequest data ,HttpServletRequest httpRequest) {
//		 String ipAddress = httpRequest.getRemoteAddr();
//		 if(sr.existsById(data.getKey()))		 {
//			 return "{\"auth\" : true , \"error\": 0 , \"ip\": \""+ipAddress+"\"}";
//		 }else {
//			 
//			 return "{\"auth\" : false , \"error\": 1}";
//		 } 
//	}
}
