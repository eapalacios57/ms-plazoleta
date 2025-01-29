package com.pragma.plazoleta.application.handler.security;

import com.pragma.plazoleta.application.dto.UserDetailResponse;
import com.pragma.plazoleta.infraestructura.exception.CustomAuthenticationEntryPoint;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
@AllArgsConstructor
public class JwtTokenFilterHandler extends OncePerRequestFilter {

    private final RestTemplate restTemplate;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractTokenFromRequest(request);
        try{
            if (token != null) {
                UserDetailResponse userDetail = validateToken(token);
                if (userDetail != null && Boolean.TRUE.equals(userDetail.getTokenValid())) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + userDetail.getRole()));
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            userDetail, token, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    filterChain.doFilter(request, response);
                } else {
                    throw new AuthenticationException("Invalid token") {};
                }
            } else {
                throw new AuthenticationException("Missing token") {};
            }
        } catch (AuthenticationException ex) {
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, ex);
        }

    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    public UserDetailResponse validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<UserDetailResponse> response = restTemplate.exchange("http://localhost:8080/api/v1/auth/validate-token", HttpMethod.GET, entity, UserDetailResponse.class);
            return  response.getBody();
        } catch (HttpClientErrorException e) {
            throw new AuthenticationException("Invalid token: " + e.getMessage()) {};
        }
    }
}
