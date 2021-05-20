package com.example.capstonedesign.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceDto {
    private int placeId;
    private String placeType;
    private String placeName;
    private String placeArea;
    private String placeDescription;
    private String placeImgUrl;
    private String placeCategory;

}