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
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
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
import vn.com.v4v.groupservice.enums.ExportGroupExcelEnum;
import vn.com.v4v.groupservice.rest.IGroupRest;
import vn.com.v4v.groupservice.service.IGroupService;
import vn.com.v4v.groupservice.validator.AddGroupValidator;
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
@Log4j2
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

    @GetMapping(CommonConstant.URL_LIST + "/download")
    @Override
    public void exportExcel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users" + ".xlsx";
        httpServletResponse.setHeader(headerKey, headerValue);
        List<SchGroup> listGroups = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            SchGroup schGroup = new SchGroup();
            schGroup.setId((long)i+1);
            schGroup.setName(i+"_NAME");
            schGroup.setCode("CODE_"+i);
            schGroup.setDescription(i+"_DESC");
            listGroups.add(schGroup);
        }
        exportExcel(listGroups, httpServletResponse, "GROUP_MANAGEMENT", ExportGroupExcelEnum.class);
    }

    private <T extends Enum<T>> void exportExcel(List<?> listData, HttpServletResponse response, String templateName, Class<T> enumClass) throws Exception {

        // Init data
        Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);


        XSSFWorkbook workbook = new XSSFWorkbook(ResourceUtils.getFile("classpath:template/excel/"+ templateName +".xlsx"));
        XSSFSheet sheet = workbook.getSheetAt(0);
        int indexRow = 4;

        for(Object item : listData) {

            XSSFRow row = sheet.createRow(indexRow++);

            // Init row from ENUM
            String toJson = gson.toJson(item);
            Map<String, Object> toMap = mapper.readValue(toJson, Map.class);
            int index = 0;

            T[] values = enumClass.getEnumConstants();
            for(T value : values) {
                System.out.println(toCamelCase(value.toString()));
            }
            int a = 1;
        }

        // Export
//        ServletOutputStream ops = response.getOutputStream();
//        workbook.write(ops);
//        workbook.close();
//        ops.close();
//        response.flushBuffer();
    }

    private String toCamelCase(String str) {

        StringBuilder result = new StringBuilder("");
        if(!str.contains("_")) {

            result.append(str.toLowerCase());
        } else {

            String[] strs = str.split("");
            for(int i = 0; i < strs.length; i++) {

                if(i > 0 && strs[i - 1] != null && strs[i - 1].equals("_")) {

                    result.append(strs[i]);
                } else if (!strs[i].equals("_")) {

                    result.append(strs[i].toLowerCase());
                }
            }
        }
        return result.toString();
    }

}