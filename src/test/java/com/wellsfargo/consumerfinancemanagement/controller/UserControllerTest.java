package com.wellsfargo.consumerfinancemanagement.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
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

import com.wellsfargo.consumerfinancemanagement.model.User;
import com.wellsfargo.consumerfinancemanagement.repository.UserRepository;
import com.wellsfargo.consumerfinancemanagement.service.UserService;
//import com.wellsfargo.consumerfinancemanagement.controller.UserController.registerUser;
@SpringBootTest
class UserControllerTest {

	@Mock
	private UserService uservice;

	@Mock
	private UserRepository urepo;


	@Spy
	private Model model;

	@Spy
	List<User> user = new ArrayList<User>();

	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		user=getUserList();
	}
	
	private List<User> getUserList() {
		User u1= new User();
		
		u1.setName("Vishal");
		u1.setPhoneNo("9999999888");
		u1.setEmailId("vishu@gmail.com");
		u1.setUserName("Vishal111");
		u1.setPassword("vishu@123");
		u1.setAddress("Hyderabad");
		u1.setCardType("Titanium");
		u1.setBank("ICICI");
		u1.setIfscCode("45678");
		u1.setAccountNo("10112022");
		u1.setDocUpload("https://vishaldocs.doc");
		u1.setActvnStatus("Pending");
		
		List<User> res= new ArrayList<User>();
		res.add(u1);
		return res;
	}
	
	@Test
	void testRegisterUser() {
		User u= user.get(0);
		User res= new User();
//		User res = rUser.registerUser(u);
		res.setName("Vishal");
		res.setPhoneNo("9999999888");
		res.setEmailId("vishu@gmail.com");
		assertNotNull(res);
		assertEquals(res.getName(),"Vishal");
		assertEquals(res.getPhoneNo(), "9999999888");
		assertEquals(res.getEmailId(), "vishu@gmail.com");
		
		verify(uservice,times(0)).registerUser(u);
	}

	@Test
	void testFindUser() {
		User res=new User();

		res.setUserName(user.get(0).getUserName());


		when(uservice.findUserByuserName(user.get(0).getUserName())).thenReturn(res);

		User addc = uservice.findUserByuserName(user.get(0).getUserName());

		assertNotNull(addc.getUserName());
		assertEquals(res.getUserName(), addc.getUserName());

		verify(uservice,times(1)).findUserByuserName(user.get(0).getUserName());
//		fail("Not yet implemented");
	}

	@Test
	void testCheckUser() {
		User res=new User();

		res.setUserName(user.get(0).getUserName());


		when(uservice.findPasswordByuserName(user.get(0).getUserName())).thenReturn(res);

		User addc = uservice.findPasswordByuserName(user.get(0).getUserName());

		assertNotNull(addc.getUserName());
		assertEquals(res.getUserName(), addc.getUserName());

		verify(uservice,times(1)).findPasswordByuserName(user.get(0).getUserName());
	}

//	@Test
//	void testUpdatePassword() {
//		fail("Not yet implemented");
//	}

}