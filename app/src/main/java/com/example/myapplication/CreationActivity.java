package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreationActivity extends AppCompatActivity {
    /**
     * The main activity where the user presses a button and takes them to
     * the next activity (AddUserToGroup).
     *
     * We need to figure out how to run it only once, which will be the first time the app
     * is installed.
     */
    Button create_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_creation);

        create_group = findViewById(R.id.create_group);

        create_group.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(CreationActivity.this, AddUserToGroup.class);
                startActivity(intent);
            }
        });
        /**
         * Test commit.
         */


    }

}
