package com.anurag.ExpenseTracker.model.dto;

import com.anurag.ExpenseTracker.model.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank
    private String userFirstName;
    @NotBlank
    private String userLastName;
    @Enumerated(EnumType.STRING)
    private Gender userGender;
    @NotBlank
    private String userAddress;
    @Email
    @Column(unique = true)
    private String userEmail;
    @NotBlank
    private String userPassword;
    @NotBlank
    private String phoneNumber;

}