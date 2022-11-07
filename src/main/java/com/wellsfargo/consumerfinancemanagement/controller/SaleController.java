package com.wellsfargo.consumerfinancemanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Math;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.consumerfinancemanagement.model.Card;
import com.wellsfargo.consumerfinancemanagement.model.Sale;
import com.wellsfargo.consumerfinancemanagement.model.User;
import com.wellsfargo.consumerfinancemanagement.repository.UserRepository;
import com.wellsfargo.consumerfinancemanagement.service.CardService;
import com.wellsfargo.consumerfinancemanagement.service.ProductService;
import com.wellsfargo.consumerfinancemanagement.service.SaleService;

@RestController // generate & manage REST API in json format
@RequestMapping(value="/api")
public class SaleController {
	
	@Autowired
	private SaleService sservice;
	
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private CardService cservice;

	@PostMapping("/buyNow/{username}/{productId}/{tenurePeriod}")
	public String registerSale(@PathVariable("username") String username, @PathVariable("productId") String productId, @PathVariable("tenurePeriod") int tenurePeriod) {
		
		Card userCard = cservice.findCardByuserName(username);
		if(userCard == null) {
			return("Oops! Your card is not active, Please contact admin.");
		}
		
		int productCost = pservice.getProductCost(productId);
		int availableLimit = userCard.getTotalCredit()-userCard.getCreditUsed();
		
		
		if(productCost > availableLimit ) {
			return("Oops! You don't have enough amount in you card to buy this product.");
		}
		
		Sale s1=new Sale();
		
		s1.setUserName(username);
		s1.setProductId(productId);
		s1.setTenurePeriod(tenurePeriod);

	    s1.setPurchaseDate(new Date());
		s1.getPurchaseDate();
		
		s1.setTotalAmount(productCost);
		
		int amountPaid = (int)Math.ceil(s1.getTotalAmount()/tenurePeriod);
		cservice.debitAmount(username, amountPaid);
		
		s1.setAmountpaid(amountPaid);
		sservice.registerSale(s1);
		return("Order Placed!");
	}
}
