package com.pet_care.appointment_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
//                .allowedOrigins("http://localhost:5173") // Chỉ cho phép từ domain này
//                .allowedMethods("GET", "POST", "PUT", "DELETE") // Các phương thức HTTP được phép
//                .allowedHeaders("*") // Cho phép tất cả headers
//                .allowCredentials(true); // Cho phép gửi thông tin đăng nhập (cookies, authorization headers)
//    }
}