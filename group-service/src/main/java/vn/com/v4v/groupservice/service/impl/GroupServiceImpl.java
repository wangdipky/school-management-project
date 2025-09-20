package vn.com.v4v.groupservice.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.v4v.common.AbstractService;
import vn.com.v4v.common.ApiRequest;
import vn.com.v4v.common.ObjectDataRes;
import vn.com.v4v.groupservice.dto.AddGroupDto;
import vn.com.v4v.groupservice.dto.ListSearchConditionDto;
import vn.com.v4v.groupservice.dto.UpdateGroupDto;
import vn.com.v4v.groupservice.entity.QSchGroup;
import vn.com.v4v.groupservice.entity.SchGroup;
import vn.com.v4v.groupservice.enums.ExportGroupExcelEnum;
import vn.com.v4v.groupservice.service.IGroupService;
import vn.com.v4v.utils.ExcelUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Name: GroupServiceImpl
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 05/09/2025
 * */
@Transactional(rollbackFor = Exception.class)
@Log4j2
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
        BooleanBuilder predicate = new BooleanBuilder();

        // Search condition
        if(searchCondition.getCode() != null) {
            predicate.or(qSchGroup.code.eq(searchCondition.getCode()));
        }
        if(searchCondition.getName() != null) {
            predicate.or(qSchGroup.name.eq(searchCondition.getName()));
        }

        // Get data
        List<SchGroup> listGroups = queryFactory
                .select(qSchGroup)
                .from(qSchGroup)
                .where(predicate)
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
    public SchGroup getDetail(Long id) {

        // Init param
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchGroup qSchGroup = QSchGroup.schGroup;

        // Get data
        SchGroup schGroup = queryFactory.select(qSchGroup)
                .from(qSchGroup)
                .where(qSchGroup.id.eq(id))
                .fetchOne();
        return schGroup;
    }

    @Override
    public void exportExcel(ApiRequest<ListSearchConditionDto> request, HttpServletResponse response) throws Exception {

        // Init param
        JPAQueryFactory queryFactory = this.getQueryFactory();
        ListSearchConditionDto searchCondition = request.getData();
        QSchGroup qSchGroup = QSchGroup.schGroup;
        BooleanBuilder predicate = new BooleanBuilder();

        // Search condition
        if(searchCondition.getCode() != null) {
            predicate.or(qSchGroup.code.eq(searchCondition.getCode()));
        }
        if(searchCondition.getName() != null) {
            predicate.or(qSchGroup.name.eq(searchCondition.getName()));
        }

        // Get data
        List<SchGroup> listGroups = queryFactory
                .select(qSchGroup)
                .from(qSchGroup)
                .where(predicate)
                .fetch();

        // Export
        ExcelUtils.exportExcelByEnum(listGroups, response, "GROUP_MANAGEMENT", ExportGroupExcelEnum.class);
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
        long result = queryFactory.insert(qSchGroup)
                .columns(qSchGroup.name, qSchGroup.code, qSchGroup.description, qSchGroup.createdBy, qSchGroup.createdDate)
                .values(schGroup.getName(), schGroup.getCode(), schGroup.getDescription(), dto.getCreatedBy(), dto.getCreatedDate())
                .execute();

        if(result == 1) {
            schGroup = this.returnNewDataByCode(dto.getCode());
            dto = mapper.convertValue(schGroup, AddGroupDto.class);
            log.info("[{}]-[GROUP]-[CREATE] Success, data: {}", LocalDateTime.now(), dto.toString());
        }

        return dto;
    }

    @Override
    public UpdateGroupDto updateGroup(UpdateGroupDto dto) {

        // Init param
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchGroup qSchGroup = QSchGroup.schGroup;

        // Set data
        dto.setUpdatedDate(new Date());
        dto.setUpdatedBy(dto.getUpdatedBy());

        // Update
        queryFactory.update(qSchGroup)
                .set(qSchGroup.code, dto.getCode())
                .set(qSchGroup.name, dto.getName())
                .set(qSchGroup.description, dto.getDescription())
                .where(qSchGroup.id.eq(dto.getId()))
                .execute();

        return dto;
    }

    @Override
    public Long deleteGroup(Long id) {

        // Init param
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchGroup qSchGroup = QSchGroup.schGroup;

        // Delete
        queryFactory.delete(qSchGroup)
                .where(qSchGroup.id.eq(id))
                .execute();
        return id;
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
                .fetch().isEmpty() ? 0 : 1;
    }

    private SchGroup returnNewDataByCode(String code) {

        // Init param
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchGroup qSchGroup = QSchGroup.schGroup;

        SchGroup schGroup = queryFactory.select(qSchGroup)
                .from(qSchGroup)
                .where(qSchGroup.code.eq(code))
                .fetchOne();

        return schGroup;
    }

}