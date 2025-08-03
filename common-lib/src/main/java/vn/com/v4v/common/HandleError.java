package vn.com.v4v.common;

import jakarta.ws.rs.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.com.v4v.exception.DetailException;

/**
 * Name: HandleError
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public class HandleError {

    public BaseRes handleError(Long start, Exception exception) {

        HttpStatus status = null;
        BaseRes res = new BaseRes();
        res.setRequest(start);
        // Status
        if(exception instanceof DetailException) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        res.setData(new ResponseEntity<>(exception.getMessage(), status));
        return res;
    }

}