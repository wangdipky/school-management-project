package vn.com.v4v.identityservice.service;


import java.util.List;

/**
 * Name: IJwtService
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 02/08/2025
 * */
public interface IJwtService {

    String generateToken(String subject, List<String> roles);
}