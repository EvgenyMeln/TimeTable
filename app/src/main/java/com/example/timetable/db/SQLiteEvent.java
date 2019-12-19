package com.example.timetable.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteEvent extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contact_db";
    public static final String TABLE_CONTACTS_EVENT = "contacts_event";

    public static final String EVENT_ID = "contact_event_id";
    public static final String EVENT_NAME = "name";
    public static final String EVENT_CONTACT_ID = "contact_id";
    public static final String EVENT_TIME = "time";
    public static final String EVENT_DAY = "day";
    public static final String EVENT_COMMENT = "comment";

    public SQLiteEvent(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_CONTACTS_EVENT + "(" + EVENT_ID
                + " integer primary key autoincrement," + EVENT_CONTACT_ID + " integer," + EVENT_NAME + " text," + EVENT_TIME + " text," + EVENT_DAY + " text,"+ EVENT_COMMENT + " text"+ ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TABLE_CONTACTS_EVENT);

        onCreate(db);
    }
}