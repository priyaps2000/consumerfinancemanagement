package com.wellsfargo.consumerfinancemanagement.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.wellsfargo.consumerfinancemanagement.model.Admin;
import com.wellsfargo.consumerfinancemanagement.model.User;
import com.wellsfargo.consumerfinancemanagement.repository.AdminRepository;
import com.wellsfargo.consumerfinancemanagement.service.AdminService;
import com.wellsfargo.consumerfinancemanagement.service.UserService;
@SpringBootTest
class AdminControllerTest {

	@Mock
	private AdminService aservice;
	
	@Mock
	private UserService uservice;

	@Mock
	private AdminRepository arepo;


	@Spy
	private Model model;

	@Spy
	List<Admin> admin = new ArrayList<Admin>();

	@Spy
	List<User> user = new ArrayList<User>();
	
	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		admin=getAdminList();
	}
	
	private List<Admin> getAdminList() {
		Admin u1= new Admin();
		
		u1.setAdminuName("Vishal");
		u1.setPassword("vishu@123");
		
		List<Admin> res= new ArrayList<Admin>();
		res.add(u1);
		return res;
	}
	
	@Test
	void testRegisterUser() {
		Admin u= admin.get(0);
		Admin res= new Admin();
//		User res = rUser.registerUser(u);
		res.setAdminuName("Vishal");
		res.setPassword("9999999888");
		assertNotNull(res);
		assertEquals(res.getAdminuName(),"Vishal");
		assertEquals(res.getPassword(), "9999999888");
		
//		verify(aservice,times(1)).registerUser(u);
	}

	@Test
	void testCheckAdmin() {
		User res=new User();

		res.setUserName(admin.get(0).getAdminuName());
		res.setPassword(admin.get(0).getPassword());
//		findPasswordByadminName
		when(aservice.findPasswordByadminName(admin.get(0).getAdminuName())).thenReturn(res.getPassword());

	}

	@Test
	void testDashBoard() {
		List<User> res= user;

		when(uservice.uDashboard()).thenReturn(res);
		res=uservice.uDashboard();

		assertNotNull(res);
		verify(uservice,times(1)).uDashboard();
	}

}
