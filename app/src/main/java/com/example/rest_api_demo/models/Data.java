
package com.example.rest_api_demo.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("movie_count")
    @Expose
    public Integer movieCount;
    @SerializedName("limit")
    @Expose
    public Integer limit;
    @SerializedName("page_number")
    @Expose
    public Integer pageNumber;
    @SerializedName("movies")
    @Expose
    public List<Movie> movies = null;

}
