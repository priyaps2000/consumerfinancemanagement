package com.wellsfargo.consumerfinancemanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.consumerfinancemanagement.model.Product;
import com.wellsfargo.consumerfinancemanagement.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository prepo;
	
	public Product AddProduct(Product product) {
		return prepo.save(product); // invoke jpa repository save() method
	}

	public List<Product> Dashboard() {
		// TODO Auto-generated method stub
		return prepo.findAll();
	}

	public Product getOne() {
		// TODO Auto-generated method stub
		return prepo.getOne();
	}
}
