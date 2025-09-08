package vn.com.v4v.common;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import vn.com.v4v.dto.BaseResponseDto;
import vn.com.v4v.exception.DetailException;

import java.util.Date;
import java.util.UUID;

/**
 * Name: HandleError
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public class HandleError {

    public BaseResponse handleError(Long start, Exception exception) {

        // Init var
        HttpStatus status = null;
        BaseResponse response = null;
        BaseResponseDto responseDto;

        // Set status
        if(exception instanceof DetailException) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        // Set dto
        responseDto = BaseResponseDto.builder()
                .data(exception.getMessage())
                .time(new Date(start))
                .requestId(UUID.randomUUID().toString())
                .build();
        // Init return data
        response = new BaseResponse(responseDto, status);
        return response;
    }

    public BaseResponse handleBindingResult(Long start, BindingResult bindingResult) {

        HttpStatus status = null;
        BaseResponse response = null;
        BaseResponseDto responseDto;
        if(bindingResult.hasErrors()) {

            // Set status
            status = HttpStatus.BAD_REQUEST;
            // Set dto
            responseDto = BaseResponseDto.builder()
                    .data(bindingResult.getFieldErrors())
                    .time(new Date(start))
                    .requestId(UUID.randomUUID().toString())
                    .build();

            // Init return data
            response = new BaseResponse(responseDto, status);
        }
        return response;
    }

}