package vn.com.v4v.identityservice.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.com.v4v.common.AbstractService;
import vn.com.v4v.identityservice.entity.*;
import vn.com.v4v.identityservice.service.IUserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Name: UserServiceImpl
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 03/08/2025
 * */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl extends AbstractService implements IUserService {

    @Override
    public SchAccount loadUserByUsername(String username) {

        // Init param and variable
        SchAccount schAccount = null;
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchAccount qSchAccount = QSchAccount.schAccount;

        // Check query factory
        if(null != queryFactory) {

            schAccount = queryFactory
                    .select(qSchAccount)
                    .from(qSchAccount)
                    .where(qSchAccount.username.eq(username))
                    .fetchOne();
        }
        return schAccount;
    }

    @Override
    public SchPwd getPasswordByUserId(Long userId) {

        // Init param and variable
        SchPwd schPwd = null;
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchPwd qSchPwd = QSchPwd.schPwd;

        // Check null and do Execution
        if(null != queryFactory) {

            schPwd = queryFactory.select(qSchPwd)
                    .select(qSchPwd)
                    .from(qSchPwd)
                    .where(qSchPwd.accountId.eq(userId))
                    .fetchOne();
        }
        return schPwd;
    }

    @Override
    public List<Long> getListGroupIds(Long userId) {

        // Init param and variable
        List<Long> listGroupIds = new ArrayList<>();
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchAccountGroup qSchAccountGroup = QSchAccountGroup.schAccountGroup;

        // Check query factory
        if(null != queryFactory) {

            listGroupIds.addAll(
                    queryFactory.select(qSchAccountGroup)
                            .from(qSchAccountGroup)
                            .where(qSchAccountGroup.accountId.eq(userId)
                                    .and(qSchAccountGroup.isActive.eq(true)))
                            .fetch()
                            .stream()
                            .map(SchAccountGroup::getGroupId)
                            .toList());
        }
        return listGroupIds;
    }

    @Override
    public List<Long> getListRoleIds(List<Long> groupIds) {

        // Init param and variable
        List<Long> listRoleIds = new ArrayList<>();
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchRoleForGroup qSchRoleForGroup = QSchRoleForGroup.schRoleForGroup;

        // Check query factory
        if(null != queryFactory) {

            listRoleIds.addAll(
                    queryFactory.select(qSchRoleForGroup)
                            .from(qSchRoleForGroup)
                            .where(qSchRoleForGroup.groupId.in(groupIds)
                                    .and(qSchRoleForGroup.isActive.eq(true)))
                            .fetch()
                            .stream()
                            .map(SchRoleForGroup::getRoleId)
                            .toList());
        }
        return listRoleIds;
    }

    @Override
    public List<Long> getListFunctionIds(List<Long> roleIds) {

        // Init param and variable
        List<Long> listFunctionIds = new ArrayList<>();
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchRoleForFunction qSchRoleForFunction = QSchRoleForFunction.schRoleForFunction;

        // Check query factory
        if(null != queryFactory) {
            listFunctionIds.addAll(
                    queryFactory.select(qSchRoleForFunction)
                            .from(qSchRoleForFunction)
                            .where(qSchRoleForFunction.roleId.in(roleIds)
                                    .and(qSchRoleForFunction.isActive.eq(true)))
                            .fetch()
                            .stream()
                            .map(SchRoleForFunction::getFunctionId)
                            .toList());
        }

        return listFunctionIds;
    }

    @Override
    public List<String> getListRoles(List<Long> functionIds) {

        // Init param and variable
        List<String> listRoles = new ArrayList<>();
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchFunction qSchFunction = QSchFunction.schFunction;

        // Check factory
        if(null != queryFactory) {
            listRoles.addAll(
                    queryFactory.select(qSchFunction)
                            .from(qSchFunction)
                            .where(qSchFunction.id.in(functionIds))
                            .fetch()
                            .stream()
                            .map(SchFunction::getCode)
                            .toList());
        }

        return listRoles;
    }

    @Override
    public Long updateStatusWrong(int countWrong, Boolean isWrong, Long userId, Date lastWrong, String refreshToken) {

        // Init param and variable
        JPAQueryFactory queryFactory = this.getQueryFactory();
        QSchPwd qSchPwd = QSchPwd.schPwd;
        long modify = 0;

        // Check factory
        if(null != queryFactory) {

            modify = queryFactory.update(qSchPwd)
                    .set(qSchPwd.countWrong, countWrong)
                    .set(qSchPwd.isLock, isWrong)
                    .set(qSchPwd.lastWrong, lastWrong)
                    .set(qSchPwd.refreshToken, refreshToken)
                    .where(qSchPwd.accountId.eq(userId))
                    .execute();
        }
        return modify;
    }

}