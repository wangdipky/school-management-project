package vn.com.v4v.identityservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Name: AuthResponseDto
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 18/08/2025
 * */
@Getter
@Setter
public class AuthResponseDto {

    private String accessToken;
    private Long expiresIn;
    private String refreshToken;
}
