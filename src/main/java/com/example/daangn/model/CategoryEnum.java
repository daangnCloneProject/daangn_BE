package com.example.daangn.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum CategoryEnum {
    DIGITAL,
    APPLIANCES,
    HOUSEHOLD,
    KID,
    GROCERIES,
    SPORT,
    CLOTHES,
    INTEREST,
    BEAUTY,
    PET,
    BOOK,
    PLANT,
    ETC;

    @JsonCreator
    public static CategoryEnum from(String category) {
        return CategoryEnum.valueOf(category.toUpperCase());
    }
}
