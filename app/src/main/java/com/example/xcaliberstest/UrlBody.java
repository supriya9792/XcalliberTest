package com.example.xcaliberstest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UrlBody {
    @SerializedName("place_of_publication")
    @Expose
    private String placeOfPublication;
    @SerializedName("lccn")
    @Expose
    private String lccn;
    @SerializedName("start_year")
    @Expose
    private String startYear;
    @SerializedName("place")
    @Expose
    private List<String> place = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("end_year")
    @Expose
    private String endYear;
    @SerializedName("issues")
    @Expose
    private List<Object> issues = null;
    @SerializedName("subject")
    @Expose
    private List<String> subject = null;

    public String getPlaceOfPublication() {
        return placeOfPublication;
    }

    public void setPlaceOfPublication(String placeOfPublication) {
        this.placeOfPublication = placeOfPublication;
    }

    public String getLccn() {
        return lccn;
    }

    public void setLccn(String lccn) {
        this.lccn = lccn;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public List<String> getPlace() {
        return place;
    }

    public void setPlace(List<String> place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public List<Object> getIssues() {
        return issues;
    }

    public void setIssues(List<Object> issues) {
        this.issues = issues;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public UrlBody(String placeOfPublication, String lccn, String startYear, List<String> place, String name, String publisher, String url, String endYear, List<Object> issues, List<String> subject) {
        this.placeOfPublication = placeOfPublication;
        this.lccn = lccn;
        this.startYear = startYear;
        this.place = place;
        this.name = name;
        this.publisher = publisher;
        this.url = url;
        this.endYear = endYear;
        this.issues = issues;
        this.subject = subject;
    }
}
