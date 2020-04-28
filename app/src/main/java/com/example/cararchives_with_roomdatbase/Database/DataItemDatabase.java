package com.example.cararchives_with_roomdatbase.Database;

import android.content.Context;
import android.database.sqlite.SQLiteException;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.cararchives_with_roomdatbase.Model.DataItem;

import java.util.List;

@Database(entities = DataItem.class,exportSchema = false,version = 1)
public abstract class DataItemDatabase extends RoomDatabase {
    private static final String DB_NAME = "cars.db";
    private static DataItemDatabase instance;

    public static synchronized DataItemDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),DataItemDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public void seedDatabase(List<DataItem> dataItemList) {
        for (DataItem item :
                dataItemList) {
            try {
                instance.dataItemDao().insertDataItem(item);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }

    }

    public  abstract DataItemDao dataItemDao();


    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
