package com.wellsfargo.consumerfinancemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.consumerfinancemanagement.model.Card;
import com.wellsfargo.consumerfinancemanagement.model.Sale;
import com.wellsfargo.consumerfinancemanagement.service.CardService;
import com.wellsfargo.consumerfinancemanagement.service.SaleService;
import com.wellsfargo.consumerfinancemanagement.service.UserService;

@RestController // generate & manage REST API in json format
@RequestMapping(value="/api")
public class CardController {
	@Autowired
	private CardService cservice;
	
	@Autowired
	private SaleService sservice;
	
	@GetMapping(value = "/card/{userName}")
	public Card getCard(@PathVariable("userName") String userName) {
		Card c = cservice.findCardByuserName(userName);
		return c;
	}
	
	@GetMapping(value = "/purchaseList/{userName}")
	public List<Sale> getProductListByUsername(@PathVariable("userName") String userName) {
		return sservice.getProductListByUsername(userName);
	}
	
}
