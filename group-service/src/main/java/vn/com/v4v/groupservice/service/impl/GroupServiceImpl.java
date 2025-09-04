package vn.com.v4v.groupservice.service.impl;

import org.springframework.stereotype.Service;
import vn.com.v4v.common.ApiRequest;
import vn.com.v4v.common.ObjectDataRes;
import vn.com.v4v.groupservice.dto.ListSearchConditionDto;
import vn.com.v4v.groupservice.entity.SchGroup;
import vn.com.v4v.groupservice.service.IGroupService;

/**
 * Name: GroupServiceImpl
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 05/09/2025
 * */
@Service
public class GroupServiceImpl implements IGroupService {

    @Override
    public ObjectDataRes<SchGroup> search(ApiRequest<ListSearchConditionDto> request) {

        
        return null;
    }
}