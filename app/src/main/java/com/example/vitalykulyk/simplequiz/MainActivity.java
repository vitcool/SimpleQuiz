package com.example.vitalykulyk.simplequiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.vitalykulyk.simplequiz.databinding.MainActivityBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String DB_NAME = "yourdb.sqlite3";

    //defining database field names as constants
    private static final String TABLE_NAME = "questions";
    private static final String QUESTION_ID = "_id";
    private static final String QUESTION_NAME = "question";

    //SQLite database
    private SQLiteDatabase database;
    private ListView listView;
    private ArrayList<String> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        //key helper
        DbOpenHelper dbOpenHelper = new DbOpenHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();

        //the database is open
        fillQuestions();
        show();

        Question question = new Question("Ваша точка зрения в общественных местах:",
                questions.get(0).toString(), questions.get(1).toString(),questions.get(2).toString(),questions.get(3).toString());
        binding.setQuestion(question);
    }

    //Extracting elements from the database
    private void fillQuestions() {
        questions = new ArrayList<String>();
        Cursor questionCursor = database.query(TABLE_NAME,
                new String[]
                        {QUESTION_ID, QUESTION_NAME},
                null, null, null, null
                , QUESTION_NAME);
        questionCursor.moveToFirst();
        if(!questionCursor.isAfterLast()) {
            do {
                String name = questionCursor.getString(1);
                questions.add(name);
            } while (questionCursor.moveToNext());
        }
        questionCursor.close();
    }

    //write to log questions from database
    private void show(){
        for (int i=0; i < questions.size(); i++){
            Log.i("Database element " + i, questions.get(i).toString());
        }
    }

}
