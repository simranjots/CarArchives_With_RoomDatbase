package com.example.cararchives_with_roomdatbase.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cararchives_with_roomdatbase.Model.DataItem;

import java.util.List;
@Dao
public interface DataItemDao {

    @Query("Select * From DataItem")
    List<DataItem> getDataItemList();
    @Insert
    Void insertDataItem(DataItem dataItem);
    @Update
    Void updateDataItem(DataItem dataItem);
    @Delete
    Void deleteDataItem(DataItem dataItem);

}
