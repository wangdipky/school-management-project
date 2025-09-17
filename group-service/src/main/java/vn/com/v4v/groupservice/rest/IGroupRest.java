package vn.com.v4v.groupservice.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import vn.com.v4v.common.BaseResponse;
import vn.com.v4v.groupservice.dto.AddGroupDto;
import vn.com.v4v.groupservice.dto.UpdateGroupDto;

/**
 * Name: IGroupRest
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 05/09/2025
 * */
public interface IGroupRest {

    /**
     * Get all groups.
     * @param params
     * @param pageable
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    BaseResponse searchGroup(@RequestParam MultiValueMap<String, String> params
            , Pageable pageable
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    /**
     * Get detail a group data by ID.
     * @param id
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    BaseResponse detail(@PathVariable("id") Long id
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    /**
     * Add new group.
     * @param dto
     * @param bindingResult
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    BaseResponse addGroup(@RequestBody AddGroupDto dto
            , BindingResult bindingResult
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    /**
     * Edit a group.
     * @param dto
     * @param bindingResult
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    BaseResponse updateGroup(@RequestBody UpdateGroupDto dto
             , BindingResult bindingResult
             , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    /**
     * Delete a group by ID.
     * @param id
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    BaseResponse deleteGroup(@PathVariable("id") Long id
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    /**
     * Export excel by list group.
     * @param params
     * @param pageable
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    void exportExcel(@RequestParam MultiValueMap<String, String> params
            , Pageable pageable
            , HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;
}