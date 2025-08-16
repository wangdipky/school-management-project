package vn.com.v4v.commonservice.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import vn.com.v4v.common.BaseResponse;

/**
 * Name: IMasterDataRest
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public interface IMasterDataRest {

    BaseResponse getAllMasterData(@RequestParam MultiValueMap<String, String> params
                                , Pageable pageable
                                , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    BaseResponse getMasterData(@RequestParam MultiValueMap<String, String> params);
}