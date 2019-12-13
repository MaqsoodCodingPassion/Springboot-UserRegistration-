package com.farzi.eng.farziweb.service;

import com.farzi.eng.farziweb.model.CreateAccountParams;
import com.farzi.eng.farziweb.model.LoginParams;
import com.farzi.eng.farziweb.model.LoginResponse;
import com.farzi.eng.farziweb.model.OTPParams;
import org.springframework.http.ResponseEntity;

public interface IUserService {

     ResponseEntity<LoginResponse> login(LoginParams params);
     ResponseEntity<LoginResponse> register(CreateAccountParams params);
     ResponseEntity<LoginResponse> generateOTP(OTPParams otpParams);
}
