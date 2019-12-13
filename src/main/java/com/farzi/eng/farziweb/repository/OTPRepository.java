package com.farzi.eng.farziweb.repository;

import com.farzi.eng.farziweb.entity.OTP;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OTPRepository extends CrudRepository<OTP, Long> {

    @Query(value ="SELECT * FROM otp where otp_number = :otpNumber", nativeQuery = true)
    OTP findOtpNumber(@Param("otpNumber") String otpNumber);
}
