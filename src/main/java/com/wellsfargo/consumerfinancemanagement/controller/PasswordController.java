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
@RequestMapping(value="/api")
public class PasswordController {
	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;
	
	@PostMapping(value="checkEmail/{email}")
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
					return("A password reset link has been sent to " + userEmail);
				}
				
	}
	
	@GetMapping(value = "/reset/{token}")
	public String resetPassword(@PathVariable("token") String token) {
		User user = userService.findUserByResetToken(token);
		if(user==null) {
			
		}
		else {
			System.out.println("initial " + user.getResetToken());
			user.setResetToken(null);
			System.out.println("final " + user.getResetToken());
		}
		return "yes";
	}
	

	
	
//	@PostMapping(value = "/reset")
//	public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
//
//		// Find the user associated with the reset token
//		Optional<User> user = userService.findUserByResetToken(requestParams.get("token"));
//
//		// This should always be non-null but we check just in case
//		if (user.isPresent()) {
//			
//			User resetUser = user.get(); 
//            
//			// Set new password    
//            resetUser.setPassword(requestParams.get("password"));
//            
//			// Set the reset token to null so it cannot be used again
//			resetUser.setResetToken(null);
//
//			// Save user
//			userService.saveUser(resetUser);
//
//			// In order to set a model attribute on a redirect, we must use
//			// RedirectAttributes
//			redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");
//
//			modelAndView.setViewName("redirect:login");
//			return modelAndView;
//			
//		} else {
//			modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
//			modelAndView.setViewName("resetPassword");	
//		}
//		
//		return modelAndView;
//   }
	
}
