package com.example.capstonedesign.Retrofit.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDto {
    private Integer scheduleId;
    private String scheduleName;
    private String schedulePlaceName;
    private String schedulePlaceId;
    private String scheduleUserId;
    private String scheduleUserName;
    private String scheduleDate;
    private String scheduleTime;
    private String schedulePlaceArea;
    private String schedulePeopleNum;
    private String scheduleWithUserId;
    private String scheduleWithUserName;

}
