package com.wellsfargo.consumerfinancemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.consumerfinancemanagement.model.Card;
import com.wellsfargo.consumerfinancemanagement.service.CardService;
import com.wellsfargo.consumerfinancemanagement.service.UserService;

@RestController // generate & manage REST API in json format
@RequestMapping(value="/api")
public class CardController {
	@Autowired
	private CardService cservice;
	
	
	@GetMapping(value = "/card/{userName}")
	public Card getCard(@PathVariable("userName") String userName) {
		Card c = cservice.findCardByuserName(userName);
		return c;
	}
	
}
