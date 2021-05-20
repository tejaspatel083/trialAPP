
package com.example.rest_api_demo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;


public class Torrent {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("hash")
    @Expose
    public String hash;
    @SerializedName("quality")
    @Expose
    public String quality;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("seeds")
    @Expose
    public Integer seeds;
    @SerializedName("peers")
    @Expose
    public Integer peers;
    @SerializedName("size")
    @Expose
    public String size;
    @SerializedName("size_bytes")
    @Expose
    public BigInteger sizeBytes;
    @SerializedName("date_uploaded")
    @Expose
    public String dateUploaded;
    @SerializedName("date_uploaded_unix")
    @Expose
    public Integer dateUploadedUnix;

}
