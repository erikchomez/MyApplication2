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
        printLinearNames(group_of_users, userArray);

        //DO SOMETHING WITH THE ArrayList OF CreateNewUsers HERE FOR THE SIDEBAR

        //Look at the day of the week
        resetValuesOnMonday();

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
    private ArrayList<CreateNewUser> printLinearNames(ArrayList<String> group_of_users, ArrayList<CreateNewUser> userArray) {
        tableLayout = findViewById(R.id.simpleTableLayout);
        TextView currentName;

        for(int i = 0; i < group_of_users.size(); i++) {
            TableRow tR = new TableRow(this);
            currentName = new TextView(this);
            currentName.setText(group_of_users.get(i));
            currentName.setTextSize(30);
            tR.setMinimumHeight(150);
            tR.addView(currentName);

            userArray.add(assignUserToClass(group_of_users, i));

            //add buttons in the correct row here
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

    public void resetValuesOnMonday(){
        Calendar currentDate = Calendar.getInstance();

        if(currentDate.get(Calendar.DAY_OF_WEEK) == 2){
            resetSquares();//WORK ON AND FINISH
        }

    }

    public void resetSquares(){
        ///FINISH
    }
}
