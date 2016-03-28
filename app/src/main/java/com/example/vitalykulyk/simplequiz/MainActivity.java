package com.example.vitalykulyk.simplequiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.vitalykulyk.simplequiz.MainActivityBinding;

public class MainActivity extends AppCompatActivity {
    private static final String DB_NAME = "yourdb.sqlite3";

    //A good practice is to define database field names as constants
    private static final String TABLE_NAME = "questions";
    private static final String FRIEND_ID = "_id";
    private static final String FRIEND_NAME = "question";


    private SQLiteDatabase database;
    private ListView listView;
    private ArrayList<String> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        //final MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        User user = new User("Test", "User");
        binding.setUser(user);

        //Our key helper
        DbOpenHelper dbOpenHelper = new DbOpenHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
        //That’s it, the database is open!
        fillFreinds();
        show();

    }

    //Extracting elements from the database
    private void fillFreinds() {
        friends = new ArrayList<String>();
        Cursor friendCursor = database.query(TABLE_NAME,
                new String[]
                        {FRIEND_ID, FRIEND_NAME},
                null, null, null, null
                , FRIEND_NAME);
        friendCursor.moveToFirst();
        if(!friendCursor.isAfterLast()) {
            do {
                String name = friendCursor.getString(1);
                friends.add(name);
            } while (friendCursor.moveToNext());
        }
        friendCursor.close();
    }

    private void show(){
        for (int i=0; i < friends.size(); i++){
            Log.i("Database element " + i, friends.get(i).toString());
        }
    }


}
