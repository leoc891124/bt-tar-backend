package com.leoc.springboot.bttar.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CaptchaService {
    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL =
            "https://www.google.com/recaptcha/api/siteverify";

    public boolean verifyCaptcha(String token) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> response = restTemplate.postForEntity(
                GOOGLE_RECAPTCHA_VERIFY_URL + "?secret=" + recaptchaSecret + "&response=" + token,
                null,
                Map.class
        );

        Map body = response.getBody();
        return body != null && (Boolean) body.get("success");
    }
}
