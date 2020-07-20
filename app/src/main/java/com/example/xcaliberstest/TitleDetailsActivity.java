package com.example.xcaliberstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TitleDetailsActivity extends AppCompatActivity {

    TextView placeOfPublication,publisher,edition,county,urltxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_details);

        Intent intent=getIntent();

        String publication=intent.getStringExtra("PlaceOfPublication");
        String Publisher=intent.getStringExtra("Publisher");
        String Country=intent.getStringExtra("Country");
        String frequency=intent.getStringExtra("Frequency");
        String Url=intent.getStringExtra("Url");

        placeOfPublication=findViewById(R.id.place);
        publisher=findViewById(R.id.publisher);
        edition=findViewById(R.id.edition);
        county=findViewById(R.id.country);
        urltxt=findViewById(R.id.url);

        placeOfPublication.setText(publication);
        publisher.setText(Publisher);
        county.setText(Country);
        edition.setText(frequency);
        urltxt.setText(Url);

        urltxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TitleDetailsActivity.this,UrlDetailsActivity.class);
                startActivity(intent);
            }
        });


    }
}