package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.TableRow;

import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;//USE THIS

import android.graphics.Color;

public class MainScreen extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<CreateNewUser> userArray = new ArrayList<CreateNewUser>();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Intent intent = getIntent();
        ArrayList<String> group_of_users = intent.getStringArrayListExtra("key");
        displayElements(group_of_users, userArray);

        //DO SOMETHING WITH THE ArrayList OF CreateNewUsers HERE FOR THE SIDEBAR

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    private CreateNewUser assignUserToClass(ArrayList<String> group_of_users, int index){
        //Constructs a user object from the name given at the index given and puts it in a list
        //ArrayList<CreateNewUser> userArray = new ArrayList<CreateNewUser>();
        //userArray.add(new CreateNewUser(group_of_users.get(index)));
        CreateNewUser user = new CreateNewUser(group_of_users.get(index));
        return user;
    }
    private ArrayList<CreateNewUser> displayElements(ArrayList<String> group_of_users, ArrayList<CreateNewUser> userArray) {
        tableLayout = findViewById(R.id.simpleTableLayout);
        TextView currentName;

        for(int i = 0; i < group_of_users.size(); i++) {
            //prints each name and set of squares on the tableLayout
            TableRow tR = new TableRow(this);
            currentName = new TextView(this);
            currentName.setText(group_of_users.get(i));
            currentName.setTextSize(30);
            tR.setMinimumHeight(150);
            tR.addView(currentName);

            //ADD MORE SQUARES TO tR
            userArray.add(assignUserToClass(group_of_users, i));

            //Reset the squares if the day is monday to start the new week
            resetValuesOnMonday(userArray.get(i));

            //add buttons in the correct row here
            //read attendanceArray values to print
            //-1 -- Red -- Missed a gym day
            //0 -- Grey -- Default
            //1 -- Green -- Went to the Gym
            //2 -- Orange -- Free Pass
            createAttendanceVisual(tR, userArray.get(i));
            tableLayout.addView(tR,i+1);
        }

        return userArray;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void createAttendanceVisual(TableRow tR, CreateNewUser user){
        for(int i = 0; i < user.getAttendanceArray().size(); i++){
            Button square = new Button(this);
            if(user.getAttendanceArray().get(i) == 1)
            {
                square.setBackgroundColor(Color.GREEN);
            }
            if(user.getAttendanceArray().get(i) == -1)
            {
                square.setBackgroundColor(Color.RED);
            }
            if(user.getAttendanceArray().get(i) == 2)
            {
                square.setBackgroundColor(Color.MAGENTA);
            }
            else
            {
                square.setBackgroundColor(Color.LTGRAY);
            }
            square.setHeight(50); //FIXME
            square.setWidth(25); //FIXME does not change the default width
            tR.addView(square);
        }
    }

    private void resetValuesOnMonday(CreateNewUser user){
        Calendar currentDate = Calendar.getInstance();

        if(currentDate.get(Calendar.DAY_OF_WEEK) == 2) {
            resetSquares(user);//WORK ON AND FINISH
        }
    }

    private void resetSquares(CreateNewUser user){
        //set the all values in attendanceArray to 0
        for(int x = 0; x < 6; x++){
            user.setAttendanceArray(0, x);
        }

    }
}
