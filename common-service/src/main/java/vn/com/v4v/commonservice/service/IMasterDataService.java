package vn.com.v4v.commonservice.service;

import org.springframework.http.ResponseEntity;
import vn.com.v4v.commonservice.dto.GetAllMasterDataDto;
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

    ResponseEntity<List<MasterData>> getAllMasterData();

    ResponseEntity<List<MasterData>> getMasterData(GetMasterDataReq masterDataReq);
}
