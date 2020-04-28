package com.example.cararchives_with_roomdatbase;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.cararchives_with_roomdatbase.Database.DataItemDatabase;
import com.example.cararchives_with_roomdatbase.Model.DataItem;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class AddCarActivity extends AppCompatActivity {

    private Activity activity;
    private String imagePath = "";
    private List<DataItem> mItems ;
    private boolean forUpdate = false;
    ImageView imageView;
    AppCompatEditText edtName, edtModel, edtYear, edtColor, edtVin, edtPrice;
    AppCompatButton btnAdd;
    DataItem carDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_data);
        mItems = new ArrayList<>();
        carDetail = new DataItem();
        activity = this;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        imageView = findViewById(R.id.imageView);
        edtName = findViewById(R.id.edtName);
        edtModel = findViewById(R.id.edtModel);
        edtYear = findViewById(R.id.edtYear);
        edtColor = findViewById(R.id.edtColor);
        edtVin = findViewById(R.id.edtVin);
        edtPrice = findViewById(R.id.edtPrice);
        btnAdd = findViewById(R.id.btnAdd);

        getIntentValue();
        listeners();


    }

    private void getIntentValue() {
        if (getIntent().hasExtra("Update")) {
            forUpdate = true;
            if (getIntent().hasExtra("CarDetail")) {
               carDetail = getIntent().getExtras().getParcelable("CarDetail");
                if (carDetail != null) {
                    setUpData();
                }else {
                    Toast.makeText(activity, "unable to update", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void setUpData() {
        imagePath = carDetail.getImage();
        edtName.setText(carDetail.getItemName());
        edtModel.setText(carDetail.getCategory());
        edtYear.setText(carDetail.getYear());
        edtColor.setText(carDetail.getColor());
        edtVin.setText(carDetail.getVin());
        btnAdd.setText(R.string.update);
        edtPrice.setText((String.valueOf(carDetail.getPrice())));
    }
    private void listeners() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(activity)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
          int i = 0;
            @Override
            public void onClick(View v) {
                DataItemDatabase diDB = DataItemDatabase.getInstance(activity);
                if (forUpdate) {
                    carDetail = new DataItem(carDetail.getItemId(),edtName.getText().toString(), carDetail.getDescription(),
                            edtModel.getText().toString(), i, Double.parseDouble(edtPrice.getText().toString()), imagePath,
                            edtColor.getText().toString(), edtYear.getText().toString(),
                            edtVin.getText().toString());
                    diDB.dataItemDao().updateDataItem(carDetail);
                } else {
                    carDetail = new DataItem(edtName.getText().toString(), edtName.getText().toString()+ " this is the new car",
                            edtModel.getText().toString(), i, Double.parseDouble(edtPrice.getText().toString()), "aston.jpg",
                            edtColor.getText().toString(), edtYear.getText().toString(),
                            edtVin.getText().toString());
                    mItems.add(carDetail);
                    diDB.seedDatabase(mItems);
                    if (i < 4) {
                        i = 0;
                    }
                    i++;
                }
                if (i < 4) {
                    i = 0;
                }
                i++;
                setResult(RESULT_OK);
                finish();
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            assert data != null;
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            assert uri != null;
            imagePath = uri.getPath();
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, "Error Image Picker", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
