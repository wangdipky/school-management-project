package vn.com.v4v.groupservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Name: AddGroupDto
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 08/09/2025
 * */
@Getter
@Setter
@NoArgsConstructor
public class AddGroupDto {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    private String description;

    private Date createdDate;

    private Long createdBy;

    private Date updatedDate;

    private Long updatedBy;

    private Date deletedDate;

    private Long deletedBy;
}