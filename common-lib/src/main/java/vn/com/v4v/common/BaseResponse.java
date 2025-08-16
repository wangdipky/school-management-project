package vn.com.v4v.common;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * Name: BaseRes
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public class BaseResponse extends ResponseEntity<Object> {

    public BaseResponse(HttpStatusCode status) {
        super(status);
    }

    public BaseResponse(Object body, HttpStatusCode status) {
        super(body, status);
    }

    public BaseResponse(MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(headers, status);
    }

    public BaseResponse(Object body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }

    public BaseResponse(Object body, MultiValueMap<String, String> headers, HttpStatusCode statusCode) {
        super(body, headers, statusCode);
    }
}