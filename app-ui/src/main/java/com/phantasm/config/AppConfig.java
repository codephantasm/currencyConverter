package com.phantasm.config;

import com.phantasm.controller.ApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurations for the total app.
 *
 * @author Raghav Sharma {raghav.sharma_1995@outlook.com}
 */
// TODO Raghav: Can this be a standalone class that provides beans to the complete application ?
@Configuration
public class AppConfig {

    @Bean
    public ApiService apiService(){
        return new ApiService();
    }
}
