package com.anurag.ExpenseTracker.controller;

import com.anurag.ExpenseTracker.model.User;
import com.anurag.ExpenseTracker.model.dto.SignInInput;
import com.anurag.ExpenseTracker.model.dto.SignInOutput;
import com.anurag.ExpenseTracker.model.dto.SignUpOutput;
import com.anurag.ExpenseTracker.model.dto.UserRequest;
import com.anurag.ExpenseTracker.service.AuthenticationService;
import com.anurag.ExpenseTracker.service.ExpenseService;
import com.anurag.ExpenseTracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;


@RestController
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    AuthenticationService authenticationService;


    //sign up, sign in , sign out a particular instagram user
    @PostMapping("user/signup")
    public SignUpOutput signUpInstaUser(@RequestBody UserRequest userRequest) throws NoSuchAlgorithmException {

        return userService.signUpUser(userRequest);
    }

    @PostMapping("user/signIn")
    public SignInOutput sigInInstaUser(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String sigOutInstaUser(String email, String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.sigOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }

    }
}
