package com.ebbcoin.organizer.organizerebbcoin.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ebbcoin.organizer.organizerebbcoin.Manifest;
import com.ebbcoin.organizer.organizerebbcoin.R;

import java.security.Permission;


public class MainActivity extends AppCompatActivity {
    public static Context context;

    private static final int READ_CONTACTS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;




    }


    public void profileClick(View v) {

        Log.d("profile called by-", "profile Click");

    }

    public void onClick(View v) {

        //Log.d("onclick called by-", v.toString());

        //synchronized (semaphore) {
        switch (v.getId()) {
            case R.id.planner_button:
                // do something
                Log.d("R.id.planner", "planner was clicked");

                Intent planner_view = new Intent(this, PlannerViewActivity.class);

                startActivity(planner_view);

                break;
            case R.id.resources_button:
                // do something
                Log.d("R.id.planner", "planner was clicked");

                //Intent resources = new Intent(this, ResourcesViewActivity.class);

                //startActivity(resources);

                break;

            case R.id.profile_button:
                // do something
                Log.d("R.id.Profile", "Profile Icon was clicked");

                //Fragment fragment =
                // Insert the fragment by replacing any existing fragment
                /*
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                               .replace(R.id.MenuBar, fragment)
                               .commit();
                */

                break;


        }

        //}
    }
}
