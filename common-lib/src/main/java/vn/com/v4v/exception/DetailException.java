package vn.com.v4v.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Name: DetailException
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DetailException extends RuntimeException {

    public DetailException(String message) {
        super(message);
    }

    public DetailException(String message, Throwable cause) {
        super(message, cause);
    }
}