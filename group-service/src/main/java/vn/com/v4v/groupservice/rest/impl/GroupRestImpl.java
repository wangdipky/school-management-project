package vn.com.v4v.groupservice.rest.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.v4v.common.AbstractRest;
import vn.com.v4v.common.ApiRequest;
import vn.com.v4v.common.BaseResponse;
import vn.com.v4v.common.ObjectDataRes;
import vn.com.v4v.constant.CommonConstant;
import vn.com.v4v.groupservice.constant.GroupConst;
import vn.com.v4v.groupservice.dto.AddGroupDto;
import vn.com.v4v.groupservice.dto.ListSearchConditionDto;
import vn.com.v4v.groupservice.entity.SchGroup;
import vn.com.v4v.groupservice.rest.IGroupRest;
import vn.com.v4v.groupservice.service.IGroupService;
import vn.com.v4v.groupservice.validator.AddGroupValidator;
import vn.com.v4v.utils.SearchUtils;

/**
 * Name: GroupRestImpl
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 05/09/2025
 * */
@RestController
@RequestMapping(CommonConstant.API_V1 + GroupConst.URI_GROUP)
public class GroupRestImpl extends AbstractRest implements IGroupRest {

    private final IGroupService iGroupService;

    private final AddGroupValidator addGroupValidator;

    @Autowired
    public GroupRestImpl(IGroupService iGroupService, AddGroupValidator addGroupValidator) {
        this.iGroupService = iGroupService;
        this.addGroupValidator = addGroupValidator;
    }

    @GetMapping(CommonConstant.URL_LIST)
    @Override
    public BaseResponse searchGroup(MultiValueMap<String, String> params
            , Pageable pageable
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        long start = System.currentTimeMillis();
        try {

            ApiRequest<ListSearchConditionDto> request = SearchUtils.buildSearch(params, ListSearchConditionDto.class, pageable);
            ObjectDataRes<SchGroup> res = iGroupService.search(request);
            return this.handleSuccess.handleSuccess(start, res);
        } catch (Exception e) {

            return this.handleError.handleError(start, e);
        }
    }

    @PostMapping(CommonConstant.URL_CREATE)
    @Override
    public BaseResponse addGroup(@Valid AddGroupDto dto
            , BindingResult bindingResult
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        long start = System.currentTimeMillis();
        try {

            if(bindingResult.hasErrors()) {

                return this.handleError.handleBindingResult(start, bindingResult);
            }
            addGroupValidator.validate(dto, bindingResult);
        } catch (Exception e) {

            return this.handleError.handleError(start, e);
        }
        return null;
    }
}