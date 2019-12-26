package com.example.timetable.table;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.timetable.EventActivity;
import com.example.timetable.MainActivity;
import com.example.timetable.R;
import com.example.timetable.db.SQLiteEvent;
import com.example.timetable.table.events.EventAdapter;
import com.example.timetable.table.events.EventClickListener;
import com.example.timetable.table.events.EventItem;
import com.example.timetable.table.weekdays.WeekdayAdapter;
import com.example.timetable.table.weekdays.WeekdayClickListener;
import com.example.timetable.table.weekdays.Weekdays;
import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {

    private RecyclerView rvEvent;
    private RecyclerView rvWeekdays;
    private TextView tvDay;

    private EventAdapter eventAdapter;
    private WeekdayAdapter weekdayAdapter;
    private SQLiteEvent dbHelper;
    private final int UPDATE_REQUEST = 1;

    private Weekdays currentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        initViews();

        tvDay.setText("Выберете день недели!");

        initEventRV();
        initWeekdaysRV();

        dbHelper = new SQLiteEvent(this);
    }

    private void initViews() {
        tvDay = findViewById(R.id.textView3);
        rvEvent = findViewById(R.id.rv_event);
        rvWeekdays = findViewById(R.id.rv_weekdays);
    }

    public void initEventRV() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        eventAdapter = new EventAdapter();
        eventAdapter.setListener(new EventClickListener() {
            @Override
            public void onClickedItem(EventItem eventitem) {
                Intent intent = new Intent(TableActivity.this, EventActivity.class);
                intent.putExtra(EventActivity.EXTRA_EDIT_OR_ADD, true);
                intent.putExtra(EventActivity.EXTRA_EVENT_ITEM, eventitem);
                intent.putExtra(EventActivity.EXTRA_EVENT_DAY, currentDay.getInt());
                startActivityForResult(intent,UPDATE_REQUEST);

            }
        });
        rvEvent.setLayoutManager(llm);
        rvEvent.setAdapter(eventAdapter);
    }

    public void initWeekdaysRV() {
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        weekdayAdapter = new WeekdayAdapter();
        weekdayAdapter.setListener(new WeekdayClickListener() {
            @Override
            public void onDayClicked(Weekdays day) {
                tvDay.setText(day.getFullName());
                selectWeekday(day);
            }
        });
        rvWeekdays.setLayoutManager(llm);
        rvWeekdays.setAdapter(weekdayAdapter);

        for (Weekdays day : Weekdays.values()) {
            weekdayAdapter.addItem(day);
        }
        weekdayAdapter.notifyDataSetChanged();
    }


    public void selectWeekday(Weekdays day) {
        currentDay = day;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(SQLiteEvent.TABLE_CONTACTS_EVENT,
                null,
                SQLiteEvent.EVENT_DAY + " = ? AND " + SQLiteEvent.EVENT_CONTACT_ID + " = ?",
                new String[] { day.getValue(), MainActivity.LOGIN_ID.toString() },
                null,
                null,
                null,
                null);

        List<EventItem> items = new ArrayList<>();

        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(SQLiteEvent.EVENT_NAME);
            int timeIndex = cursor.getColumnIndex(SQLiteEvent.EVENT_TIME);
            int commentIndex = cursor.getColumnIndex(SQLiteEvent.EVENT_COMMENT);
            int idIndex = cursor.getColumnIndex(SQLiteEvent.EVENT_ID);
            int homeworkIndex = cursor.getColumnIndex(SQLiteEvent.EVENT_HOMEWORK);
            items.add(
                    new EventItem(
                            cursor.getInt(idIndex),
                            cursor.getString(nameIndex),
                            cursor.getString(timeIndex),
                            cursor.getString(commentIndex),
                            cursor.getString(homeworkIndex) ));
        }

        eventAdapter.setItems(items);

        cursor.close();
    }

    public void onEventButtonClick(View v) {
        Intent i = new Intent(TableActivity.this, EventActivity.class);
        startActivity(i);
    }

    public void onButtonClick(View v) {
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UPDATE_REQUEST && resultCode == RESULT_OK) {
            selectWeekday(currentDay);
            eventAdapter.notifyDataSetChanged();
        }
    }
}