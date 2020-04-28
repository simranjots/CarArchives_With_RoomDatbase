package com.example.cararchives_with_roomdatbase.Model;

import android.content.ContentValues;

import android.os.Parcel;
import android.os.Parcelable;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "DataItem")
public class DataItem implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long itemId ;
    private String itemName;
    private String description;
    private String category;
    private int sortPosition;
    private double price;
    private String image;
    private String color;
    private String year;
    private String vin;

    @Ignore
    public DataItem() {
    }

    @Ignore
    public DataItem(String itemName, String description, String category, int sortPosition, double price, String image, String color, String year, String vin) {
        this.itemName = itemName;
        this.description = description;
        this.category = category;
        this.sortPosition = sortPosition;
        this.price = price;
        this.image = image;
        this.color = color;
        this.year = year;
        this.vin = vin;
    }

    public DataItem(long itemId, String itemName, String description, String category, int sortPosition, double price, String image, String color, String year, String vin) {

        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.category = category;
        this.sortPosition = sortPosition;
        this.price = price;
        this.image = image;
        this.color = color;
        this.year = year;
        this.vin = vin;
    }


    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSortPosition() {
        return sortPosition;
    }

    public void setSortPosition(int sortPosition) {
        this.sortPosition = sortPosition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }


//    public ContentValues toValues() {
//        ContentValues values = new ContentValues(7);
//        values.put(DBHelper.COLUMN_ID, itemId);
//        values.put(DBHelper.COLUMN_NAME, itemName);
//        values.put(DBHelper.COLUMN_CATEGORY, category);
//        values.put(DBHelper.COLUMN_DESCRIPTION, description);
//        values.put(DBHelper.COLUMN_POSITION, sortPosition);
//        values.put(DBHelper.COLUMN_PRICE, price);
//        values.put(DBHelper.COLUMN_IMAGE, image);
//        values.put(DBHelper.COLOR, color);
//        values.put(DBHelper.YEAR, year);
//        values.put(DBHelper.VIN, vin);
//        return values;
//    }

    @Override
    public String toString() {
        return "DataItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", sortPosition=" + sortPosition +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", color='" + color + '\'' +
                ", year='" + year + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }


    protected DataItem(Parcel in) {
        itemId = in.readLong();
        itemName = in.readString();
        description = in.readString();
        category = in.readString();
        sortPosition = in.readInt();
        price = in.readDouble();
        image = in.readString();
        color = in.readString();
        year = in.readString();
        vin = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(itemId);
        dest.writeString(itemName);
        dest.writeString(description);
        dest.writeString(category);
        dest.writeInt(sortPosition);
        dest.writeDouble(price);
        dest.writeString(image);
        dest.writeString(color);
        dest.writeString(year);
        dest.writeString(vin);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel in) {
            return new DataItem(in);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };


}
