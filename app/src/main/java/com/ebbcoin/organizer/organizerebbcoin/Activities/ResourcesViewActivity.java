package com.ebbcoin.organizer.organizerebbcoin.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ebbcoin.organizer.organizerebbcoin.R;
import com.ebbcoin.organizer.organizerebbcoin.template.ScrollView_MenuBar_Template;

public class ResourcesViewActivity extends AppCompatActivity {
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
    }



        public void profileClick(View v) {

            Log.d("profile called by-", "profile Click");

        }

        public void onClick(View v) {

            //Log.d("onclick called by-", v.toString());

            switch (v.getId()) {
                case R.id.manage_volunteers_button:
                    // do something
                    Log.d("R.id.planner", "planner was clicked");

                    Intent intent = new Intent(this, ScrollView_MenuBar_Template.class);

                    startActivity(intent);

                    break;

                case R.id.profile_button:
                    // do something
                    Log.d("R.id.Profile", "Profile Icon was clicked");


                    break;


            }


        }



}
