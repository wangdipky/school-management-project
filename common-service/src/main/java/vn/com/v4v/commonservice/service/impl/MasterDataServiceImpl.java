package vn.com.v4v.commonservice.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.v4v.common.AbstractService;
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
    public ResponseEntity<List<MasterData>> getAllMasterData() {

        // Init param
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QMasterData qMasterData = QMasterData.masterData;

        // Do execute
        List<MasterData> masterData = queryFactory
                .select(qMasterData)
                .from(qMasterData)
                .orderBy(qMasterData.groupCode.asc())
                .fetch();

        return new ResponseEntity<>(masterData, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MasterData>> getMasterData(GetMasterDataReq masterDataReq) {

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
        return new ResponseEntity<>(masterData, HttpStatus.OK);
    }
}
