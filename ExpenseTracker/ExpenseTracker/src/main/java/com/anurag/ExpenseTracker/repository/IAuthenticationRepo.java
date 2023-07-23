package com.anurag.ExpenseTracker.repository;

import com.anurag.ExpenseTracker.model.AuthenticationToken;

import com.anurag.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {


    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
