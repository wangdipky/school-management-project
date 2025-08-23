package vn.com.v4v.apigateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Name: JwtHandler
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 02/08/2025
 * */
@Component
public class JwtHandler {

    private Claims getClaims(String token) {

        return Jwts
                .parser()
                .decryptWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode("5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437")))
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode("5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437")))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {

        Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean validateToken(String token) {

        Date expDate = extractClaims(token, Claims::getExpiration);
        return new Date().before(expDate);
    }

    public List<String> getRoles(String token) {

        Map<String, Object> claims = getClaims(token);
        return (List<String>) claims.get("roles");
    }

    public String getUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Boolean isRefreshToken(String token) {

        Map<String, Object> claims = getClaims(token);
        return claims.get("refreshToken") != null;
    }
}