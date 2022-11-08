package com.wellsfargo.consumerfinancemanagement.controller;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wellsfargo.consumerfinancemanagement.model.User;
import com.wellsfargo.consumerfinancemanagement.service.EmailService;
import com.wellsfargo.consumerfinancemanagement.service.UserService;

@RestController // generate & manage REST API in json format
@RequestMapping(value="/api/password")
public class PasswordController {
	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;
	
	@PostMapping(value="/checkEmail/{email}")
	public String processForgotPasswordForm(@PathVariable("email")  String userEmail, HttpServletRequest request) {
		// Lookup user in database by e-mail
				User optional = userService.findUserByEmail(userEmail);
				if (optional == null) {
					return("We didn't find an account for that e-mail address.");
				} else {
					
					// Generate random 36-character string token for reset password 
					User user = optional;
					user.setResetToken(UUID.randomUUID().toString());

					// Save token to database
					userService.saveUser(user);

					// Email message
					SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
					passwordResetEmail.setFrom("consumerfinancemanagement@gmail.com");
					passwordResetEmail.setTo(user.getEmailId());
					passwordResetEmail.setSubject("Password Reset Request");
					passwordResetEmail.setText("To reset your password, use this token - " + user.getResetToken());
					
					emailService.sendEmail(passwordResetEmail);

					// Add success message to view
					return("A password reset token has been sent to " + userEmail + ". Enter the token and new password to proceed");
				}
				
	}
	
	@PostMapping(value = "/reset/{token}")
	public String resetPassword(ModelAndView modelAndView, @PathVariable("token") String token, @RequestBody String password) {
		String user = userService.findUserByResetToken(token);
		
		if(user==null) {
			return("Oops!  This is an invalid password reset token.");
		}
		else {
			userService.updateUserToken(token);
			userService.updatePwdByuName(user, password);
		}
		return "Your password has been reset";
	}
}
