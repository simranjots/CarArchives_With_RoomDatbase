package com.example.cararchives_with_roomdatbase;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.cararchives_with_roomdatbase.Model.DataItem;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private TextView tvName, tvDescription, tvPrice,tvColor,tvYear,tvModel,tvVin;
    private ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        DataItem item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
        if (item == null) {
            throw new AssertionError("Null data item received!");
        }

        tvName = findViewById(R.id.tvItemName);
        tvPrice = findViewById(R.id.tvPrice);
        tvDescription = findViewById(R.id.tvDescription);
        itemImage = findViewById(R.id.itemImage);
        tvColor = findViewById(R.id.tvItemColor);
        tvYear = findViewById(R.id.tvItemYear);
        tvModel = findViewById(R.id.tvItemModel);
        tvVin = findViewById(R.id.tvItemVin);
        tvName.setText(item.getItemName());
        tvDescription.setText(item.getDescription());
        tvColor.setText(item.getColor());
        tvModel.setText(item.getCategory());
        tvVin.setText(item.getVin());
        tvYear.setText(item.getYear());

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
        tvPrice.setText(nf.format(item.getPrice()));

        InputStream inputStream = null;
        try {
            String imageFile = item.getImage();
            inputStream = getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, imageFile);
            itemImage.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}