package com.kojitsystem.gestion.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kojitsystem.gestion.model.Client;
import com.kojitsystem.gestion.model.Commande;
import com.kojitsystem.gestion.model.User;
import com.kojitsystem.gestion.model.User.Role;
import com.kojitsystem.gestion.repository.ClientRepository;
import com.kojitsystem.gestion.repository.UserRepository;
import com.kojitsystem.gestion.service.*;
import com.kojitsystem.gestion.util.Util;

@RestController
@RequestMapping("api/")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public List<User> list() {
	        return userRepository.findAll();
	    }
		
		@PreAuthorize("hasRole('ROLE_ADMIN')")
	    @RequestMapping(value = "users", method = RequestMethod.POST)
	    public User create(@RequestBody User user) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.saveAndFlush(user);
	    }
	    
		
		@PreAuthorize("hasRole('ROLE_ADMIN') or #id == principal.id")
		@RequestMapping(value = "users/{id}", method = RequestMethod.GET)
	    public User get(@PathVariable Long id) {
	        return userRepository.findOne(id);
	    }
	    
	    @PreAuthorize("hasRole('ROLE_ADMIN') or #user.id == principal.id")
	    @RequestMapping(value = "users/{id}", method = RequestMethod.PUT)
	    public User update(@PathVariable Long id, @RequestBody User user) {
	    	User existingUser = userRepository.findOne(id);
	        BeanUtils.copyProperties(user, existingUser, "password");
	        if(user.getPassword()!=null)
	        	existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.saveAndFlush(existingUser); 
	        
	    }
	    
	    @PreAuthorize("hasRole('ROLE_ADMIN')")
	    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
	    public User delete(@PathVariable Long id) {
	    	User existingUser = userRepository.findOne(id);
	        userRepository.delete(existingUser);
	        return existingUser;
	    }
}
