package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    SQLiteEvent dbHelper;

    private enum WEEKDAYS {
        WEEKDAY_MONDAY("0"),
        WEEKDAY_TUESDAY("1"),
        WEEKDAY_WEDNESDAY("2"),
        WEEKDAY_THURSDAY("3"),
        WEEKDAY_FRIDAY("4"),
        WEEKDAY_SATURDAY("5"),
        WEEKDAY_SUNDAY("6");

        private final String value;

        WEEKDAYS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        TextView day = findViewById(R.id.textView3);
        day.setText("Выберете день недели!");
    }

    public void OnClickButton1(View v) {
        TextView day = findViewById(R.id.textView3);
        day.setText("Понедельник");
        selectWeekday(WEEKDAYS.WEEKDAY_MONDAY);
    }

    public void OnClickButton2(View v) {
        TextView day = findViewById(R.id.textView3);
        day.setText("Вторник");
        selectWeekday(WEEKDAYS.WEEKDAY_TUESDAY);
    }

    public void OnClickButton3(View v) {
        TextView day = findViewById(R.id.textView3);
        day.setText("Среда");
        selectWeekday(WEEKDAYS.WEEKDAY_WEDNESDAY);
    }

    public void OnClickButton4(View v) {
        TextView day = findViewById(R.id.textView3);
        day.setText("Четверг");
        selectWeekday(WEEKDAYS.WEEKDAY_THURSDAY);
    }

    public void OnClickButton5(View v) {
        TextView day = findViewById(R.id.textView3);
        day.setText("Пятница");
        selectWeekday(WEEKDAYS.WEEKDAY_FRIDAY);
        ;
    }

    public void OnClickButton6(View v) {
        TextView day = findViewById(R.id.textView3);
        day.setText("Суббота");
        selectWeekday(WEEKDAYS.WEEKDAY_SATURDAY);
        ;
    }

    public void OnClickButton7(View v) {
        TextView day = findViewById(R.id.textView3);
        day.setText("Воскресенье");
        selectWeekday(WEEKDAYS.WEEKDAY_SUNDAY);
    }

    public void onEventButtonClick(View v) {
        Intent i = new Intent(TableActivity.this, EventActivity.class);
        startActivity(i);
    }

    public void onButtonClick(View v) {
        finish();
    }

    public void selectWeekday(WEEKDAYS day) {

        dbHelper = new SQLiteEvent(this);

        SQLiteDatabase db = dbHelper .getWritableDatabase();

        Cursor cursor = db.query(SQLiteEvent.TABLE_CONTACTS_EVENT,null,SQLiteEvent.EVENT_DAY + " = ?", new String[]{day.getValue()}, null,null,null,null);

        ArrayList<String> names = new ArrayList<>();

        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(SQLiteEvent.EVENT_NAME);
            int timeIndex = cursor.getColumnIndex(SQLiteEvent.EVENT_TIME);
            int commentIndex = cursor.getColumnIndex(SQLiteEvent.EVENT_COMMENT);

            names.add(cursor.getString(nameIndex));
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        ListView list_event = findViewById(R.id.event_list);
        list_event.setAdapter(adapter);
    }
}