package vn.com.v4v.identityservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.v4v.annotation.NumericToBooleanConverter;

import java.util.Date;

/**
 * Name: SchRoleForGroup
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 03/08/2025
 * */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sch_role_for_group", schema = "auth_service")
public class SchRoleForGroup {

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Id
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "is_active")
    @Convert(converter = NumericToBooleanConverter.class)
    private Boolean isActive;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by")
    private Long createdBy;
}