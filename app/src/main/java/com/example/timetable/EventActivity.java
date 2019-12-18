package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
;

public class EventActivity extends AppCompatActivity {

    SQLiteEvent dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        dbHelper = new SQLiteEvent(this);
    }

    public void onADDclick(View v){

        String name = ((EditText) findViewById(R.id.event_name)).getText().toString();
        String time = ((EditText) findViewById(R.id.event_time)).getText().toString();
        long day = ((Spinner) findViewById(R.id.event_day)).getSelectedItemId();
        String comment = ((EditText) findViewById(R.id.event_comment)).getText().toString();

        SQLiteDatabase db;

        db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteEvent.EVENT_CONTACT_ID, MainActivity.LOGIN_ID);
        contentValues.put(SQLiteEvent.EVENT_NAME, name);
        contentValues.put(SQLiteEvent.EVENT_TIME, time);
        contentValues.put(SQLiteEvent.EVENT_DAY, day);
        contentValues.put(SQLiteEvent.EVENT_COMMENT, comment);

        db.insert(SQLiteEvent.TABLE_CONTACTS_EVENT, null , contentValues);

        finish();

    }

    public void onButtonBackClick(View v) {
        finish();
    }
}