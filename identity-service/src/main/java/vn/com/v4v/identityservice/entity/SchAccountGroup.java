package vn.com.v4v.identityservice.entity;

import com.thoughtworks.xstream.converters.basic.BooleanConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.v4v.annotation.NumericToBooleanConverter;

import java.util.Date;

/**
 * Name: SchAccount
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 02/08/2025
 * */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sch_account_group", schema = "auth_service")
public class SchAccountGroup {

    @Id
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Id
    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "is_active")
    @Convert(converter = NumericToBooleanConverter.class)
    private Boolean isActive;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by")
    private Long createdBy;
}