package vn.com.v4v.identityservice.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.com.v4v.exception.DetailException;
import vn.com.v4v.identityservice.entity.SchAccount;
import vn.com.v4v.identityservice.entity.SchAccountGroup;
import vn.com.v4v.identityservice.service.IJwtService;
import vn.com.v4v.identityservice.service.IUserService;

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

    @Value("${jwt.access_token_time}")
    private Long accessTokenTime;

    private final IUserService iUserService;

    @Autowired
    public JwtServiceImpl(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Override
    public String generateToken(String subject, List<String> roles) {

        // Init variable and param
        Map<String, Object> claims = new HashMap<>();

        // Check user exist
        SchAccount account = this.iUserService.loadUserByUsername(subject);
        if(ObjectUtils.isEmpty(account)) {

            throw new DetailException("User not found");
        }
        // Get list group
        List<Long> listGroupIds = this.iUserService.getListGroupIds(account.getId());

        List<Long> listRoleIds = this.iUserService.getListRoleIds(listGroupIds);

        List<Long> listFunction = this.iUserService.getListFunctionIds(listRoleIds);

        List<String> listRoles = this.iUserService.getListRoles(listFunction);

        return Jwts
                .builder()
                .subject(subject)
                .expiration(new Date(System.currentTimeMillis() + accessTokenTime))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey)))
                .claims(claims)
                .compact();
    }
}