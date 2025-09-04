package vn.com.v4v.groupservice.rest.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.v4v.common.AbstractRest;
import vn.com.v4v.common.BaseResponse;
import vn.com.v4v.constant.CommonConstant;
import vn.com.v4v.groupservice.rest.IGroupRest;

/**
 * Name: GroupRestImpl
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 05/09/2025
 * */
@RestController
@RequestMapping(CommonConstant.API_V1 + "/group")
public class GroupRestImpl extends AbstractRest implements IGroupRest {

    @Override
    public BaseResponse searchGroup(MultiValueMap<String, String> params
            , Pageable pageable
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        long start = System.currentTimeMillis();
        try {

        } catch (Exception e) {

            return this.handleError.handleError(start, e);
        }
        return null;
    }
}