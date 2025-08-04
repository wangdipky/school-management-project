package vn.com.v4v.identityservice.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.com.v4v.exception.DetailException;
import vn.com.v4v.identityservice.entity.SchAccount;
import vn.com.v4v.identityservice.entity.SchAccountGroup;
import vn.com.v4v.identityservice.entity.SchPwd;
import vn.com.v4v.identityservice.req.AuthReq;
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
    public String generateToken(AuthReq authReq) {

        // Init variable and param
        int countLock = 3;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Map<String, Object> claims = new HashMap<>();
        SchPwd sPwd = null;
        // Check user exist
        SchAccount account = this.iUserService.loadUserByUsername(authReq.getUsername());
        if(ObjectUtils.isEmpty(account)) {

            throw new DetailException("User not found");
        }

        sPwd = this.iUserService.getPasswordByUserId(account.getId());
        if(ObjectUtils.isEmpty(sPwd)) {

            throw new DetailException("Password is empty");
        }

        if(sPwd.getCountWrong() >= countLock && sPwd.getIsLock()) {

            throw new DetailException("Account is locked");
        }

        if(!encoder.matches(authReq.getPassword(), sPwd.getPassword())) {

            this.iUserService.updateStatusWrong(sPwd.getCountWrong() + 1
                    , countLock == sPwd.getCountWrong() + 1
                    , sPwd.getAccountId());
            throw new DetailException("Wrong password");
        }

        // Get list group
        List<Long> listGroupIds = this.iUserService.getListGroupIds(account.getId());

        List<Long> listRoleIds = this.iUserService.getListRoleIds(listGroupIds);

        List<Long> listFunction = this.iUserService.getListFunctionIds(listRoleIds);

        List<String> listRoles = this.iUserService.getListRoles(listFunction);
        claims.put("roles", listRoles);

        return Jwts
                .builder()
                .subject(authReq.getUsername())
                .expiration(new Date(System.currentTimeMillis() + accessTokenTime))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey)))
                .claims(claims)
                .compact();
    }
}