package vn.com.v4v.annotation;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Name: NumericToBooleanConverter
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 04/08/2025
 * */
@Converter
public class NumericToBooleanConverter implements AttributeConverter <Boolean, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? 1 : 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        return dbData != null && dbData == 1;
    }
}
