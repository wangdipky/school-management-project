package vn.com.v4v.commonservice.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import vn.com.v4v.common.ObjectDataRes;
import vn.com.v4v.commonservice.entity.MasterData;
import vn.com.v4v.commonservice.req.GetMasterDataReq;

import java.util.List;

/**
 * Name: IMasterDataService
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public interface IMasterDataService {

    ObjectDataRes<MasterData> getAllMasterData(Pageable pageable);

    ObjectDataRes<MasterData> getMasterData(GetMasterDataReq masterDataReq);
}
