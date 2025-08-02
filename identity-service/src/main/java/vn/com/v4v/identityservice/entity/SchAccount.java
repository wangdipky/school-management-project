package vn.com.v4v.identityservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.v4v.db.AuditCommonDto;

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
@Table(name = "sch_account", schema = "auth_service")
public class SchAccount extends AuditCommonDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_service.seq_sch_account")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "accoun_type_id")
    private Long accountTypeId;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "is_notified")
    private Integer isNotified;

    @Column(name = "is_locked")
    private Integer isLocked;
}