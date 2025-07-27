package vn.com.v4v.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Name: HandleError
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public class HandleError {

    public BaseRes handleError(Long start, Exception exception) {

        BaseRes res = new BaseRes();
        res.setRequest(start);
        res.setData(new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        return res;
    }

}