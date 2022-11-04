package com.wellsfargo.consumerfinancemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.consumerfinancemanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
