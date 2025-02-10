package com.pragma.plazoleta.infraestructura.configuration.security;

import com.pragma.plazoleta.infraestructura.exception.CustomAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

   private JwtTokenFilterHandler jwtAuthenticationFilter;
   private CustomAuthenticationEntryPoint authenticationEntryPoint;
   private AccessDeniedHandler accessDeniedHandler;

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http.csrf(AbstractHttpConfigurer::disable)
              .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authorizeHttpRequests( authReqConfig -> {
                  authReqConfig.requestMatchers(HttpMethod.POST, "/error").permitAll();
                  authReqConfig.anyRequest().authenticated();

              })
              .exceptionHandling(exceptConfig -> {
                 exceptConfig.authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);
              })
              .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
              .build();
   }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(List.of());
    }

}
