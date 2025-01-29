package com.pragma.plazoleta.infraestructura.configuration.security;

import com.pragma.plazoleta.application.dto.UserDetailResponse;
import com.pragma.plazoleta.application.handler.security.JwtTokenFilterHandler;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomUserDetailHandler implements UserDetailsService {

    private final JwtTokenFilterHandler jwtTokenFilter;
    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        UserDetailResponse user = jwtTokenFilter.validateToken(token);
        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), null, authorities);
        }

        return null;
    }
}
