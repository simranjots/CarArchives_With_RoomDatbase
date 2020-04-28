package com.example.cararchives_with_roomdatbase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cararchives_with_roomdatbase.Database.DataItemDatabase;
import com.example.cararchives_with_roomdatbase.Model.DataItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder>  {

    public static final String ITEM_KEY = "item_key";
    private List<DataItem> mItems;
    private Context mContext;
    DataItemDatabase dataItemDatabase;
    String imageFile = "";
    InputStream inputStream = null;

    public DataItemAdapter(Context context, List<DataItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @NonNull
    @Override
    public DataItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataItemAdapter.ViewHolder holder, int position) {
        final DataItem item = mItems.get(position);
        try {
                  holder.tvName.setText(item.getItemName());
                  imageFile = item.getImage();
                  inputStream = mContext.getAssets().open(imageFile);
                Drawable d = Drawable.createFromStream(inputStream, null);
                holder.imageView.setImageDrawable(d);
             } catch (IOException e) {
                  e.printStackTrace();
              }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(ITEM_KEY, item);
                mContext.startActivity(intent);
            }
        });



    }

    DataItem getPositionObject(int position){
        DataItem d = mItems.get(position);
        return d;
    }
     void getDataItemAtPosition(int position) {

        DataItem d = mItems.get(position);
        if(d !=null){
        dataItemDatabase = DataItemDatabase.getInstance(mContext);
             dataItemDatabase.dataItemDao().deleteDataItem(d);
            mItems.remove(position);
            this.notifyItemRemoved(position);
        }else {
            Toast.makeText(mContext, "Unable to Delete", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

         TextView tvName;
        ImageView imageView;
        View mView;
         ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.itemNameText);
            imageView = itemView.findViewById(R.id.imageView);
            mView = itemView;
        }
    }
}