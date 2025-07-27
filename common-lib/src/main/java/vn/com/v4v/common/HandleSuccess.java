package vn.com.v4v.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Name: HandleSuccess
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public class HandleSuccess {

    public BaseRes handleSuccess(Long start, Object response) {

        BaseRes res = new BaseRes();
        res.setData(new ResponseEntity<>(response, HttpStatus.OK));
        res.setRequest(start);
        return res;
    }
}