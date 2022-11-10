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

import com.wellsfargo.consumerfinancemanagement.model.Card;
import com.wellsfargo.consumerfinancemanagement.model.Sale;
import com.wellsfargo.consumerfinancemanagement.model.User;
import com.wellsfargo.consumerfinancemanagement.repository.UserRepository;
import com.wellsfargo.consumerfinancemanagement.service.CardService;
import com.wellsfargo.consumerfinancemanagement.service.SaleService;
import com.wellsfargo.consumerfinancemanagement.service.UserService;

class CardControllerTest {
	@Mock
	private CardService cservice;
	
	@Mock
	private SaleService sservice;
	
	@Spy
	private Model model;

	@Spy
	List<Card> card = new ArrayList<Card>();
	@Spy
	List<Sale> sale = new ArrayList<Sale>();

	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		card=getUserList();
	}
	
	private List<Card> getUserList() {
		Card u1= new Card();
		
		u1.setValidity("12/26");
		u1.setCardType("Titanium");
		u1.setCardLimit(10000);
		u1.setTotalCredit(10000);
		u1.setCreditUsed(10000);
		u1.setUserName("Vishal");
		
		List<Card> res= new ArrayList<Card>();
		res.add(u1);
		return res;
	}
	@Test
	void testGetCard() {
		Card res=new Card();

		res.setUserName(card.get(0).getUserName());


		when(cservice.findCardByuserName(card.get(0).getUserName())).thenReturn(res);

		Card addc = cservice.findCardByuserName(card.get(0).getUserName());

		assertNotNull(addc.getUserName());
		assertEquals(res.getUserName(), addc.getUserName());

		verify(cservice,times(1)).findCardByuserName(card.get(0).getUserName());

	}

	@Test
	void testGetProductListByUsername() {
		Card res1=new Card();
		List<Sale> res= sale;

		when(sservice.getProductListByUsername(card.get(0).getUserName())).thenReturn(res);
		res=sservice.getProductListByUsername(card.get(0).getUserName());

		assertNotNull(res);
		verify(sservice,times(1)).getProductListByUsername(card.get(0).getUserName());
	}

}
