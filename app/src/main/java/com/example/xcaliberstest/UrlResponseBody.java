package com.example.xcaliberstest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UrlResponseBody {

    private List<UrlBody> urlBodyList = new ArrayList<UrlBody>();

    public List<UrlBody> getUrlBodyList() {
        return urlBodyList;
    }

    public void setUrlBodyList(List<UrlBody> urlBodyList) {
        this.urlBodyList = urlBodyList;
    }
}
