package com.wellsfargo.consumerfinancemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.consumerfinancemanagement.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

	@Query(value = "SELECT * FROM card c WHERE c.user_Name = :userName", nativeQuery = true)
	public Card findCardByuserName(@Param("userName") String userName);


}
