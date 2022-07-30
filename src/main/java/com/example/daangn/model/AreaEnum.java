package com.example.daangn.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum AreaEnum {
    JUNGGU,
    BUKGU,
    DONGGU,
    DALSEONGGUN,
    DALSEOGU,
    SEOGU,
    NAMGU,
    SUSEONGGU;

    @JsonCreator
    public static AreaEnum from(String area) {
        area = area.toUpperCase();
        switch (area) {
            case "BUKGU":
                return AreaEnum.BUKGU;
            case "DONGGU":
                return AreaEnum.DONGGU;
            case "DALSEONGGUN":
                return AreaEnum.DALSEONGGUN;
            case "DALSEOGU":
                return AreaEnum.DALSEOGU;
            case "SEOGU":
                return AreaEnum.SEOGU;
            case "NAMGU":
                return AreaEnum.NAMGU;
            case "SUSEONGGU":
                return AreaEnum.SUSEONGGU;
            default:
                return AreaEnum.JUNGGU;
        }
    }
}