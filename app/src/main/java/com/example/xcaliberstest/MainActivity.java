package com.example.xcaliberstest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TitleAdapter adapter;
    ArrayList<Item> subListArrayList = new ArrayList<Item>();
    Item subList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getdata();
    }
    private void getdata() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.Baseurl)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<ResponseBody> call = api.getItems();

        call.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                ResponseBody responseHome = response.body();

                subListArrayList=new ArrayList<>();
                if(responseHome != null) {
                    List<Item> additionalProperties = responseHome.getItems();

                    for (int i = 0; i < additionalProperties.size(); i++) {
                        subList = new Item();
                        subList.setTitle(additionalProperties.get(i).getTitle());
                        subListArrayList.add(subList);
                    }
                    adapter = new TitleAdapter(MainActivity.this, additionalProperties);
                    recyclerView.setAdapter(adapter);
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}