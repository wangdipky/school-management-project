package vn.com.v4v.identityservice.service;

import vn.com.v4v.identityservice.entity.SchAccount;
import vn.com.v4v.identityservice.entity.SchAccountGroup;
import vn.com.v4v.identityservice.entity.SchPwd;

import java.util.Date;
import java.util.List;

/**
 * Name: IUserService
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 03/08/2025
 * */
public interface IUserService {

    SchAccount loadUserByUsername(String username);

    SchPwd getPasswordByUserId(Long userId);

    List<Long> getListGroupIds(Long userId);

    List<Long> getListRoleIds(List<Long> groupIds);

    List<Long> getListFunctionIds(List<Long> roleIds);

    List<String> getListRoles(List<Long> functionIds);

    Long updateStatusWrong(int countWrong, Boolean isWrong, Long userId, Date lastWrong, String refreshToken);
}