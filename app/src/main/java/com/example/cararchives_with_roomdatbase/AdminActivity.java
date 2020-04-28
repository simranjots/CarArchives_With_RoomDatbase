package com.example.cararchives_with_roomdatbase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cararchives_with_roomdatbase.Database.DataItemDatabase;
import com.example.cararchives_with_roomdatbase.Model.DataItem;
import com.example.cararchives_with_roomdatbase.Model.SwipeHelper;
import com.example.cararchives_with_roomdatbase.SampleData.SampleDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;


public class AdminActivity extends AppCompatActivity {

    private static final int SIGNIN_REQUEST = 1001;
    public static final String MY_GLOBAL_PREFS = "my_global_prefs";
    List<DataItem> dataItemList;
    RecyclerView mRecyclerView;
    DataItemAdapter mItemAdapter;
    FloatingActionButton fab;
    DataItem item;
    DataItemDatabase dataItemDatabase;
    SwipeHelper swipeHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecyclerView = findViewById(R.id.rvItems);
        dataItemList = SampleDataProvider.dataItemList;
        dataItemDatabase =DataItemDatabase.getInstance(this);
        if (dataItemList != null){

            dataItemDatabase.seedDatabase(dataItemList);
        }
        item =  new DataItem();
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AddCarActivity.class);
                startActivity(intent);
            }
        });


     swipeHelper =  new SwipeHelper(this, mRecyclerView) {
            @Override
            public void instantiateUnderlayButton(final RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Delete",
                        0,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete
                                mItemAdapter.getDataItemAtPosition(pos);
                            }
                        }
                ));

                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Update",
                        0,
                        Color.parseColor("#FF9502"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnUpdate
                                Intent intent = new Intent(getApplicationContext(), AddCarActivity.class);
                                intent.putExtra("CarDetail", pos);
                                intent.putExtra("Update", true);
                                startActivity(intent);
                            }
                        }
                ));
//                underlayButtons.add(new SwipeHelper.UnderlayButton(
//                        "Unshare",
//                        0,
//                        Color.parseColor("#C7C7CB"),
//                        new SwipeHelper.UnderlayButtonClickListener() {
//                            @Override
//                            public void onClick(int pos) {
//                                // TODO: OnUnshare
//                            }
//                        }
//                ));
            }
        };

//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
//                ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//                 mItemAdapter.getDataItemAtPosition(viewHolder.getAdapterPosition());
//
//                Toast.makeText(AdminActivity.this, viewHolder.getItemId() +"Item Deleted", Toast.LENGTH_SHORT).show();
//            }
//        }).attachToRecyclerView(mRecyclerView);

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





