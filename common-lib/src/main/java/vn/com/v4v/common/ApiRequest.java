package vn.com.v4v.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

/**
 * Name: ApiRequest
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 04/09/2025
 * */
@Getter
@Setter
@NoArgsConstructor
public class ApiRequest<T> {

    private T data;

    private Pageable pageable;
}