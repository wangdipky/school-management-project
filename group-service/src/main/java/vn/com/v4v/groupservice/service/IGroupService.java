package vn.com.v4v.groupservice.service;

import vn.com.v4v.common.ApiRequest;
import vn.com.v4v.common.ObjectDataRes;
import vn.com.v4v.groupservice.dto.ListSearchConditionDto;
import vn.com.v4v.groupservice.entity.SchGroup;

/**
 * Name: IGroupService
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 05/09/2025
 * */
public interface IGroupService {

    ObjectDataRes<SchGroup> search(ApiRequest<ListSearchConditionDto> request);
}
