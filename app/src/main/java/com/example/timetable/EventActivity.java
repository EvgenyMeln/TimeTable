package com.example.timetable;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.timetable.db.SQLiteEvent;
import com.example.timetable.table.events.EventItem;

public class EventActivity extends AppCompatActivity {

    SQLiteEvent dbHelper;
    public final static String EXTRA_EDIT_OR_ADD = "extra_edit_or_add";
    public final static String EXTRA_EVENT_ITEM = "extra_event_item";
    public final static String EXTRA_EVENT_DAY = "extra_event_day";
    private boolean isEdit = false;

    EditText name;
    EditText time;
    Spinner day;
    EditText comment;
    EditText homework;
    private Button button;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initViews();

        Intent intent = getIntent();
        isEdit = intent.getBooleanExtra(EXTRA_EDIT_OR_ADD, false);

        if (isEdit)
            initEdit(intent);
        else
            initAdd();

    }

    public void onAddClick(View v){

        dbHelper = new SQLiteEvent(this);

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

        if(isEdit) {
            contentValues.put(SQLiteEvent.EVENT_HOMEWORK, homework.getText().toString());
            db.update(SQLiteEvent.TABLE_CONTACTS_EVENT, contentValues, SQLiteEvent.EVENT_ID + "=" + id, null);
        }
        else
            db.insert(SQLiteEvent.TABLE_CONTACTS_EVENT, null , contentValues);

        db.close();
        setResult(RESULT_OK);
        finish();1

    }

    public void onButtonBackClick(View v) {
        finish();
    }

    private void initViews() {
        name = findViewById(R.id.event_name);
        time = findViewById(R.id.event_time);
        comment = findViewById(R.id.event_comment);
        homework = findViewById(R.id.event_homework);
        button = findViewById(R.id.button10);
        day = findViewById(R.id.event_day);
    }

    private void initEdit(Intent intent){
        EventItem eventitem = (EventItem) intent.getSerializableExtra(EXTRA_EVENT_ITEM);

        id = eventitem.getId();

        name.setText(eventitem.getName());
        time.setText(eventitem.getTime());
        comment.setText(eventitem.getComment());
        day.setSelection(intent.getIntExtra(EXTRA_EVENT_DAY, 0));
        homework.setVisibility(View.VISIBLE);
        homework.setText(eventitem.getHomework());
        button.setText("Изменить");

    }

    private void initAdd(){
        homework.setVisibility(View.INVISIBLE);
        button.setText("Готово");
    }
}