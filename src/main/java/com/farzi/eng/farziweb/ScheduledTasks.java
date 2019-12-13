package com.farzi.eng.farziweb;

import com.farzi.eng.farziweb.entity.OTP;
import com.farzi.eng.farziweb.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private OTPRepository otpRepository;

    @Scheduled(fixedRate = 10000)
    public void updateOTPStatus() {

        Iterable<OTP> otpLIST = otpRepository.findAll();
        for(OTP otp : otpLIST){
            if(otp.getStatus().equalsIgnoreCase("ACTIVE")){
                otp.setStatus("EXPIRED");
                otpRepository.save(otp);
            }
            System.out.println("Id : "+otp.getId() + " Number : "+ otp.getOtpNumber() + "Status : "+otp.getStatus());
        }
    }
}


