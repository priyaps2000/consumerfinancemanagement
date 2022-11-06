package com.wellsfargo.consumerfinancemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.consumerfinancemanagement.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT * FROM product p WHERE p.purchase_count = (SELECT MAX(p.purchase_count) FROM product p) LIMIT 1", nativeQuery = true)
	public Product getOne();
}

