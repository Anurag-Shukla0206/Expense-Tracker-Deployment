package com.anurag.ExpenseTracker.repository;

import com.anurag.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {



     boolean existsByUserEmail(String userEmail);

    User findFirstByUserEmail(String signInEmail);
}
