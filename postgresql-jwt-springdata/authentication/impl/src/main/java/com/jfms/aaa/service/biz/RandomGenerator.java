package com.jfms.aaa.service.biz;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomGenerator {

    private String digits = "0123456789";
    private Random random = new Random();
    public String getRandomNumber(Integer length) {
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomNumber.append(
                    digits.charAt(random.nextInt(digits.length()))
            );
        }
        return randomNumber.toString();
    }
}
