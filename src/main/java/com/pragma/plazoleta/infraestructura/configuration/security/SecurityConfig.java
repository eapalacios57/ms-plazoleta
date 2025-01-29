package com.pragma.plazoleta.infraestructura.configuration.security;

import com.pragma.plazoleta.application.handler.security.JwtTokenFilterHandler;
import com.pragma.plazoleta.infraestructura.exception.CustomAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

   private JwtTokenFilterHandler jwtAuthenticationFilter;
   private CustomAuthenticationEntryPoint authenticationEntryPoint;
   private AccessDeniedHandler accessDeniedHandler;
   private AuthenticationProvider authenticationProvider;

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http.csrf(AbstractHttpConfigurer::disable)
              .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authenticationProvider(authenticationProvider)
              .authorizeHttpRequests( authReqConfig -> {
                  authReqConfig.requestMatchers(HttpMethod.POST, "/error").permitAll();
                  authReqConfig.anyRequest().authenticated();

              })
              .exceptionHandling(exceptConfig -> {
                 exceptConfig.authenticationEntryPoint(authenticationEntryPoint);
                 exceptConfig.accessDeniedHandler(accessDeniedHandler);
              })
              .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
              .build();
   }

//   private void buildRequestMatcher(
//           AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
//      authReqConfig.requestMatchers(HttpMethod.POST, "/api/v1/restaurant")
//              .hasAuthority("ROL_ADMIN");
//      authReqConfig.anyRequest().authenticated();
//   }


}
