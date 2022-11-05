package com.wellsfargo.consumerfinancemanagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.consumerfinancemanagement.model.Admin;
import com.wellsfargo.consumerfinancemanagement.repository.AdminRepository;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	private AdminRepository arepo;

	public Admin registerUser(Admin a) {
		// TODO Auto-generated method stub
		return arepo.save(a);
	}

	public void changeActStatus(String userName, String response) {
		// TODO Auto-generated method stub
		arepo.changeActStatus(userName, response);
	}
}
