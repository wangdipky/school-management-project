package vn.com.v4v.groupservice.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import vn.com.v4v.common.BaseResponse;

/**
 * Name: IGroupRest
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 05/09/2025
 * */
public interface IGroupRest {

    BaseResponse searchGroup(@RequestParam MultiValueMap<String, String> params
            , Pageable pageable
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}