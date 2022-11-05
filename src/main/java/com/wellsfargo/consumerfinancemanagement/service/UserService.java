package com.wellsfargo.consumerfinancemanagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.consumerfinancemanagement.model.User;
import com.wellsfargo.consumerfinancemanagement.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository urepo;
	
	public User registerUser(User user) {
		return urepo.save(user); // invoke jpa repository save() method
	}
	
	public User findUserByuserName(String userName) {
		return urepo.findUserByuName(userName);
	}

	public void updatePwdByuName(String userName, String password) {
		// TODO Auto-generated method stub
		urepo.updatePwdByuName(userName, password);
		System.out.println(userName + " " +  password);
	}

//	User getById(int id)
	
}
