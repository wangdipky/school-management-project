package vn.com.v4v.commonservice.rest.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.v4v.common.*;
import vn.com.v4v.commonservice.constant.MasterDataConst;
import vn.com.v4v.commonservice.entity.MasterData;
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
public class MasterDataRestImpl extends AbstractRest implements IMasterDataRest {

    private final IMasterDataService iMasterDataService;

    @Autowired
    public MasterDataRestImpl(IMasterDataService iMasterDataService) {
        this.iMasterDataService = iMasterDataService;
    }

    @GetMapping(CommonConstant.URL_LIST)
    @Override
    public BaseResponse getAllMasterData(MultiValueMap<String, String> params
                                        , Pageable pageable
                                        , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        long start = System.currentTimeMillis();
        try {

            ObjectDataRes<MasterData> response = iMasterDataService.getAllMasterData(pageable);
            return this.handleSuccess.handleSuccess(start, response);
        } catch (Exception e) {

            return this.handleError.handleError(start, e);
        }
    }

    @GetMapping(MasterDataConst.URI_GET_DETAIL)
    @Override
    public BaseResponse getMasterData(MultiValueMap<String, String> params) {

        long start = System.currentTimeMillis();
        try {

            GetMasterDataReq req = this.buildSearch(params, GetMasterDataReq.class);
            ObjectDataRes<MasterData> response = iMasterDataService.getMasterData(req);
            return this.handleSuccess.handleSuccess(start, response);
        } catch (Exception e) {
            return this.handleError.handleError(start, e);
        }
    }
}
