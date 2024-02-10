package com.devendra.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {
    @Override
    public String getFortune() {
        return getFortune(false);
    }

    @Override
    public String getFortune(boolean flag) {

        if(flag) {
            throw new RuntimeException("Major accident. Highway is closed");
        }

        // simulate a delay
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // return a fortune
        return "Expect Heavy traffic this morning :(";
    }
}
