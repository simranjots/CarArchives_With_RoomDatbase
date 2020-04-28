package com.example.cararchives_with_roomdatbase.SampleData;


import com.example.cararchives_with_roomdatbase.Model.DataItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleDataProvider {
    public static List<DataItem> dataItemList;
    public static Map<Long, DataItem> dataItemMap;

    static {
        dataItemList = new ArrayList<>();
        dataItemMap = new HashMap<>();

        addItem(new DataItem( "Ferrari", "Ferrari is an Italian luxury sports car manufacturer based in Maranello. Founded by Enzo Ferrari in 1939 out of Alfa Romeo's race division as Auto Avio Costruzioni, the company built its first car in 1940.","Sports",
                1, 80000, "ferrari.jpg","Red","2018","10000"));

        addItem(new DataItem("Lamborghini",
                "Automobili Lamborghini S.p.A. is an Italian brand and manufacturer of luxury sports cars and SUVs based in Sant'Agata Bolognese. The company is owned by the Volkswagen Group through its subsidiary Audi.",
                "Sports",  2, 75000, "lamborghini.jpg","Red","2018","10000"));

        addItem(new DataItem( "BMW",
                "Bayerische Motoren Werke AG, translated in English as Bavarian Motor Works, commonly referred to as BMW  , is a German multinational company which produces luxury vehicles and motorcycles." +
                        " The company was founded in 1916 as a manufacturer of aircraft engines, which it produced from 1917 until 1918 and again from 1933 to 1945.",
                "Sports", 3, 60000, "bmw.jpg","Red","2018","10000"));

        addItem(new DataItem( "Aston Martin",
                "Aston Martin Lagonda Global Holdings plc is a British independent manufacturer of luxury sports cars and grand tourers." +
                        " It was founded in 1913 by Lionel Martin and Robert Bamford.",
                "Sports", 4, 50000, "aston.jpg","Red","2018","10000"));

        addItem(new DataItem( "McLaren",
                "McLaren Automotive  is a British automotive manufacturer based at the McLaren Technology Centre in Woking, Surrey. " +
                        "The main products of the company are supercars, which are produced in-house in designated production facilities. " +
                        "In July 2017, McLaren Automotive became a 100% owned subsidiary of the wider McLaren Group.",
                "Sports", 1, 90000, "mclaren.jpg","Red","2018","10000"));

        addItem(new DataItem("Porsche",
                "Dr.-Ing. h.c. F. Porsche AG, usually shortened to Porsche AG , is a German automobile manufacturer specializing in high-performance sports cars, SUVs and sedans. " +
                        "The headquarters of Porsche AG is in Stuttgart, and is owned by Volkswagen AG, a controlling stake of which is owned by Porsche Automobil Holding SE." +
                        " Porsche's current lineup includes the 718 Boxster/Cayman, 911, Panamera, Macan, Cayenne and Taycan.",
                "Sports", 2, 65000, "porsche.jpg","Red","2018","10000"));


    }

    private static void addItem(DataItem item) {
        dataItemList.add(item);
        dataItemMap.put(item.getItemId(), item);
    }

}
