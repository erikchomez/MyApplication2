package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddUserToGroup extends AppCompatActivity {
    /**
     * Second activity where the user adds people to a group.
     */

    ArrayList<CreateNewUser> group_of_users;

    Button addUser, done_adding;
    EditText insertName;

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
                String user_name = insertName.getText().toString();
                CreateNewUser new_user = new CreateNewUser(user_name);

                group_of_users.add(new_user);

                insertName.getText().clear();
                Toast.makeText(getApplicationContext(),"User added to the group!",Toast.LENGTH_SHORT).show();

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
