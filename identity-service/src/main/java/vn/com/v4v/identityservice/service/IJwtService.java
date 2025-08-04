package vn.com.v4v.identityservice.service;

import vn.com.v4v.identityservice.req.AuthReq;

/**
 * Name: IJwtService
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 02/08/2025
 * */
public interface IJwtService {

    String generateToken(AuthReq authReq);
}