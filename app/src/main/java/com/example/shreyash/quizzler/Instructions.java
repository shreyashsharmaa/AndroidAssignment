package com.example.shreyash.quizzler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Instructions extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        String[] inst={"Welcome to Quizzler!","1.Each game  consists of 10 questions","2.Each question will have two choices True/False","3.Each question will belong to different random category (Science,Entertainmaint etc.) and random difficulty level(easy,meadium,hard) ", "4.For Each Correct Question you will get 10 points  " ,"4.Results will displayed at the end of each game "};
        ArrayAdapter <String> adapter  = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,inst);
        ListView listView = findViewById(R.id.listView);
    }
}
