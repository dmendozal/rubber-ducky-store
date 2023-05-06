package com.danielml.rubbyduckystore.infrastructure.config;

import com.danielml.rubbyduckystore.application.service.ducky.DomainDuckyService;
import com.danielml.rubbyduckystore.application.service.ducky.IDuckyService;
import com.danielml.rubbyduckystore.application.service.order.DomainOrderService;
import com.danielml.rubbyduckystore.application.service.order.IOrderService;
import com.danielml.rubbyduckystore.domain.ports.IDuckyRepository;
import com.danielml.rubbyduckystore.domain.ports.IOrderRepository;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BeanDuckyConfiguration {

    @Bean
    IDuckyService duckyBeanService(final IDuckyRepository duckyRepository) {
        return new DomainDuckyService(duckyRepository);
    }

    @Bean
    IOrderService duckyOrderBeanService(final IOrderRepository orderRepository, IDuckyRepository duckyRepository) {
        return new DomainOrderService(duckyRepository, orderRepository);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }
}
