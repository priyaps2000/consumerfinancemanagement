package com.wellsfargo.consumerfinancemanagement.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wellsfargo.consumerfinancemanagement.model.Card;
import com.wellsfargo.consumerfinancemanagement.model.User;
import com.wellsfargo.consumerfinancemanagement.repository.CardRepository;

@Service
@Transactional
public class CardService {
	@Autowired
	private CardRepository crepo;

	public Card activateCard(User user)
	{
		Card c = new Card();

		long lowBound = 100000000000L;
		long uppBound = 999999999999L;
		long rand = (long) Math.floor(Math.random()*(uppBound-lowBound+1)+lowBound);

		LocalDate curDate = LocalDate.now();
		int curMonth = curDate.getMonthValue();
		int curYear = curDate.getYear();
		int valYear = curYear+5;

		c.setUserName(user.getUserName());
		c.setCardNo(rand);
		c.setCardType(user.getCardType());
		if(user.getCardType().equals("Gold"))
		{
			c.setCardLimit(60000);
		}
		else
		{
			c.setCardLimit(100000);
		}
		c.setTotalCredit(c.getCardLimit());
		c.setCreditUsed(0);
		c.setValidity(curMonth + "/" + valYear);	
		return crepo.save(c);

	}

	public Card findCardByuserName(String userName) {
		// TODO Auto-generated method stub
		return crepo.findCardByuserName(userName);
	}

	public int getAvailableLimit(String username) {
		return crepo.getAvailableLimit(username);
	}

	public void debitAmount(String username, int amountPaid) {
		crepo.debitAmount(username, amountPaid);
	}

	public void payDebit(String userName, int amount) {
		// TODO Auto-generated method stub
		crepo.payDebit(userName, amount);
	}

}
