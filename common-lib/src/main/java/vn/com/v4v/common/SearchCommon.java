package vn.com.v4v.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class SearchCommon {

    public static <T> T buildSearch(MultiValueMap<String, String> params, Class<T> returnClass) {

        T result;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> paramsMap = params.toSingleValueMap();
        result = mapper.convertValue(paramsMap, returnClass);
        return result;
    }
}