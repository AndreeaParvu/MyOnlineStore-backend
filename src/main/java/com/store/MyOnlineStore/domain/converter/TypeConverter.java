package com.store.MyOnlineStore.domain.converter;

import com.store.MyOnlineStore.domain.entities.Type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type type) {
        return type.getDescription();
    }

    @Override
    public Type convertToEntityAttribute(String description) {
        return Type.from(description);
    }
}
