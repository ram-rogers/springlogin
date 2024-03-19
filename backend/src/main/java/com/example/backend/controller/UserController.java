package com.example.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.LoginDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.dto.VerifyDTO;
import com.example.backend.entity.User;
import com.example.backend.repo.UserRepo;
import com.example.backend.req.EmailRequest;
import com.example.backend.response.LoginResponse;
import com.example.backend.response.PasswordChangeResponse;
import com.example.backend.service.UserService;

import jakarta.mail.MessagingException;

@RestController
@CrossOrigin
//@RequestMapping("api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;  
	
	@Autowired
	private UserRepo userRepo;
	

	
	@PostMapping(path="/save")
	public String addUser(@RequestBody UserDTO userDTO) {
		
		String id = userService.addUser(userDTO);
		return id;
	}
	
	
	@GetMapping("/users")
	public List<User> viewUser() {
		return userService.viewUsers();
	}
	
	@GetMapping("/user/{username}")
	User getUserById(@PathVariable String username) {
		return userRepo.findByUsername(username);
	}
	
	@PostMapping(path="/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
		LoginResponse loginResponse = userService.loginUser(loginDTO);
		return ResponseEntity.ok(loginResponse);
	}
	
	
	


    @PostMapping("/forgot-password")
    public ResponseEntity<?> sendEmail(@RequestBody String email) {
    	LoginResponse mailSent = userService.generateOtpAndSend(email);
    	return ResponseEntity.ok(mailSent);
    	
    
    }
    
    @PostMapping(path="/confirm-otp")
	public ResponseEntity<?> confirmOtp(@RequestBody VerifyDTO verifyDTO) {
		
		PasswordChangeResponse success = userService.confirmOtp(verifyDTO);
		return ResponseEntity.ok(success);
  
		   
	}
		
	 

}
