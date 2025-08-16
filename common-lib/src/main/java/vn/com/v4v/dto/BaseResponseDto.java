package vn.com.v4v.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

/**
 * Name: BaseResponseDto
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 16/08/2025
 * */
@Data
@Builder
public class BaseResponseDto {

    private String requestId;
    private Date time;
    private Object data;
}