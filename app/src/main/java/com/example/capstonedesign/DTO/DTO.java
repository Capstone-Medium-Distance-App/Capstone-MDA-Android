package com.example.capstonedesign.DTO;

import com.google.gson.annotations.SerializedName;

public class DTO {
    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    @Override
    public String toString() {
        return "DTO{" +
                "title='" + title + '\'' +
                "content='"+content+'\'' +
                '}';
    }
}
