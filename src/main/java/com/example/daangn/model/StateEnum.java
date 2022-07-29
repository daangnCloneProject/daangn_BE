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
        state = state.toUpperCase();
        switch (state) {
            case "RESERVED":
                return StateEnum.RESERVED;
            case "DONE":
                return StateEnum.DONE;
            default:
                return StateEnum.SALE;
        }
    }
}
