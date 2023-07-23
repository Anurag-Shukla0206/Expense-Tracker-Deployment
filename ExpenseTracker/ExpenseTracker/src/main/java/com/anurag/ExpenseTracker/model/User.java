package com.anurag.ExpenseTracker.model;

import com.anurag.ExpenseTracker.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  userId;

    private String userFirstName;

    private String userLastName;

    private Gender userGender;

    private String userAddress;

    private String userEmail;

    private String userPassword;

    private String phoneNumber;
}
