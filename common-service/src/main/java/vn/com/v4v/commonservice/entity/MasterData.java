package vn.com.v4v.commonservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "master_data", schema = "common_service")
@Data
public class MasterData {

    @Id
    private Long id;

    private String groupCode;

    private String kindCode;

    private String code;

    private String value;

    private Date createDate;

    private Long createdId;
}
