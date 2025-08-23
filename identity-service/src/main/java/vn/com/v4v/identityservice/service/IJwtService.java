package vn.com.v4v.identityservice.service;

import vn.com.v4v.identityservice.dto.AuthResponseDto;
import vn.com.v4v.identityservice.req.AuthReq;
import vn.com.v4v.identityservice.req.RefreshTokenReq;

import java.util.List;

/**
 * Name: IJwtService
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 02/08/2025
 * */
public interface IJwtService {

    AuthResponseDto generateToken(AuthReq authReq);

    AuthResponseDto refreshToken(RefreshTokenReq refreshTokenReq);

    List<String> getRoles(Long accountId);
}