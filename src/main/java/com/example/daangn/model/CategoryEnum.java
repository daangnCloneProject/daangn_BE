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
    PLANET,
    ETC;

    @JsonCreator
    public static CategoryEnum from(String category) {
        category = category.toUpperCase();
        switch (category) {
            case "DIGITAL":
                return CategoryEnum.DIGITAL;
            case "APPLIANCES":
                return CategoryEnum.APPLIANCES;
            case "HOUSEHOLD":
                return CategoryEnum.HOUSEHOLD;
            case "KID":
                return CategoryEnum.KID;
            case "GROCERIES":
                return CategoryEnum.GROCERIES;
            case "SPORT":
                return CategoryEnum.SPORT;
            case "CLOTHES":
                return CategoryEnum.CLOTHES;
            case "INTEREST":
                return CategoryEnum.INTEREST;
            case "BEAUTY":
                return CategoryEnum.BEAUTY;
            case "PET":
                return CategoryEnum.PET;
            case "BOOK":
                return CategoryEnum.BOOK;
            case "PLANET":
                return CategoryEnum.PLANET;
            default:
                return CategoryEnum.ETC;
        }
    }
}
