package vn.com.v4v.commonservice.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.v4v.commonservice.constant.MasterDataConst;
import vn.com.v4v.commonservice.req.GetMasterDataReq;
import vn.com.v4v.commonservice.rest.IMasterDataRest;
import vn.com.v4v.commonservice.service.IMasterDataService;
import vn.com.v4v.constant.CommonConstant;

/**
 * Name: MasterDataRestImpl
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
@RestController
@RequestMapping(CommonConstant.API_V1 + MasterDataConst.URI_MASTER_DATA)
public class MasterDataRestImpl implements IMasterDataRest {

    private final IMasterDataService iMasterDataService;

    @Autowired
    public MasterDataRestImpl(IMasterDataService iMasterDataService) {
        this.iMasterDataService = iMasterDataService;
    }

    @GetMapping(CommonConstant.URL_LIST)
    @Override
    public Object getAllMasterData() {

        return this.iMasterDataService.getAllMasterData();
    }

    @Override
    public Object getMasterData(GetMasterDataReq masterDataReq) {

        return this.iMasterDataService.getMasterData(masterDataReq);
    }
}
