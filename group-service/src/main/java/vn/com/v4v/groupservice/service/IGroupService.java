package vn.com.v4v.groupservice.service;

import jakarta.servlet.http.HttpServletResponse;
import vn.com.v4v.common.ApiRequest;
import vn.com.v4v.common.ObjectDataRes;
import vn.com.v4v.groupservice.dto.AddGroupDto;
import vn.com.v4v.groupservice.dto.ListSearchConditionDto;
import vn.com.v4v.groupservice.dto.UpdateGroupDto;
import vn.com.v4v.groupservice.entity.SchGroup;

/**
 * Name: IGroupService
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 05/09/2025
 * */
public interface IGroupService {

    ObjectDataRes<SchGroup> search(ApiRequest<ListSearchConditionDto> request);

    SchGroup getDetail(Long id);

    void exportExcel(ApiRequest<ListSearchConditionDto> request, HttpServletResponse response) throws Exception;

    AddGroupDto addGroup(AddGroupDto dto);

    UpdateGroupDto updateGroup(UpdateGroupDto dto);

    Long deleteGroup(Long id);

    int checkDuplicateCode(String code);
}
