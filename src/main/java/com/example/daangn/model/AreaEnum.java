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
        return AreaEnum.valueOf(area.toUpperCase());
    }
}