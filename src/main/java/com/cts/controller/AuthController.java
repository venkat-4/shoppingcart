package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.User;
import com.cts.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody User user){
		
		/*if(authService.login(user).equals("User Looged in sucessfully")){
			return new ResponseEntity(user + authService.login(user)+ " with user Id: "+user.getUserId(), HttpStatus.OK);
		}else{
		    return new ResponseEntity(user + authService.login(user)+ " with user Id: "+user.getUserId(), HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
		return authService.login(user)+ " with user Id:"+user.getUserId();
	}
	
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public String createUser(@RequestBody User user){
	
		
		return authService.createUser(user);
	}
}
