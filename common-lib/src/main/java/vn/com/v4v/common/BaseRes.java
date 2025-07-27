package vn.com.v4v.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

/**
 * Name: BaseRes
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public class BaseRes {

    @Getter
    @Setter
    private Long request;

    @Getter
    private final Date date = new Date();

    @Getter
    @Setter
    private ResponseEntity<Object> data;
}