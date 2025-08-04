package vn.com.v4v.identityservice.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.v4v.common.AbstractRest;
import vn.com.v4v.common.BaseRes;
import vn.com.v4v.identityservice.req.AuthReq;
import vn.com.v4v.identityservice.rest.IAuthRest;
import vn.com.v4v.identityservice.service.IJwtService;

/**
 * Name: AuthRestImpl
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 02/08/2025
 * */
@RestController
@RequestMapping("/api/v1/login")
public class AuthRestImpl extends AbstractRest implements IAuthRest {

    private final IJwtService iJwtService;

    @Autowired
    public AuthRestImpl(IJwtService iJwtService) {
        this.iJwtService = iJwtService;
    }

    @PostMapping
    @Override
    public BaseRes login(AuthReq req) {

        long start = System.currentTimeMillis();
        try {
            String response = iJwtService.generateToken(req);

            return this.handleSuccess.handleSuccess(start, response);
        } catch (Exception e) {

            return this.handleError.handleError(start, e);
        }
    }

}