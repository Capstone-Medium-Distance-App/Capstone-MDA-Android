package com.example.capstonedesign.Retrofit.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class locFin {
    private String schName;
    private String schPlaceName;
    private String schPlaceArea;
    private String schWIthUserName;

    @Override
    public String toString() {
        return "locFin{" +
                "schName='" + schName + '\'' +
                ", schPlaceName='" + schPlaceName + '\'' +
                ", schPlaceArea='" + schPlaceArea + '\'' +
                ", schWIthUserName='" + schWIthUserName + '\'' +
                '}';
    }
}
