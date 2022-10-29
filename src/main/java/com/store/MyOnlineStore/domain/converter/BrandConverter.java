package com.store.MyOnlineStore.domain.converter;

import com.store.MyOnlineStore.domain.entities.Brand;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BrandConverter implements AttributeConverter<Brand, String> {
    @Override
    public String convertToDatabaseColumn(Brand brand) {
        return brand.getDescription();
    }

    @Override
    public Brand convertToEntityAttribute(String description) {
        return Brand.from(description);
    }
}
