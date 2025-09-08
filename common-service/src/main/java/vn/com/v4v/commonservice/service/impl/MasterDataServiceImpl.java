package vn.com.v4v.commonservice.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.com.v4v.common.AbstractService;
import vn.com.v4v.common.ApiRequest;
import vn.com.v4v.common.ObjectDataRes;
import vn.com.v4v.commonservice.dto.ListSearchConditionDto;
import vn.com.v4v.commonservice.entity.MasterData;
import vn.com.v4v.commonservice.entity.QMasterData;
import vn.com.v4v.commonservice.req.GetMasterDataReq;
import vn.com.v4v.commonservice.service.IMasterDataService;

import java.util.List;

/**
 * Name: MasterDataServiceImpl
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
@Service
public class MasterDataServiceImpl extends AbstractService implements IMasterDataService {

    @Override
    public ObjectDataRes<MasterData> getAllMasterData(ApiRequest<ListSearchConditionDto> request) {

        // Init param
        Pageable pageable = request.getPageable();
        ListSearchConditionDto searchDto = request.getData();
        ObjectDataRes<MasterData> res = new ObjectDataRes<>();
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QMasterData qMasterData = QMasterData.masterData;

        // Do execute
        List<MasterData> masterData = queryFactory
                .select(qMasterData)
                .from(qMasterData)
                .where(
                        (searchDto.getGroupCode() != null ? qMasterData.groupCode.eq(searchDto.getGroupCode()) : null)
                                .or(searchDto.getKindCode() != null ? qMasterData.kindCode.eq(searchDto.getKindCode()) : null)
                )
                .orderBy(qMasterData.groupCode.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Get count
        int count = queryFactory.select(qMasterData).from(qMasterData).fetch().size();

        res.setData(masterData);
        res.setTotal(count);
        return res;
    }

    @Override
    public ObjectDataRes<MasterData> getMasterData(GetMasterDataReq masterDataReq) {

        ObjectDataRes<MasterData> res = new ObjectDataRes<>();
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QMasterData qMasterData = QMasterData.masterData;

        List<MasterData> masterData = queryFactory
                .select(qMasterData)
                .from(qMasterData)
                .where(
                        qMasterData.kindCode.eq(masterDataReq.getKindCode())
                        .and(qMasterData.groupCode.eq(masterDataReq.getGroupCode()))
                )
                .fetch();
        res.setData(masterData);
        res.setTotal(masterData.size());
        return res;
    }
}
