package com.wellsfargo.consumerfinancemanagement.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.lang.Math;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.consumerfinancemanagement.model.Card;
import com.wellsfargo.consumerfinancemanagement.model.Product;
import com.wellsfargo.consumerfinancemanagement.model.Sale;
import com.wellsfargo.consumerfinancemanagement.service.CardService;
import com.wellsfargo.consumerfinancemanagement.service.ProductService;
import com.wellsfargo.consumerfinancemanagement.service.SaleService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController // generate & manage REST API in json format
@RequestMapping(value="/api/sale")
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
		
		Product product = pservice.getProductCost(productId);
		int availableLimit = userCard.getTotalCredit()-userCard.getCreditUsed();
		
		
		if(product.getCost() > availableLimit ) {
			return("Oops! You don't have enough amount in you card to buy this product.");
		}
		
		Sale s1=new Sale();
		
		s1.setUserName(username);
		s1.setProductId(productId);
		s1.setProductName(product.getProductName());
		s1.setTenurePeriod(tenurePeriod);

	    s1.setPurchaseDate(new Date());
		s1.getPurchaseDate();
		
		s1.setTotalAmount(product.getCost());
		
		int amountPaid = (int)Math.ceil(s1.getTotalAmount()/tenurePeriod);
		cservice.debitAmount(username, amountPaid);
		
		s1.setAmountpaid(amountPaid);
		sservice.registerSale(s1);
		return("Order Placed!");
	}
	
	@PostMapping("/refresh")
	public void refresh() {
		List<Sale> lsale = sservice.getSaleData();
		
		Date curr = new Date();
		
		long diff;
		
		for(Sale s: lsale) {
			diff = TimeUnit.DAYS.convert(Math.abs(curr.getTime() - s.getPurchaseDate().getTime()), TimeUnit.MILLISECONDS);
			float temp = s.getTotalAmount()/s.getTenurePeriod();
			if(s.getAmountpaid() < (1+diff/30)*temp) {
				cservice.debitAmount(s.getUserName(), (int)Math.ceil(temp));
				sservice.amountpaid(s.getUserName(), (int)Math.ceil(temp));
			}
		}
	}
}
