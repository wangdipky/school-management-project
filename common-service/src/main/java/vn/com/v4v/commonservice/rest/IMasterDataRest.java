package vn.com.v4v.commonservice.rest;

import vn.com.v4v.commonservice.req.GetMasterDataReq;

/**
 * Name: IMasterDataRest
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public interface IMasterDataRest {

    Object getAllMasterData();

    Object getMasterData(GetMasterDataReq masterDataReq);
}