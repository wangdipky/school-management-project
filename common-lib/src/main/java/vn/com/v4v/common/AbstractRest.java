package vn.com.v4v.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * Name: AbstractRest
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public class AbstractRest {

    protected HandleSuccess handleSuccess = new HandleSuccess();

    protected HandleError handleError = new HandleError();

    protected final ObjectMapper mapper = new ObjectMapper();

    /**
     * This function uses build param to Object.
     * @param {{@link MultiValueMap}, {@link Class}}
     * @return Generic Class
     * Author: QuangDK
     */
    protected <T> T buildSearch(MultiValueMap<String, String> params, Class<T> returnClass) {

        T result;
        Map<String, String> paramsMap = params.toSingleValueMap();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        result = mapper.convertValue(paramsMap, returnClass);
        return result;
    }

}