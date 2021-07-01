package com.girlsclothremover_bodyscanner2020.audreyprank.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdsPriorityResponse {

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
        @SerializedName("application")
        public Application application;
        @Expose
        @SerializedName("facebook_reward")
        public String facebook_reward;
        @Expose
        @SerializedName("google_reward")
        public String google_reward;
        @Expose
        @SerializedName("facebook_native_banner")
        public String facebook_native_banner;
        @Expose
        @SerializedName("google_native_banner")
        public String google_native_banner;
        @Expose
        @SerializedName("facebook_industrial")
        public String facebook_industrial;
        @Expose
        @SerializedName("google_industrial")
        public String google_industrial;
        @Expose
        @SerializedName("facebook_banner")
        public String facebook_banner;
        @Expose
        @SerializedName("google_banner")
        public String google_banner;
        @Expose
        @SerializedName("intertitial_status")
        public String intertitial_status;
        @Expose
        @SerializedName("priority")
        public String priority;
        @Expose
        @SerializedName("app_id")
        public int app_id;
        @Expose
        @SerializedName("id")
        public int id;
    }

    public static class Application {
        @Expose
        @SerializedName("app_type")
        public String app_type;
        @Expose
        @SerializedName("facebook_app_id")
        public String facebook_app_id;
        @Expose
        @SerializedName("google_app_id")
        public String google_app_id;
        @Expose
        @SerializedName("app_url")
        public String app_url;
        @Expose
        @SerializedName("app_name")
        public String app_name;
        @Expose
        @SerializedName("id")
        public int id;
    }
}
