package com.wellsfargo.consumerfinancemanagement.controller;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wellsfargo.consumerfinancemanagement.model.User;
import com.wellsfargo.consumerfinancemanagement.service.EmailService;
import com.wellsfargo.consumerfinancemanagement.service.UserService;

@RestController // generate & manage REST API in json format
@RequestMapping(value="/forgotpassword")
public class PasswordController {
	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;
	
	@PostMapping(value="/{email}")
	public String processForgotPasswordForm(@PathVariable("email")  String userEmail, HttpServletRequest request) {
		// Lookup user in database by e-mail
				User optional = userService.findUserByEmail(userEmail);
				if (optional == null) {
					return("We didn't find an account for that e-mail address.");
				} else {
					
					//Generate random 36-character string token for reset password 
					User user = optional;
					user.setResetToken(UUID.randomUUID().toString());

					// Save token to database
					userService.saveUser(user);
					String appUrl = request.getScheme() + "://" + request.getServerName();
					System.out.println("step1 "+appUrl);
					// Email message
					SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
					passwordResetEmail.setFrom("priya00020130@gmail.com");
					passwordResetEmail.setTo(user.getEmailId());
					passwordResetEmail.setSubject("Password Reset Request");
					passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
							+ "/reset?token=" + user.getResetToken());
					
					System.out.println("step2 ");
					
					try {
					emailService.sendEmail(passwordResetEmail);}
					catch(Exception e) {
						e.printStackTrace();
					}

					// Add success message to view
				
					return ("email sent successfully");
				}		
	}
}
