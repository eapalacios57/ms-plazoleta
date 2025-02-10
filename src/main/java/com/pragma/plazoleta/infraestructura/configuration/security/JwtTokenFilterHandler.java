package com.pragma.plazoleta.infraestructura.configuration.security;


import com.pragma.plazoleta.domain.model.UserDetail;
import com.pragma.plazoleta.infraestructura.JwtUtils;
import com.pragma.plazoleta.infraestructura.exception.CustomAuthenticationEntryPoint;
import com.pragma.plazoleta.infraestructura.exception.CustomJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
@AllArgsConstructor
public class JwtTokenFilterHandler extends OncePerRequestFilter {

//    private final RestTemplate restTemplate;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtUtils jwtUtils;
    private final CustomJwtException customJwtException;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractTokenFromRequest(request);
        try{
            if (token != null) {
                UserDetail userDetail = jwtUtils.extractUser(token);
                if (userDetail != null && userDetail.getToken() != null) {
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
        } catch (ExpiredJwtException ex) {
            SecurityContextHolder.clearContext();
            customJwtException.handleJwtExpiratedException(response, ex);

        }catch (JwtException ex) {
            customJwtException.handleJwtException(response, ex);
        }
        }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}



