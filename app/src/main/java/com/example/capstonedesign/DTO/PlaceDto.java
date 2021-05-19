package com.example.capstonedesign.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceDto {
    private Integer placeId;
    private String placeType;
    private String placeName;
    private String placeArea;
    private String placeDescription;
    private String placeImgUrl;
    private String placeCategory;

    @Builder
    public PlaceDto(Integer placeId, String placeType, String placeName, String placeArea, String placeDescription,
                    String placeImgUrl, String placeCategory) {

        this.placeId = placeId;
        this.placeType = placeType;
        this.placeName = placeName;
        this.placeArea = placeArea;
        this.placeDescription = placeDescription;
        this.placeImgUrl = placeImgUrl;
        this.placeCategory = placeCategory;

    }

}
