package vn.com.v4v.groupservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExportGroupExcelEnum {

    ID("id"),
    CODE("code"),
    NAME("name"),
    DESCRIPTION("description"),
    USER_NAME("test")
    ;
    private String key;
}