package com.devendra.springcoredemo.config;

import com.devendra.springcoredemo.common.Coach;
import com.devendra.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    //@Bean
    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
