package vn.com.v4v.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "system_key", schema = "common_service")
public class SystemKey {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "KEY")
    private String key;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private Long createdBy;
}
