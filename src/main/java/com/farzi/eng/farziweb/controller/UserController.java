package com.farzi.eng.farziweb.controller;

import com.farzi.eng.farziweb.model.CreateAccountParams;
import com.farzi.eng.farziweb.model.LoginParams;
import com.farzi.eng.farziweb.model.LoginResponse;
import com.farzi.eng.farziweb.model.OTPParams;
import com.farzi.eng.farziweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("farzi")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginParams loginParams) {
        ResponseEntity<LoginResponse> loginResponse = userService.login(loginParams);
        return loginResponse;
    }

    @RequestMapping(value = "/otp", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> getOTP(@RequestBody OTPParams otpParams) {
        ResponseEntity<LoginResponse> loginResponse = userService.generateOTP(otpParams);
        return loginResponse;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> register(@RequestBody CreateAccountParams params){
        ResponseEntity<LoginResponse> loginResponse = userService.register(params);
        return loginResponse;
    }
}