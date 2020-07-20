package com.example.xcaliberstest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UrlDetailsActivity extends AppCompatActivity {
    TableLayout tl;

    EditText inputSearch;
    ImageView searchbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_details);

         tl = (TableLayout) findViewById(R.id.tableLayout);
         inputSearch=findViewById(R.id.inputsearchid);
         searchbtn=findViewById(R.id.search);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputSearch.getText().length()>0){
                    Intent intent=new Intent(UrlDetailsActivity.this,SearchActivity.class);
                    intent.putExtra("search_text",inputSearch.getText());
                    startActivity(intent);
                }else{
                    Toast.makeText(UrlDetailsActivity.this,"Please enter text to search", Toast.LENGTH_LONG).show();
                }

            }
        });
         getdata();

    }

    private void getdata() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://chroniclingamerica.loc.gov/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<UrlBody> call = api.getString();

        call.enqueue(new Callback<UrlBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<UrlBody> call, retrofit2.Response<UrlBody> response) {
                UrlBody responseHome = response.body();

                if(responseHome != null) {
                   // String jsonresponse = response.body().toString();


                        addrow("Place Of Publication",responseHome.getPlaceOfPublication());
                        addrow("Start Year",responseHome.getStartYear());
                        addrow("Place",responseHome.getPlace().get(0));
                        addrow("Name",responseHome.getName());
                        addrow("Publisher",responseHome.getPublisher());
                        addrow("End Year",responseHome.getEndYear());
                        addrow("Subject",responseHome.getSubject().get(0));

                }

            }

            @Override
            public void onFailure(Call<UrlBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addrow(String title,String data){
        TableRow pprow = new TableRow(UrlDetailsActivity.this);
        TextView pp = new TextView(UrlDetailsActivity.this);
        TextView pptext = new TextView(UrlDetailsActivity.this);

        pp.setPadding(5,5,5,5);
        pptext.setPadding(5,5,5,5);
        pp.setText(title);
        pptext.setText(data);
        pprow.addView(pp);
        pprow.addView(pptext);
        pp.setBackground(getResources().getDrawable(R.drawable.tableborder));
        pptext.setBackground(getResources().getDrawable(R.drawable.tableborder));
        tl.addView(pprow, new TableLayout.LayoutParams());

    }
}