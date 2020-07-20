package com.example.xcaliberstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class SearchActivity extends AppCompatActivity {
    WebView simpleWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        simpleWebView = (WebView) findViewById(R.id.simpleWebView);

        Intent intent=getIntent();
        String text=intent.getStringExtra("search_text");
        String url = "https://chroniclingamerica.loc.gov/search/pages/results/?andtext="+text;
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url); // load a web page in a web view

    }
}