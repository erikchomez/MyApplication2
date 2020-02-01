package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity{
    /**
     * This is going to be the activity where we will be doing the bulk of the work.
     * Creating and tracking the activity tracker for the group.
     */
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    public void init() {
        tableLayout = findViewById(R.id.simpleTableLayout);

        for(int i = 1; i < 3; i++) {
            TableRow tR = new TableRow(this);
            Button new_button = new Button(this);
            tR.addView(new_button);

            tableLayout.addView(tR,i);
        }

    }
}


