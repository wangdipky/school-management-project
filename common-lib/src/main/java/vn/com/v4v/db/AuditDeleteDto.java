package vn.com.v4v.db;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Name: AuditDeleteDto
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 02/08/2025
 * */
@Getter
@Setter
public class AuditDeleteDto extends AuditUpdateDto {

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "deleted_date")
    private Date deletedDate;
}