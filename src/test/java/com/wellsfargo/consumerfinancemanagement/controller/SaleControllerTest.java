package com.wellsfargo.consumerfinancemanagement.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
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

import com.wellsfargo.consumerfinancemanagement.model.Sale;
import com.wellsfargo.consumerfinancemanagement.model.User;
import com.wellsfargo.consumerfinancemanagement.repository.UserRepository;
import com.wellsfargo.consumerfinancemanagement.service.SaleService;
import com.wellsfargo.consumerfinancemanagement.service.UserService;
@SpringBootTest
class SaleControllerTest {
	@Mock
	private SaleService sservice;

	@Spy
	private Model model;

	@Spy
	List<Sale> sale = new ArrayList<Sale>();

	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		sale=getsaleList();
	}
	
	private List<Sale> getsaleList() {
		Sale u1= new Sale();
		
		u1.setTenurePeriod(3);
		u1.setProductName("chair");
		u1.setProductId("101");
		u1.setUserName("Vishalll");
		u1.setPurchaseDate(new Date());
		u1.setTotalAmount(1000);
		u1.setAmountpaid(100);
		
		List<Sale> res= new ArrayList<Sale>();
		res.add(u1);
		return res;
	}
	@Test
	void testRegisterSale() {
		Sale u= sale.get(0);
		Sale res= new Sale();
//		User res = rUser.registerUser(u);
		res.setUserName("Vishalll");
		res.setProductId("101");
		res.setProductName("chair");
		assertNotNull(res);
		assertEquals(res.getUserName(),"Vishalll");
		assertEquals(res.getProductId(), "101");
		assertEquals(res.getProductName(), "chair");
	}

	@Test
	void testRefresh() {
	}

}
