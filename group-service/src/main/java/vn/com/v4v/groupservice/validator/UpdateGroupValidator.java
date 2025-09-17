package vn.com.v4v.groupservice.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.com.v4v.exception.DetailException;
import vn.com.v4v.groupservice.dto.AddGroupDto;
import vn.com.v4v.groupservice.dto.UpdateGroupDto;
import vn.com.v4v.groupservice.service.IGroupService;

/**
 * Name: UpdateGroupValidator
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 17/09/2025
 * */
@Component
public class UpdateGroupValidator implements Validator {

    private final IGroupService iGroupService;

    @Autowired
    public UpdateGroupValidator(IGroupService iGroupService) {
        this.iGroupService = iGroupService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}