package vn.com.v4v.identityservice.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import vn.com.v4v.exception.DetailException;
import vn.com.v4v.identityservice.dto.AuthResponseDto;
import vn.com.v4v.identityservice.entity.SchAccount;
import vn.com.v4v.identityservice.entity.SchPwd;
import vn.com.v4v.identityservice.req.AuthReq;
import vn.com.v4v.identityservice.req.RefreshTokenReq;
import vn.com.v4v.identityservice.service.IJwtService;
import vn.com.v4v.identityservice.service.IUserService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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

    @Value("${jwt.refresh_token_time}")
    private Long refreshTokenTime;

    private final IUserService iUserService;

    @Autowired
    public JwtServiceImpl(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthResponseDto generateToken(AuthReq authReq) {

        // Init variable and param
        AuthResponseDto authResponseDto = new AuthResponseDto();
        String token, refreshToken = null;
        int countLock = 3;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Map<String, Object> accessClaims = new HashMap<>();
        Map<String, Object> refreshClaims = new HashMap<>();
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
                    , sPwd.getAccountId(), new Date(), sPwd.getRefreshToken());
            throw new DetailException("Wrong password");
        }

        // Get list roles
        List<String> listRoles = this.getRoles(account.getId());
        accessClaims.put("roles", listRoles);
        refreshClaims.put("roles", listRoles);
        refreshClaims.put("refreshToken", true);
        // Create token
        token = Jwts
                .builder()
                .subject(authReq.getUsername())
                .expiration(new Date(System.currentTimeMillis() + accessTokenTime))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey)))
                .claims(accessClaims)
                .compact();

        // Create refreshToken
        refreshToken = Jwts
                .builder()
                .subject(authReq.getUsername())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenTime))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey)))
                .claims(refreshClaims)
                .compact();

        // Reset count wrong password
        if(token != null) {

            this.iUserService.updateStatusWrong(0
                    , false
                    , sPwd.getAccountId(), sPwd.getLastWrong(), refreshToken);
        }

        // set data
        authResponseDto.setAccessToken(token);
        authResponseDto.setExpiresIn(new Date(System.currentTimeMillis() + accessTokenTime).getTime());
        authResponseDto.setRefreshToken(refreshToken);

        return authResponseDto;
    }

    @Override
    public AuthResponseDto refreshToken(RefreshTokenReq refreshTokenReq) {

        AuthResponseDto authResponseDto = new AuthResponseDto();
        String refreshToken = null;
        Map<String, Object> claims = new HashMap<>();
        SchAccount account = this.iUserService.loadUserByUsername(refreshTokenReq.getUsername());
        if(ObjectUtils.isEmpty(account)) {

            throw new DetailException("User not found");
        }

        // String refresh token
        SchPwd sPwd = this.iUserService.getPasswordByUserId(account.getId());
        refreshToken = sPwd.getRefreshToken();

        // Check valid token
        if(!StringUtils.equals(refreshTokenReq.getRefreshToken(), refreshToken)) {

            throw new DetailException("Refresh token is invalid");
        }

        // Check refresh token expr
        Date exprDate = this.extractClaims(refreshToken, Claims::getExpiration);
        if(new Date().after(exprDate)) {
            throw new DetailException("Refresh token is expired");
        }

        // Get list role
        List<String> listRoles = this.getRoles(account.getId());
        claims.put("roles", listRoles);

        // Create new token
        String newToken = Jwts
                .builder()
                .subject(refreshTokenReq.getUsername())
                .expiration(new Date(System.currentTimeMillis() + accessTokenTime))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey)))
                .claims(claims)
                .compact();

        // Set data return
        authResponseDto.setRefreshToken(refreshToken);
        authResponseDto.setAccessToken(newToken);
        authResponseDto.setExpiresIn(new Date(System.currentTimeMillis() + accessTokenTime).getTime());
        return authResponseDto;
    }

    @Override
    public List<String> getRoles(Long accountId) {

        List<Long> listGroupIds = this.iUserService.getListGroupIds(accountId);

        List<Long> listRoleIds = this.iUserService.getListRoleIds(listGroupIds);

        List<Long> listFunction = this.iUserService.getListFunctionIds(listRoleIds);

        return this.iUserService.getListRoles(listFunction);
    }

    private Claims getClaims(String token) {

        return Jwts
                .parser()
                .decryptWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey)))
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaims(String token, Function<Claims, T> function) {

        Claims claims = this.getClaims(token);
        return function.apply(claims);
    }
}