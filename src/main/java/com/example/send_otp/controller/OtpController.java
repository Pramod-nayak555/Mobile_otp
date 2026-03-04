package com.example.send_otp.controller;

import com.example.send_otp.service.EmailService;
//import com.example.send_otp.service.EmailService;
import com.example.send_otp.service.OtpService;
import com.example.send_otp.service.SmsService;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
@CrossOrigin(origins = "http://localhost:4200")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;


    @PostMapping("/send/sms")
    public String sendOtpSms(@RequestParam String phone) {
        String otp = otpService.generateOtp(phone);
        smsService.sendOtp(phone, otp);
        return "OTP sent to your phone. Please check your phone."+otp;
    }


    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String key, @RequestParam String otp) {
        return otpService.validateOtp(key, otp) ? "OTP Verified Successfully" : "Invalid OTP";
    }

    
    @PostMapping("/send/mail")
    public String requestOtp(@RequestParam String email) {
    	String otp = otpService.generateOtp(email);
    	emailService.sendOtp(email, otp);
    	return "OTP sent to your email. Please check your email";
    }

}
    
//    @GetMapping("/user")
//    public Map<String, Object> user(Authentication authentication) {
//
//        if (authentication == null) {
//            return Map.of("message", "Not logged in");
//        }
//
//        OAuth2User user = (OAuth2User) authentication.getPrincipal();
//
//        return Map.of(
//                "name", user.getAttribute("name"),
//                "email", user.getAttribute("email")
//        );
//    }
//}
//        );
//    }
//    @PostMapping("/verfiy-otp")
//    public String verfiyOtp(@RequestParam String email, @RequestParam String otp) {
//        return EmailService.verrifyLoginotp(email, otp);
//
//
//    }

//    @PostMapping("/send/email")
//    public String sendOtpEmail(@RequestParam String email) {
//        String otp = otpService.generateOtp(email);
//        emailService.sendOtp(email, otp);
//        return "OTP sent to email your email. Please check your email."+ otp;
//    }

//}

