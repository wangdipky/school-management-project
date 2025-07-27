package vn.com.v4v.commonservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Name: IMasterDataRest
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
@Getter
@Setter
@NoArgsConstructor
public class GetAllMasterDataDto {

    private Long id;

    private String groupCode;

    private String kindCode;

    private String code;

    private String value;

    private Date createDate;

    private Long createdId;
}