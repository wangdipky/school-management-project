package vn.com.v4v.identityservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.NumericBooleanConverter;
import vn.com.v4v.db.AuditCreateDto;

import java.util.Date;

/**
 * Name: SchPwd
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 04/08/2025
 * */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sch_pwd", schema = "auth_service")
public class SchPwd {

    @Id
    @Column(name = "account_id", precision = 20, scale = 0)
    private Long accountId;

    @Column(name = "password", length = 250)
    private String password;

    @Column(name = "count_wrong", precision = 1, scale = 0)
    private Integer countWrong;

    @Column(name = "last_wrong")
    private Date lastWrong;

    @Column(name = "is_lock")
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean isLock;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_date")
    private Date createdDate;
}