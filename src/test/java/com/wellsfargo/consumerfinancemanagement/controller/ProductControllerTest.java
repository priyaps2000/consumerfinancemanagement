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
import org.springframework.ui.Model;

import com.wellsfargo.consumerfinancemanagement.model.Admin;
import com.wellsfargo.consumerfinancemanagement.model.Product;
import com.wellsfargo.consumerfinancemanagement.model.User;
import com.wellsfargo.consumerfinancemanagement.repository.AdminRepository;
import com.wellsfargo.consumerfinancemanagement.service.AdminService;
import com.wellsfargo.consumerfinancemanagement.service.ProductService;
import com.wellsfargo.consumerfinancemanagement.service.UserService;

class ProductControllerTest {

	@Mock
	private ProductService pservice;

	@Spy
	private Model model;

	@Spy
	List<Product> product = new ArrayList<Product>();

	@Spy
	List<User> user = new ArrayList<User>();
	
	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		product=getproductList();
	}
	
	private List<Product> getproductList() {
		Product u1= new Product();
		
		u1.setProductId(101l);
		u1.setProductName("vishu@123");
		u1.setProductDetails("Testing");
		u1.setProductURL("test.com");
		u1.setCost(100l);
		u1.setPurchaseCount();
		
		List<Product> res= new ArrayList<Product>();
		res.add(u1);
		return res;
	}
	@Test
	void testAddProduct() {
		Product u= product.get(0);
		Product res= new Product();
//		User res = rUser.registerUser(u);
		res.setProductId(101l);
		res.setProductURL("test.com");
		assertNotNull(res);
		assertEquals(res.getProductId(),101l);
		assertEquals(res.getProductURL(), "test.com");
	}

	@Test
	void testDashBoard() {
		List<Product> res= product;

		when(pservice.Dashboard()).thenReturn(res);
		res=pservice.Dashboard();

		assertNotNull(res);
		verify(pservice,times(1)).Dashboard();
	}

	@Test
	void testGetProduct() {
		Product res= product.get(0);

		when(pservice.getProductCost(String.valueOf(product.get(0).getProductId()))).thenReturn(res);

		Product addc = pservice.getProductCost(String.valueOf(product.get(0).getProductId()));

		assertNotNull(addc.getProductName());
		assertEquals(res.getProductName(), addc.getProductName());
		verify(pservice,times(1)).getProductCost(String.valueOf(product.get(0).getProductId()));

	}

	@Test
	void testGetOne() {
		Product res= product.get(0);

		when(pservice.getProductCost(String.valueOf(product.get(0).getProductId()))).thenReturn(res);

		Product addc = pservice.getProductCost(String.valueOf(product.get(0).getProductId()));

		assertNotNull(addc.getProductName());
		assertEquals(res.getProductName(), addc.getProductName());
		verify(pservice,times(1)).getProductCost(String.valueOf(product.get(0).getProductId()));

	}

}
