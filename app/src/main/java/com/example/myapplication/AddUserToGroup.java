package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddUserToGroup extends AppCompatActivity {
    /**
     * Second activity where the user adds people to a group.
     */
    ArrayList<String> group_of_users;

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
}
