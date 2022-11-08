package com.wellsfargo.consumerfinancemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.consumerfinancemanagement.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

	@Query(value = "SELECT * FROM card c WHERE c.user_Name = :userName", nativeQuery = true)
	public Card findCardByuserName(@Param("userName") String userName);

	@Query(value = "SELECT c.total_credit-c.credit_used FROM card c WHERE c.user_Name =?1", nativeQuery = true)
	public int getAvailableLimit(String username);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE card SET credit_used=credit_used+?2 WHERE user_name = ?1", nativeQuery = true)
	public void debitAmount(String username, int amountPaid);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE card SET credit_used=credit_used-?2 WHERE user_name = ?1", nativeQuery = true)
	public void payDebit(String userName, int amount);
}
