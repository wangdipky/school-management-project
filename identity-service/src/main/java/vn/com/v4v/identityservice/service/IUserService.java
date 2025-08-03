package vn.com.v4v.identityservice.service;

import vn.com.v4v.identityservice.entity.SchAccount;
import vn.com.v4v.identityservice.entity.SchAccountGroup;

import java.util.List;

/**
 * Name: IUserService
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 03/08/2025
 * */
public interface IUserService {

    SchAccount loadUserByUsername(String username);

    List<Long> getListGroupIds(Long userId);

    List<Long> getListRoleIds(List<Long> groupIds);

    List<Long> getListFunctionIds(List<Long> roleIds);

    List<String> getListRoles(List<Long> functionIds);
}