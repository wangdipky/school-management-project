package vn.com.v4v.groupservice.service.impl;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.com.v4v.common.AbstractService;
import vn.com.v4v.common.ApiRequest;
import vn.com.v4v.common.ObjectDataRes;
import vn.com.v4v.groupservice.dto.AddGroupDto;
import vn.com.v4v.groupservice.dto.ListSearchConditionDto;
import vn.com.v4v.groupservice.entity.QSchGroup;
import vn.com.v4v.groupservice.entity.SchGroup;
import vn.com.v4v.groupservice.service.IGroupService;

import java.util.Date;
import java.util.List;

/**
 * Name: GroupServiceImpl
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 05/09/2025
 * */
@Service
public class GroupServiceImpl extends AbstractService implements IGroupService {

    @Override
    public ObjectDataRes<SchGroup> search(ApiRequest<ListSearchConditionDto> request) {

        // Init param
        Pageable pageable = request.getPageable();
        JPAQueryFactory queryFactory = this.getQueryFactory();
        ListSearchConditionDto searchCondition = request.getData();
        QSchGroup qSchGroup = QSchGroup.schGroup;
        ObjectDataRes<SchGroup> objectDataRes = new ObjectDataRes<>();

        // Get data
        List<SchGroup> listGroups = queryFactory
                .select(qSchGroup)
                .from(qSchGroup)
                .where(
                        (searchCondition.getCode() != null ? qSchGroup.code.eq(searchCondition.getCode()) : null)
                                .or(searchCondition.getName() != null ? qSchGroup.name.eq(searchCondition.getName()) : null)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Get count
        int count = queryFactory.select(qSchGroup).from(qSchGroup).fetch().size();

        // set data
        objectDataRes.setData(listGroups);
        objectDataRes.setTotal(count);
        return objectDataRes;
    }

    @Override
    public AddGroupDto addGroup(AddGroupDto dto) {

        // Init param
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchGroup qSchGroup = QSchGroup.schGroup;

        // Set new data
        dto.setCreatedDate(new Date());
        dto.setCreatedBy(dto.getCreatedBy());

        // Convert
        SchGroup schGroup = mapper.convertValue(dto, SchGroup.class);

        //
        queryFactory.insert(qSchGroup)
                .set(qSchGroup.code, schGroup.getCode())
                .set(qSchGroup.name, schGroup.getName())
                .set(qSchGroup.description, schGroup.getDescription())
                .set(qSchGroup.createdDate, schGroup.getCreatedDate())
                .set(qSchGroup.createdBy, schGroup.getCreatedBy())
                .execute();

        return mapper.convertValue(schGroup, AddGroupDto.class);
    }

    @Override
    public int checkDuplicateCode(String code) {

        // Init param
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchGroup qSchGroup = QSchGroup.schGroup;

        return queryFactory
                .select(qSchGroup)
                .from(qSchGroup)
                .where(qSchGroup.code.eq(code))
                .limit(1)
                .fetch() != null ? 1 : 0;
    }

}