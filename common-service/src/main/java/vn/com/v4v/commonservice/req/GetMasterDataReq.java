package vn.com.v4v.commonservice.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Name: GetMasterDataReq
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
@Getter
@Setter
@NoArgsConstructor
public class GetMasterDataReq {

    private String kindCode;

    private String groupCode;
}
