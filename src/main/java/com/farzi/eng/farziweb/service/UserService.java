package com.farzi.eng.farziweb.service;

import com.farzi.eng.farziweb.FarziUtil;
import com.farzi.eng.farziweb.entity.OTP;
import com.farzi.eng.farziweb.entity.User;
import com.farzi.eng.farziweb.exceptionHandler.FarziError;
import com.farzi.eng.farziweb.exceptionHandler.FarziRuntimeException;
import com.farzi.eng.farziweb.model.CreateAccountParams;
import com.farzi.eng.farziweb.model.LoginParams;
import com.farzi.eng.farziweb.model.LoginResponse;
import com.farzi.eng.farziweb.model.OTPParams;
import com.farzi.eng.farziweb.repository.OTPRepository;
import com.farzi.eng.farziweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.farzi.eng.farziweb.FarziUtil.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Override
    public ResponseEntity<LoginResponse> login(LoginParams login) throws FarziRuntimeException{
        LoginResponse response = new LoginResponse();
        try {
            User dbUser = userRepository.findUsername(login.getUsername());
            String password = dbUser.getPassword();
            if (dbUser != null && dbUser.getUsername().equals(login.getUsername()) &&
                    password.equals(returnHashKey(login.getPassword()))) {
                response.setMessage("Login success");
                response.setCode(0);
            } else {
                throw new FarziRuntimeException(new FarziError(400, "201", "Invalid user and password"));
            }
        } catch (Exception err) {
            throw err;
        }
        return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LoginResponse> register(CreateAccountParams createAccount) throws FarziRuntimeException {
        LoginResponse response = new LoginResponse();
        try {
            User dbUser = userRepository.findUsername(createAccount.getUsername());
            Iterable<OTP> otpLIST = otpRepository.findAll();
            for (OTP otp : otpLIST) {
                if (otp.getOtpNumber().equalsIgnoreCase(createAccount.getOtp()) && otp.getStatus().equalsIgnoreCase("ACTIVE")) {
                    if (otp.getOtpNumber().equalsIgnoreCase(createAccount.getOtp())) {
                        if (otp.getStatus().equalsIgnoreCase("ACTIVE")) {
                            if (!(dbUser != null && createAccount.getUsername().equals(createAccount.getUsername()))) {
                                User user = new User();
                                user.setUsername(createAccount.getUsername());
                                user.setEmail(createAccount.getEmail());
                                user.setPassword(returnHashKey(createAccount.getPassword()));
                                user.setNumber(createAccount.getMobileNumber());
                                user.setTimestamp(FarziUtil.getCurrentDate());
                                userRepository.save(user);
                                response.setMessage("Registered account success");
                                response.setCode(0);
                            } else {
                                throw new FarziRuntimeException(new FarziError(400, "111", "Register failed due to duplicate account"));
                            }
                        }
                    } else {
                        throw new FarziRuntimeException(new FarziError(400, "201", "OTP is expired please resend again"));
                    }
                }
            }

        } catch (Exception err) {
            throw err;
        }
        return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LoginResponse> generateOTP(OTPParams otpParams) throws FarziRuntimeException {
        LoginResponse response = new LoginResponse();
        try {
            String otp = getRandomOTP();
            String status = getOTPStatus(otpParams.getNumber(), otp);
            if (status.contains("success")) {
                response.setMessage("OTP sent successfully");
                response.setCode(0);
                OTP otpDB = new OTP();
                otpDB.setOtpNumber(otp);
                otpDB.setStatus("ACTIVE");
                otpDB.setTimestamp(FarziUtil.getCurrentDate());
                otpRepository.save(otpDB);
            } else {
                throw new FarziRuntimeException(new FarziError(400, "500", status));
            }
        } catch (Exception err) {
            throw err;
        }
        return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
    }
}
