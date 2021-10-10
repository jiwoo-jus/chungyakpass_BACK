//package com.hanium.chungyakpassback.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class CorsConfig {
//
//   @Bean
//   public CorsFilter corsFilter() {
//      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      CorsConfiguration config = new CorsConfiguration();
//      config.setAllowCredentials(true);
//      config.addAllowedOrigin("*");
//      config.addAllowedHeader("*");
//      config.addAllowedMethod("*");
//
//      source.registerCorsConfiguration("/account/**", config);
//      source.registerCorsConfiguration("/user/**", config);
//      source.registerCorsConfiguration("/verification/**", config);
//      source.registerCorsConfiguration("/point/**", config);
//
//      return new CorsFilter(source);
//   }
//
//}
