package com.pragma.plazoleta.infraestructura;

import com.pragma.plazoleta.domain.model.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public UserDetail extractUser(String jwt) {
        UserDetail user = new UserDetail();
        Claims claims = extractAllClaims(jwt);
        user.setId(Long.valueOf(claims.get("id").toString()));
        user.setEmail(claims.getSubject());
        user.setRole(claims.get("role").toString());
        user.setToken(jwt);
        return user;
    }

    private SecretKey generateKey() {
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    private Claims extractAllClaims(String jwt)  {
            return Jwts.parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
    }

    public static String getBearerToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        return "Bearer " + userDetail.getToken();
    }

}
