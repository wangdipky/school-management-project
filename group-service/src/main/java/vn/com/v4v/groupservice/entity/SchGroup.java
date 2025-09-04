package vn.com.v4v.groupservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Name: SchGroup
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 03/09/2025
 * */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sch_group", schema = "auth_service")
public class SchGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_service.seq_sch_group")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "deleted_date")
    private Date deletedDate;

    @Column(name = "deleted_by")
    private Long deletedBy;
}