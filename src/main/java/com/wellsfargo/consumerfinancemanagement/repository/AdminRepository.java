package com.wellsfargo.consumerfinancemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.consumerfinancemanagement.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	@Modifying
	@Query(value = "UPDATE user SET actvn_status = ?2 WHERE user_name = ?1", nativeQuery = true)
	void changeActStatus(String userName, String response);
	
	@Query(value = "SELECT password FROM admin a WHERE a.adminu_name = :userName", nativeQuery = true)
	public String findPasswordByaName(@Param("userName") String userName);
	
//	@Modifying(clearAutomatically = true)
//	@Query(value = "UPDATE user SET password = ?2 WHERE user_name = ?1", nativeQuery = true)
//	void updatePwdByuName(String userName, String password);

}

