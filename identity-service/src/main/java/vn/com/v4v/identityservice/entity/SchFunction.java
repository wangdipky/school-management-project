package vn.com.v4v.identityservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.v4v.db.AuditCommonDto;

/**
 * Name: SchFunction
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 03/08/2025
 * */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sch_function", schema = "auth_service")
public class SchFunction extends AuditCommonDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_service.seq_sch_function")
    @Column(name = "id")
    private Long id;

    @Column(name = "code", length = 150)
    private String code;

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "description", length = 500)
    private String description;
}
