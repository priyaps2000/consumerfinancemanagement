package com.wellsfargo.consumerfinancemanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.consumerfinancemanagement.model.Sale;
import com.wellsfargo.consumerfinancemanagement.repository.SaleRepository;

@Service
@Transactional
public class SaleService {
	
	@Autowired
	private SaleRepository srepo;
	
	public Sale registerSale(Sale sale) {
		return srepo.save(sale);
	}

	public List<Sale> getProductListByUsername(String userName) {
		return srepo.getProductListByUsername(userName);
	}

}
