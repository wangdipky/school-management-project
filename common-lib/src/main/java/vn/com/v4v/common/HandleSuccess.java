package vn.com.v4v.common;

import org.springframework.http.HttpStatus;
import vn.com.v4v.dto.BaseResponseDto;

import java.util.Date;
import java.util.UUID;

/**
 * Name: HandleSuccess
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public class HandleSuccess {

    public BaseResponse handleSuccess(Long start, Object res) {

        // Init var
        BaseResponse response = null;
        BaseResponseDto responseDto = null;
        HttpStatus httpStatus = HttpStatus.OK;

        // Set data
        responseDto = BaseResponseDto.builder()
                .data(res)
                .time(new Date())
                .requestId(UUID.randomUUID().toString())
                .build();

        response = new BaseResponse(responseDto, httpStatus);
        return response;
    }
}