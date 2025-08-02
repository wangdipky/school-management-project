package vn.com.v4v.identityservice.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Name: AuthReq
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 02/08/2025
 * */
@Getter
@Setter
@NoArgsConstructor
public class AuthReq {

    private String username;

    private String password;
}