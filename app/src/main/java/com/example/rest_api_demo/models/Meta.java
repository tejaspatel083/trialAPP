
package com.example.rest_api_demo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("server_time")
    @Expose
    public Integer serverTime;
    @SerializedName("server_timezone")
    @Expose
    public String serverTimezone;
    @SerializedName("api_version")
    @Expose
    public Integer apiVersion;
    @SerializedName("execution_time")
    @Expose
    public String executionTime;

}
