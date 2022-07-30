package com.example.daangn.dto;

import com.example.daangn.model.AreaEnum;
import com.example.daangn.model.CategoryEnum;
import com.example.daangn.model.StateEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) //null인 데이터는 json 결과에 나오지 않음
public class PostResultDto {
    private Long id;

    private String title;

    private CategoryEnum category;

    private int price;

    private AreaEnum area;

    private String content;

    private String imageUrl;

    private StateEnum state;

    private LocalDateTime after;

    private Long userId;

    private String nickname;

    private long likeCount;

    public String getAfter() {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = "";
//        return Duration.between(after,now).toDays();

        if(now.getYear() != after.getYear()){
            timestamp = timestamp + (now.getYear()-after.getYear()) + "년전";
        }else if(now.getMonthValue() != after.getMonthValue()){
            timestamp = timestamp + (now.getMonthValue()-after.getMonthValue()) + "달전";
        }else if(now.getDayOfMonth() != after.getDayOfMonth()){
            timestamp = timestamp + (now.getDayOfMonth()-after.getDayOfMonth()) + "일전";
        }else if(now.getHour() != after.getHour()){
            timestamp = timestamp + (now.getHour()-after.getHour()) + "시간전";
        }else if(now.getMinute() != after.getMinute()) {
            timestamp = timestamp + (now.getMinute() - after.getMinute()) + "분전";
        }else {
            timestamp = "방금전";
        }
        return timestamp;
    }
}
