package vn.com.v4v.identityservice.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import vn.com.v4v.identityservice.service.IJwtService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name: JwtServiceImpl
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 02/08/2025
 * */
@Service
@PropertySource("classpath:config.properties")
public class JwtServiceImpl implements IJwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public String generateToken(String subject, List<String> roles) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        return Jwts
                .builder()
                .subject(subject)
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey)))
                .claims(claims)
                .compact();
    }
}