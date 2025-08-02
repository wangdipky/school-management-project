package vn.com.v4v.db;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Name: AuditUpdateDto
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 02/08/2025
 * */
@Getter
@Setter
public class AuditUpdateDto extends AuditCreateDto {

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;
}