package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AddUserToGroup extends AppCompatActivity {
    /**
     * Second activity where the user adds people to a group.
     */
    ArrayList<String> group_of_users;
    static final int READ_BLOCK_SIZE = 100;
    Button addUser, done_adding;
    EditText insertName;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_to_group);

        addUser = findViewById(R.id.add_user);
        done_adding = findViewById(R.id.done_adding);
        insertName = findViewById(R.id.name);

        group_of_users = new ArrayList<>();

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                String user_name = insertName.getText().toString();
                CreateNewUser new_user = new CreateNewUser(user_name);

                group_of_users.add(new_user.getName());
                WriteBtn(insertName);

                insertName.getText().clear();
                Toast.makeText(getApplicationContext(),"User added to the group!",Toast.LENGTH_SHORT).show();

                if(counter == 6) {
                    // once there are 6 users in the group, the button to add a user is no longer clickable
                    addUser.setClickable(false);
                    addUser.setText("No more users can be added.");
                }

            }

        });

        done_adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddUserToGroup.this, MainScreen.class);
                intent.putExtra("key", group_of_users);
                startActivity(intent);

            }
        });

    }

    // write text to file
    public void WriteBtn(EditText v) {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            String name = v.getText().toString();
            //CreateNewUser new_user = new CreateNewUser(name);

            outputWriter.write(name);
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public void ReadBtn(View v) {

        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            insertName.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


