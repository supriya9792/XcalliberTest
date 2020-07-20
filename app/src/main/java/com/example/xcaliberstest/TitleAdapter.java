package com.example.xcaliberstest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TitleAdapter extends  RecyclerView.Adapter<TitleAdapter.MyView>  {

    private Context mContext;
    private List<Item> subList;
    private ArrayList<Item> arraylist;


    public class MyView extends RecyclerView.ViewHolder {
        public TextView title;
        public CardView cardview;
        public MyView(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            cardview=(CardView)view.findViewById(R.id.cardview);

        }
    }

    public TitleAdapter(Context mContext, List<Item> albumList) {
        this.mContext = mContext;
        this.subList = albumList;
        this.arraylist = new ArrayList<Item>();
        this.arraylist.addAll(albumList);
    }

    @Override
    public TitleAdapter.MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_list, parent, false);

        return new TitleAdapter.MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final TitleAdapter.MyView holder, int position) {

        final Item enq = subList.get(position);

        holder.title.setText(enq.getTitle());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"card clicked",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(mContext,TitleDetailsActivity.class);
                intent.putExtra("PlaceOfPublication",enq.getPlaceOfPublication());
                intent.putExtra("Publisher",enq.getPublisher());
                intent.putExtra("Frequency", enq.getFrequency());
                intent.putExtra("Country",enq.getCountry());
                intent.putExtra("Url",enq.getUrl());
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return subList.size();
    }

}

