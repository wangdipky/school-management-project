package vn.com.v4v.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * Name: ObjectDataRes
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public class ObjectDataRes<T> {

    @Getter
    @Setter
    private Collection<T> data;

    @Getter
    @Setter
    private int total;
}