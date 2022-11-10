package com.wellsfargo.consumerfinancemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.consumerfinancemanagement.model.Product;
import com.wellsfargo.consumerfinancemanagement.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController // generate & manage REST API in json format
@RequestMapping(value="/api/product")
public class ProductController {
	@Autowired
	private ProductService pservice;
	
	
	/*POST - http://localhost:8082/consumerfinancemanagement/api/user */
	@PostMapping("/")		
	public Product AddProduct(@Validated @RequestBody Product product) {
		Product p = new Product();
		
		System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getProductDetails()+ " " + product.getCost());
		
		p.setProductId(product.getProductId());
		p.setProductName(product.getProductName());
		p.setProductDetails(product.getProductDetails());
		p.setProductURL(product.getProductURL());
		
		p.setCost(product.getCost());
		p.setPurchaseCount();
		
		System.out.println(p.getProductId() + " " + p.getProductName() + " " + p.getProductDetails()+ " " + p.getCost() + " " + p.getPurchaseCount());
		
		return pservice.AddProduct(p);
	}
	
	@GetMapping(value = "/productDashboard")
	public List<Product> dashBoard() {
		return pservice.Dashboard();
	}
	
	@GetMapping(value = "/getProduct/{productid}")
	public Product getProduct(@PathVariable("productid") String id) {
		return pservice.getProductCost(id);
	}
	
	@GetMapping(value = "/productMax")
	public Product getOne() {
		return pservice.getOne();
	}

}
