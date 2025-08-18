package vn.com.v4v.identityservice.req;

import lombok.Getter;
import lombok.Setter;

/**
 * Name: RefreshTokenReq
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 18/08/2025
 * */
@Getter
@Setter
public class RefreshTokenReq {

    private String username;

    private String refreshToken;
}