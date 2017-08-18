package com.example.toto.projertbutstop;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;


public class listbus_offline extends AppCompatActivity {

    private String startString, endString;
    private String[] numberBusStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listbus_offline);

        //GetValue Intent
        getValueIntent();

        //Find NumberBus
        findNumberBus();


    }   // Main Method

    private void findNumberBus() {
        String tag = "18AugV4";
        ArrayList<String> stringArrayList = new ArrayList<String>();
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM busrouteTABLE", null);
        cursor.moveToFirst();
        String[] strNumberBusManyStrings = new String[cursor.getCount()];
        for (int i=0;i<strNumberBusManyStrings.length; i++) {
            strNumberBusManyStrings[i] = cursor.getString(2);
            Log.d(tag, "bus[" + i + "] ==> " + strNumberBusManyStrings[i]);
            stringArrayList.add(strNumberBusManyStrings[i]);
            cursor.moveToNext();
        }   //for

        Log.d(tag, "stringArrayList ==> " + stringArrayList);
        Object[] objects = stringArrayList.toArray();
        for (Object object : objects) {
            if (stringArrayList.indexOf(object) != stringArrayList.lastIndexOf(object)) {
                stringArrayList.remove(stringArrayList.lastIndexOf(object));
            }
        }

        Log.d(tag, "last StringArrayList ==> " + stringArrayList);
        numberBusStrings = new String[stringArrayList.size()];
        numberBusStrings = stringArrayList.toArray(new String[0]);
        for (int i=0;i<numberBusStrings.length;i++) {
            Log.d(tag, "numbutBus[" + i + "] ==> " + numberBusStrings[i]);
        }


    }   // findNumberBus

    private void getValueIntent() {
        String tag = "18AugV4";
        startString = getIntent().getStringExtra("Start");
        endString = getIntent().getStringExtra("End");
        Log.d(tag, "rev Start ==> " + startString);
        Log.d(tag, "rev End ==> " + endString);


    }

}   // Main Class