package vn.com.v4v.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import vn.com.v4v.common.ApiRequest;

import java.util.Map;

/**
 * Name: SearchUtils
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 04/09/2025
 * */
public class SearchUtils {

    public static <T> ApiRequest<T> buildSearch(MultiValueMap<String, String> params, Class<T> dtoClass, Pageable pageable) {

        // Init var
        ObjectMapper mapper = new ObjectMapper();
        ApiRequest<T> apiRequest = new ApiRequest<>();
        Gson gson = new Gson();

        // Convert param to map
        Map<String, String> paramsMap = params.toSingleValueMap();

        // Convert map to Dto
        String json = gson.toJson(paramsMap);
        T dto = gson.fromJson(json, dtoClass);

        // Handle data
        apiRequest.setData(dto);
        apiRequest.setPageable(pageable);
        return apiRequest;
    }

}