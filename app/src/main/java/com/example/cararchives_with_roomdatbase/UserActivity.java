package com.example.cararchives_with_roomdatbase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cararchives_with_roomdatbase.Database.DataItemDatabase;
import com.example.cararchives_with_roomdatbase.Model.DataItem;
import com.example.cararchives_with_roomdatbase.SampleData.SampleDataProvider;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private static final int SIGNIN_REQUEST = 1001;
    public static final String MY_GLOBAL_PREFS = "my_global_prefs";
    List<DataItem> dataItemList ;
    RecyclerView mRecyclerView;
    DataItemAdapter mItemAdapter;
    DataItemDatabase dataItemDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mRecyclerView = findViewById(R.id.rvItems);
        dataItemList = SampleDataProvider.dataItemList;
        dataItemDatabase =DataItemDatabase.getInstance(this);
        if (dataItemList != null){

            dataItemDatabase.seedDatabase(dataItemList);
        }

        displayDataItems();
    }

    private void displayDataItems() {
        dataItemList = dataItemDatabase.dataItemDao().getDataItemList();
        mItemAdapter = new DataItemAdapter(this, dataItemList);
        mRecyclerView.setAdapter(mItemAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayDataItems();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == SIGNIN_REQUEST) {
            String email = data.getStringExtra(LoginDetailsActivity.EMAIL_KEY);
            Toast.makeText(this, "You signed in as " + email, Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor =
                    getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE).edit();
            editor.putString(LoginDetailsActivity.EMAIL_KEY, email);
            editor.apply();

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
