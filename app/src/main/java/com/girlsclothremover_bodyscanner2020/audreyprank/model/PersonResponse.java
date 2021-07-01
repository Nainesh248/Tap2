package com.girlsclothremover_bodyscanner2020.audreyprank.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonResponse {
    @Expose
    @SerializedName("message")
    public String message;
    @Expose
    @SerializedName("data")
    public List<Data> data;
    @Expose
    @SerializedName("status")
    public String status;

    public static class Data {
        @Expose
        @SerializedName("updated_at")
        public String updated_at;
        @Expose
        @SerializedName("created_at")
        public String created_at;
        @Expose
        @SerializedName("image2")
        public String image2;
        @Expose
        @SerializedName("image1")
        public String image1;
        @Expose
        @SerializedName("title")
        public String title;
        @Expose
        @SerializedName("app_id")
        public int app_id;
        @Expose
        @SerializedName("id")
        public int id;
    }
}
