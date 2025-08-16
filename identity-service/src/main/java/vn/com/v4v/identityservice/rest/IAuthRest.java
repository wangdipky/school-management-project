package vn.com.v4v.identityservice.rest;

import org.springframework.web.bind.annotation.RequestBody;
import vn.com.v4v.common.BaseResponse;
import vn.com.v4v.identityservice.req.AuthReq;

/**
 * Name: IAuthRest
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 02/08/2025
 * */
public interface IAuthRest {

    BaseResponse login(@RequestBody AuthReq req);
}