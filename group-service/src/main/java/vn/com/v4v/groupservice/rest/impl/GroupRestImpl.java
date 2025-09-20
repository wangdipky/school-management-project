package vn.com.v4v.groupservice.rest.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.com.v4v.common.AbstractRest;
import vn.com.v4v.common.ApiRequest;
import vn.com.v4v.common.BaseResponse;
import vn.com.v4v.common.ObjectDataRes;
import vn.com.v4v.constant.CommonConstant;
import vn.com.v4v.groupservice.constant.GroupConst;
import vn.com.v4v.groupservice.dto.AddGroupDto;
import vn.com.v4v.groupservice.dto.ListSearchConditionDto;
import vn.com.v4v.groupservice.dto.UpdateGroupDto;
import vn.com.v4v.groupservice.entity.SchGroup;
import vn.com.v4v.groupservice.enums.ExportGroupExcelEnum;
import vn.com.v4v.groupservice.rest.IGroupRest;
import vn.com.v4v.groupservice.service.IGroupService;
import vn.com.v4v.groupservice.validator.AddGroupValidator;
import vn.com.v4v.groupservice.validator.UpdateGroupValidator;
import vn.com.v4v.utils.ExcelUtils;
import vn.com.v4v.utils.SearchUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final UpdateGroupValidator updateGroupValidator;

    @Autowired
    public GroupRestImpl(IGroupService iGroupService, AddGroupValidator addGroupValidator, UpdateGroupValidator updateGroupValidator) {
        this.iGroupService = iGroupService;
        this.addGroupValidator = addGroupValidator;
        this.updateGroupValidator = updateGroupValidator;
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

    @GetMapping(CommonConstant.URL_DETAIL + CommonConstant.URL_PATH_ID)
    @Override
    public BaseResponse detail(Long id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        long start = System.currentTimeMillis();
        try {

            SchGroup schGroup = this.iGroupService.getDetail(id);
            return this.handleSuccess.handleSuccess(start, schGroup);
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

            AddGroupDto res = iGroupService.addGroup(dto);
            return this.handleSuccess.handleSuccess(start, res);
        } catch (Exception e) {

            return this.handleError.handleError(start, e);
        }
    }

    @PutMapping(CommonConstant.URL_UPDATE)
    @Override
    public BaseResponse updateGroup(@Valid UpdateGroupDto dto
            , BindingResult bindingResult
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        long start = System.currentTimeMillis();
        try {

            if(bindingResult.hasErrors()) {

                return this.handleError.handleBindingResult(start, bindingResult);
            }

            updateGroupValidator.validate(dto, bindingResult);
            UpdateGroupDto res = iGroupService.updateGroup(dto);
            return this.handleSuccess.handleSuccess(start, res);
        } catch (Exception e) {
            return this.handleError.handleError(start, e);
        }
    }

    @DeleteMapping(CommonConstant.URL_DELETE + CommonConstant.URL_PATH_ID)
    @Override
    public BaseResponse deleteGroup(Long id
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        long start = System.currentTimeMillis();
        try {

            Long res = iGroupService.deleteGroup(id);
            return this.handleSuccess.handleSuccess(start, res);
        } catch (Exception e) {
            return this.handleError.handleError(start, e);
        }
    }

    @GetMapping(CommonConstant.URL_LIST + CommonConstant.URL_DOWNLOAD)
    @Override
    public void exportExcel(MultiValueMap<String, String> params
            , Pageable pageable
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        try {

            ApiRequest<ListSearchConditionDto> request = SearchUtils.buildSearch(params, ListSearchConditionDto.class, pageable);
            this.iGroupService.exportExcel(request, httpServletResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}