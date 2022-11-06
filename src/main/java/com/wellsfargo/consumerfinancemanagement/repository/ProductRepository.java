package com.wellsfargo.consumerfinancemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.consumerfinancemanagement.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Modifying
	@Query(value = "UPDATE user SET actvn_status = ?2 WHERE user_name = ?1", nativeQuery = true)
	void changeActStatus(String userName, String response);
	
	@Query(value = "SELECT password FROM admin a WHERE a.adminu_name = :userName", nativeQuery = true)
	public String findPasswordByaName(@Param("userName") String userName);

	@Query(value = "SELECT * FROM product p WHERE p.purchase_count = (SELECT MAX(p.purchase_count) FROM product p) LIMIT 1", nativeQuery = true)
	public Product getOne();
	
//	@Modifying(clearAutomatically = true)
//	@Query(value = "UPDATE user SET password = ?2 WHERE user_name = ?1", nativeQuery = true)
//	void updatePwdByuName(String userName, String password);

}

