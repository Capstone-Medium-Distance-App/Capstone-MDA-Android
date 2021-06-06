package com.example.capstonedesign.Retrofit.DTO;


import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class voteStatus {
    @SerializedName("pvotedUserName")
    private String pvotedUserName;

    @SerializedName("placePname")
    private String placePname;

    @SerializedName("pid")
    private int pid;

    @Override
    public String toString() {
        return "voteStatus{" +
                "pVotedUserName='" + pvotedUserName + '\'' +
                ", placePname='" + placePname + '\'' +
                ", pId='" + pid + '\'' +
                '}';
    }
}
