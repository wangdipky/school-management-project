package vn.com.v4v.groupservice.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import vn.com.v4v.common.BaseResponse;
import vn.com.v4v.groupservice.dto.AddGroupDto;

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


    BaseResponse addGroup(@RequestBody AddGroupDto dto
            , BindingResult bindingResult
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    void exportExcel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;
}