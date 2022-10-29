package com.store.MyOnlineStore.domain.entities;


import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

public enum Type {
    WRITING("Writing"),
    READING("Reading"),
    CARRY("Carry"),
    UNKNOWN_TYPE("Unknown type");

    private final String description;

    Type (String description){
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    public static Type from(String description) {
        if (StringUtils.isEmpty(description)) {
            throw new IllegalArgumentException("In Type Enum: NULL OR EMPTY STRING");
        }

        for (Type type : values()){
            if (type.description.equals(description)){
                return type;
            }
        }
        throw new IllegalArgumentException("In Type Enum: No type found for description" + description);
    }
}
