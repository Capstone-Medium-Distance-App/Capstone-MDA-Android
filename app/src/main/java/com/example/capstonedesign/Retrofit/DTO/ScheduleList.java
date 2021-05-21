package com.example.capstonedesign.Retrofit.DTO;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleList {
    private int userId;
    private ArrayList<ScheduleDto> list;

}
