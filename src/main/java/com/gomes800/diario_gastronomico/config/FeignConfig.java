package com.gomes800.diario_gastronomico.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.gomes800.diario_gastronomico.client")
public class FeignConfig {
}
