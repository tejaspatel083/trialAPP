
package com.example.rest_api_demo.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("imdb_code")
    @Expose
    public String imdbCode;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("title_english")
    @Expose
    public String titleEnglish;
    @SerializedName("title_long")
    @Expose
    public String titleLong;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("year")
    @Expose
    public Integer year;
    @SerializedName("rating")
    @Expose
    public Double rating;
    @SerializedName("runtime")
    @Expose
    public Integer runtime;
    @SerializedName("genres")
    @Expose
    public List<String> genres = null;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("description_full")
    @Expose
    public String descriptionFull;
    @SerializedName("synopsis")
    @Expose
    public String synopsis;
    @SerializedName("yt_trailer_code")
    @Expose
    public String ytTrailerCode;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("mpa_rating")
    @Expose
    public String mpaRating;
    @SerializedName("background_image")
    @Expose
    public String backgroundImage;
    @SerializedName("background_image_original")
    @Expose
    public String backgroundImageOriginal;
    @SerializedName("small_cover_image")
    @Expose
    public String smallCoverImage;
    @SerializedName("medium_cover_image")
    @Expose
    public String mediumCoverImage;
    @SerializedName("large_cover_image")
    @Expose
    public String largeCoverImage;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("torrents")
    @Expose
    public List<Torrent> torrents = null;
    @SerializedName("date_uploaded")
    @Expose
    public String dateUploaded;
    @SerializedName("date_uploaded_unix")
    @Expose
    public Integer dateUploadedUnix;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImdbCode() {
        return imdbCode;
    }

    public void setImdbCode(String imdbCode) {
        this.imdbCode = imdbCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public String getTitleLong() {
        return titleLong;
    }

    public void setTitleLong(String titleLong) {
        this.titleLong = titleLong;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescriptionFull() {
        return descriptionFull;
    }

    public void setDescriptionFull(String descriptionFull) {
        this.descriptionFull = descriptionFull;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getYtTrailerCode() {
        return ytTrailerCode;
    }

    public void setYtTrailerCode(String ytTrailerCode) {
        this.ytTrailerCode = ytTrailerCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMpaRating() {
        return mpaRating;
    }

    public void setMpaRating(String mpaRating) {
        this.mpaRating = mpaRating;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getBackgroundImageOriginal() {
        return backgroundImageOriginal;
    }

    public void setBackgroundImageOriginal(String backgroundImageOriginal) {
        this.backgroundImageOriginal = backgroundImageOriginal;
    }

    public String getSmallCoverImage() {
        return smallCoverImage;
    }

    public void setSmallCoverImage(String smallCoverImage) {
        this.smallCoverImage = smallCoverImage;
    }

    public String getMediumCoverImage() {
        return mediumCoverImage;
    }

    public void setMediumCoverImage(String mediumCoverImage) {
        this.mediumCoverImage = mediumCoverImage;
    }

    public String getLargeCoverImage() {
        return largeCoverImage;
    }

    public void setLargeCoverImage(String largeCoverImage) {
        this.largeCoverImage = largeCoverImage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Torrent> getTorrents() {
        return torrents;
    }

    public void setTorrents(List<Torrent> torrents) {
        this.torrents = torrents;
    }

    public String getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(String dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public Integer getDateUploadedUnix() {
        return dateUploadedUnix;
    }

    public void setDateUploadedUnix(Integer dateUploadedUnix) {
        this.dateUploadedUnix = dateUploadedUnix;
    }
}
