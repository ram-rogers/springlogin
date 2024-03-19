package com.example.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.dto.LoginDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.dto.VerifyDTO;
import com.example.backend.entity.User;
import com.example.backend.repo.UserRepo;
import com.example.backend.response.LoginResponse;
import com.example.backend.response.PasswordChangeResponse;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	

	@Override
	public List<User> viewUsers() {
		return userRepo.findAll();
	}     
	
	
	

	@Override
	public String addUser(UserDTO userDTO) {
		User user = new User(
				userDTO.getId(),
				userDTO.getUsername(),
				userDTO.getEmail(),
//				userDTO.getPassword(),
				
				this.passwordEncoder.encode(userDTO.getPassword()),
				null
		);   
		
		userRepo.save(user);
		return user.getUsername();
		
		
	}

	@Override
	public LoginResponse loginUser(LoginDTO loginDTO) {
		
		String msg = "";
		User user1 = userRepo.findByUsername(loginDTO.getUsername());
		
		if(user1 != null) {
			String password = loginDTO.getPassword();
			String encodedPassword = user1.getPassword();
			
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if(isPwdRight) {
				Optional<User> user = userRepo.findOneByUsernameAndPassword(loginDTO.getUsername(), encodedPassword);
				
				if(user.isPresent()) {
					return new LoginResponse("Login Success",true);
				}
				else {
					return new LoginResponse("Login failed",false);
				}  
			}
			else {
				return new LoginResponse("Password Not Match",false);
			}
		}
		else {
			return new LoginResponse("Email Not exists",false);
		}
	}
	
	
	@Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

	@Override
	public LoginResponse generateOtpAndSend(String email) {
		email = email.replace("{", "");
		email = email.replace("}", "");
		email = email.replace(":", "");
		email = email.replace("\"", "");
		email = email.substring(5, email.length());
		
		
    	User user1 = userRepo.findByEmail(email);

		
		if(user1 != null) {
			Random random = new Random();

	     
	        int randomNumber = 100000 + random.nextInt(900000); 

	        user1.setForgotPassword(randomNumber);

	        userRepo.save(user1);
	        sendEmail(email, "Forgot Password-Verification", "The OTP to verify: " + randomNumber);
	        return new LoginResponse("Mail Sent",true);
		}
		return new LoginResponse("Error Happened",true);

		
	}

	@Override
	public PasswordChangeResponse confirmOtp(VerifyDTO verifyDTO) {
		System.out.println("Hello");
		User user1 = userRepo.findByEmail(verifyDTO.getEmail());
		if(user1 == null) {
			return new PasswordChangeResponse("email Not found",false);
		}
		String otp = "" + user1.getForgotPassword();
		String confirmOtp = "" + verifyDTO.getOtp();
		System.out.println(otp);
		System.out.println(verifyDTO.getOtp());
		if(otp.equals(confirmOtp)) {
			user1.setPassword(passwordEncoder.encode(verifyDTO.getPassword()));
			user1.setForgotPassword(null);
			userRepo.save(user1);
			return new PasswordChangeResponse("change success",true);
		}
		else {  
			return new PasswordChangeResponse("OTP Doesn't match", false);
		}      
		      
		
	
	}

	


}