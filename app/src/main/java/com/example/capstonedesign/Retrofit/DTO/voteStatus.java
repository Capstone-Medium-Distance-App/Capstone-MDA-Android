package com.example.capstonedesign.Retrofit.DTO;


import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class voteStatus {
    @SerializedName("pVotedUserName")
    private String pVotedUserName;

    @SerializedName("placePname")
    private String placePname;

    @SerializedName("pId")
    private String pId;

    @Override
    public String toString() {
        return "voteStatus{" +
                "pVotedUserName='" + pVotedUserName + '\'' +
                ", placePname='" + placePname + '\'' +
                ", pId='" + pId + '\'' +
                '}';
    }
}
