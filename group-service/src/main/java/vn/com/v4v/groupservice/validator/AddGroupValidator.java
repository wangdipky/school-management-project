package vn.com.v4v.groupservice.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.com.v4v.exception.DetailException;
import vn.com.v4v.groupservice.dto.AddGroupDto;
import vn.com.v4v.groupservice.service.IGroupService;

/**
 * Name: AddGroupValidator
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 08/09/2025
 * */
@Component
public class AddGroupValidator implements Validator {

    private final IGroupService iGroupService;

    @Autowired
    public AddGroupValidator(IGroupService iGroupService) {
        this.iGroupService = iGroupService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        AddGroupDto addGroupDto = (AddGroupDto) target;

        // Check code
        long countByCode = iGroupService.checkDuplicateCode(addGroupDto.getCode());
        if (countByCode > 0) {

            throw new DetailException("Code is duplicated");
        }
    }
}