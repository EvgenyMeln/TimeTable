package com.example.timetable;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.timetable.db.SQLiteSignIn;
import com.example.timetable.table.TableActivity;

public class MainActivity extends AppCompatActivity {

    int d = 0;
    int n = 0;
    SQLiteSignIn dbHelper;
    public static Integer LOGIN_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toast toast = Toast.makeText(MainActivity.this, "Добро пожаловать!", Toast.LENGTH_LONG);
        toast.show();
    }

    public void onLoginButtonClick(View v) {

        TextView check =  findViewById(R.id.textView2);

        EditText log = findViewById(R.id.login);
        EditText pas = findViewById(R.id.password);

        String login = log.getText().toString();
        String password = pas.getText().toString();

        dbHelper = new SQLiteSignIn(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(SQLiteSignIn.TABLE_CONTACTS, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            int loginIndex = cursor.getColumnIndex(SQLiteSignIn.KEY_LOGIN);
            int passwordIndex = cursor.getColumnIndex(SQLiteSignIn.KEY_PASSWORD);
            int idIndex = cursor.getColumnIndex(SQLiteSignIn.KEY_ID);
            do{
                if(cursor.getString(loginIndex).equals(login)&& cursor.getString(passwordIndex).equals(password)){
                    LOGIN_ID = cursor.getInt(idIndex);
                    Intent i = new Intent(MainActivity.this, TableActivity.class);
                    startActivity(i);
                    break;
                }else d++;
            }while(cursor.moveToNext());

        }
        if(d>n){
            log.setText("");
            pas.setText("");
            check.setText("Неверный логин или пароль");
            d=0;
        }
    }

    public void onRegisterButtonClick(View v) {
        setContentView(R.layout.activity_reg);
    }

    public void onRegButtonClick(View v) {


        TextView check = findViewById(R.id.textView2);

        String log = ((EditText) findViewById(R.id.reg_login)).getText().toString();
        String pas_1 = ((EditText) findViewById(R.id.reg_password)).getText().toString();
        String pas_2 = ((EditText) findViewById(R.id.reg_password2)).getText().toString();


        dbHelper = new SQLiteSignIn(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.query(SQLiteSignIn.TABLE_CONTACTS, null, null, null, null, null, null);

        if(!pas_1.equals(pas_2) || pas_1.length() < 6){
            check.setText("Пароль не совпадает или слишком короткий(не менее 6 символов)");
        }else {
            if(cursor.moveToFirst()){
                int loginIndex = cursor.getColumnIndex(SQLiteSignIn.KEY_LOGIN);
                do {
                    if(cursor.getString(loginIndex).equals(log)){
                        check.setText("Такой логин уже существует");
                       return;
                    }
                }while(cursor.moveToNext());
            }
            n++;
            contentValues.put(SQLiteSignIn.KEY_LOGIN, log);
            contentValues.put(SQLiteSignIn.KEY_PASSWORD, pas_1);
            db.insert(SQLiteSignIn.TABLE_CONTACTS, null, contentValues);

            setContentView(R.layout.activity_login);
        }

    }

    public void onBackButtonClick(View v) {
        setContentView(R.layout.activity_login);
    }
}