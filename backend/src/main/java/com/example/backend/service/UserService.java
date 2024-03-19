package com.example.backend.service;



import com.example.backend.response.LoginResponse;
import com.example.backend.response.PasswordChangeResponse;

import jakarta.mail.MessagingException;

import java.util.List;

import com.example.backend.dto.LoginDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.dto.VerifyDTO;
import com.example.backend.entity.User;


public interface UserService {

	String addUser(UserDTO userDTO);

	LoginResponse loginUser(LoginDTO loginDTO);

	void sendEmail(String string, String string2, String string3);
	
	LoginResponse generateOtpAndSend(String email);

	PasswordChangeResponse confirmOtp(VerifyDTO verifyDTO);   
	
	List<User> viewUsers();
  
}
