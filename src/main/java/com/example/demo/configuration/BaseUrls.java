package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseUrls {

  @Bean("anime-service-base-url")
  public String animeServiceBaseUrl() {
    return "https://api.jikan.moe/v4/";
  }
}
