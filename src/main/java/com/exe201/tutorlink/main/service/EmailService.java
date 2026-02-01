package com.exe201.tutorlink.main.service;

import com.exe201.tutorlink.main.client.SendGridClient;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final SendGridClient sendGridClient;

    @Value("${app.mail.from}")
    private String fromEmail;

    private final Cache<String, String> otpCache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();

    public String generateOTP(String email) {
        String otp = String.valueOf(new Random().nextInt(8999) + 1000);
        otpCache.put(email, otp);
        return otp;
    }

    public void sendOtpEmail(String to, String otp) {
        String subject = "Mã xác thực OTP - TutorLink";
        String content = "Mã OTP của bạn là: " + otp + ". Mã có hiệu lực trong 5 phút.";

        sendGridClient.send(fromEmail, to, subject, content);
    }

    public boolean validateOTP(String email, String otp) {
        String cachedOtp = otpCache.getIfPresent(email);
        return otp.equals(cachedOtp);
    }

    public void clearOTP(String email) {
        otpCache.invalidate(email);
    }
}
