package com.example.daangn.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum StateEnum {
    SALE,
    RESERVED,
    DONE;

    @JsonCreator
    public static StateEnum from(String state) {
        return StateEnum.valueOf(state.toUpperCase());
    }
}
