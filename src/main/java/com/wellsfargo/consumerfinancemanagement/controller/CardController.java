package com.wellsfargo.consumerfinancemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.consumerfinancemanagement.model.Card;
import com.wellsfargo.consumerfinancemanagement.model.Sale;
import com.wellsfargo.consumerfinancemanagement.service.CardService;
import com.wellsfargo.consumerfinancemanagement.service.SaleService;
import com.wellsfargo.consumerfinancemanagement.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController // generate & manage REST API in json format
@RequestMapping(value="/api/card")
public class CardController {
	@Autowired
	private CardService cservice;
	
	@Autowired
	private SaleService sservice;
	
	@GetMapping(value = "/{userName}")
	public Card getCard(@PathVariable("userName") String userName) {
		Card c = cservice.findCardByuserName(userName);
		return c;
	}
	
	@GetMapping(value = "/purchaseList/{userName}")
	public List<Sale> getProductListByUsername(@PathVariable("userName") String userName) {
		return sservice.getProductListByUsername(userName);
	}
	
	@PostMapping(value = "/paydebit/{userName}/{amount}")
	public void payDebit(@PathVariable("userName") String userName, @PathVariable("amount") int amount) {
		cservice.payDebit(userName, amount);
	}
	
}
