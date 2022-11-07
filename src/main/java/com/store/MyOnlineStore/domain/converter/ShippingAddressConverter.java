package com.store.MyOnlineStore.domain.converter;

import com.store.MyOnlineStore.domain.entities.OrderAggregate.ShippingAddress;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ShippingAddressConverter implements AttributeConverter<ShippingAddress, String> {
    @Override
    public String convertToDatabaseColumn(ShippingAddress shippingAddress) {
        return shippingAddress.getAddress1()
                .concat("|")
                .concat(shippingAddress.getAddress2())
                .concat("|")
                .concat(shippingAddress.getCountry())
                .concat("|")
                .concat(shippingAddress.getCity())
                .concat("|")
                .concat(shippingAddress.getZipCode());
    }

    @Override
    public ShippingAddress convertToEntityAttribute(String s) {
        String[] splitAddressData = s.split("|");

        ShippingAddress shippingAddress = new ShippingAddress();

        switch (splitAddressData.length) {
            case 1 :
                shippingAddress.setAddress1(splitAddressData[0]);
                break;
            case 2:
                shippingAddress.setAddress1(splitAddressData[0]);
                shippingAddress.setAddress2(splitAddressData[1]);
                break;
            case 3:
                shippingAddress.setAddress1(splitAddressData[0]);
                shippingAddress.setAddress2(splitAddressData[1]);
                shippingAddress.setCountry(splitAddressData[2]);
                break;
            default:

        }

        return shippingAddress;
    }
}
