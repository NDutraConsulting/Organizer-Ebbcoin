package com.ebbcoin.organizer.organizerebbcoin.template;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebbcoin.organizer.organizerebbcoin.R;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_VERTICAL;
import static android.widget.LinearLayout.VERTICAL;

public class ScrollView_MenuBar_Template extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);


        View v = findViewById(R.id.active_scroll_view);


        LinearLayout ll = (LinearLayout) findViewById(R.id.active_scroll_linear_layout);

        int i = 0;
        while (i < 10) {

            String description = "Watering & Weeding";

            View parentLayout = addInfoDisplay("Index:" + i, "Fri, October 30, 2016", "1:00pm - 3:00pm", description, 3);

            ll.addView(parentLayout);
            i++;
        }

    }


    private View addInfoDisplay(String title, String date, String time, String description, int coins) {


        LinearLayout parentLayout = new LinearLayout(this);

        LinearLayout.LayoutParams parent_params = new LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.FILL_PARENT, 700);

        parentLayout.setOrientation(LinearLayout.HORIZONTAL);
        parent_params.setMargins(40, 10, 40, 10);

        parentLayout.setLayoutParams(parent_params);


        //MAINVIEW LAYOUT
        LinearLayout linearLayout1 = new LinearLayout(this);

        LinearLayout.LayoutParams main_params = new LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);

        main_params.gravity = Gravity.CENTER_HORIZONTAL;
        linearLayout1.setOrientation(LinearLayout.VERTICAL);


        //BOTTOM LAYOUT
        LinearLayout linearLayout2 = new LinearLayout(this);

        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams bottom_layout_params = new LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);


        //Nest the layouts
        parentLayout.addView(linearLayout1, main_params);


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);


        parentLayout.setBackgroundResource(R.drawable.info_display);
        /*if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            parentLayout.setBackground(this.getResources().getDrawable(R.drawable.info_display));
        }
        else {
            Bitmap bmImg = BitmapFactory.decodeResource(this.getResources(), R.drawable.info_display);
            BitmapDrawable background = new BitmapDrawable(bmImg);
            parentLayout.setBackgroundDrawable(background);
        }*/


        int title_font_size = 20;
        int title_left_margin = 70;
        int title_top_margin = 50;
        //Title
        setText(title, title_font_size, title_left_margin, title_top_margin, linearLayout1);


        int description_left_margin = title_left_margin + 30;


        setText(date, title_font_size, description_left_margin, 0, linearLayout1);
        setText(time, title_font_size, description_left_margin, 0, linearLayout1);

        setText(description, title_font_size, description_left_margin, 0, linearLayout1);


        linearLayout1.addView(linearLayout2, bottom_layout_params);


        //Bottom Text View Params
        LinearLayout.LayoutParams tv_bottom_left_params = new LinearLayout.LayoutParams(
                new LinearLayoutCompat.LayoutParams(
                        100,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT));

        tv_bottom_left_params.setMargins(80, 50, 40, 10);

        TextView tv_bottom_left = new TextView(this);

        tv_bottom_left.setTextSize(20);
        tv_bottom_left.setText("Join");
        tv_bottom_left.setGravity(Gravity.CENTER);
        tv_bottom_left_params.weight = .5f;

        tv_bottom_left.setTypeface(null, Typeface.BOLD);
        tv_bottom_left.setBackgroundResource(R.drawable.button_bg1);
        tv_bottom_left.setLayoutParams(tv_bottom_left_params);
        linearLayout2.addView(tv_bottom_left);

        tv_bottom_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // do whatever stuff you wanna do here

                Log.d("Clicked Join", "Join");
            }
        });


        TextView tv_bottom_right = new TextView(this);

        LinearLayout.LayoutParams tv_bottom_right_params = new LinearLayout.LayoutParams(
                new LinearLayoutCompat.LayoutParams(
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT));

        tv_bottom_right_params.setMargins(50, 50, 10, 10);


        tv_bottom_right.setTextSize(20);
        tv_bottom_right.setText(coins + " Coins");
        tv_bottom_right.setGravity(Gravity.CENTER);
        tv_bottom_right_params.weight = .5f;
        tv_bottom_right.setTypeface(null, Typeface.BOLD);
        tv_bottom_right.setLayoutParams(tv_bottom_right_params);

        //tv_bottom_right.setClickable(true);
        tv_bottom_right.setOnClickListener(new View.OnClickListener() {

                                               @Override
                                               public void onClick(View v) {
                                                   // do whatever stuff you wanna do here

                                                   Log.d("Clicked coins", "coins");

                                               }
                                           }

        );


        linearLayout2.addView(tv_bottom_right);


        return parentLayout;


    }


    public void onClick(View v) {
        // do whatever stuff you wanna do here

        Log.d("Clicked coins", "coins");
    }


    private void setText(String title, int font_size, int left_margin, int top_margin, LinearLayout LL) {

        //Title
        LinearLayout.LayoutParams tv_title_params = new LinearLayout.LayoutParams(
                new LinearLayoutCompat.LayoutParams(
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT));

        tv_title_params.setMargins(left_margin, top_margin, 20, 20);


        TextView tv_title = new TextView(this);

        tv_title.setLayoutParams(tv_title_params);

        tv_title.setTextSize(font_size);
        tv_title.setGravity(Gravity.CENTER);

        tv_title.setTypeface(null, Typeface.BOLD);

        tv_title.setText(title);

        LL.addView(tv_title);
    }


    private TextView makeTextView(String str, int font_size, int leftMargin, LinearLayout.LayoutParams tv_title_params) {
        TextView tv_title = new TextView(this);


        tv_title_params.setMargins(leftMargin, 20, 20, 20);
        tv_title.setLayoutParams(tv_title_params);

        tv_title.setTextSize(font_size);
        tv_title.setGravity(Gravity.CENTER);

        tv_title.setText(font_size);

        return tv_title;
    }

    private TextView makeTextView(String str, int font_size, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        TextView tv_title = new TextView(this);

        LinearLayout.LayoutParams tv_title_params = new LinearLayout.LayoutParams(
                new LinearLayoutCompat.LayoutParams(
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT));

        tv_title_params.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        tv_title.setLayoutParams(tv_title_params);

        tv_title.setTextSize(font_size);
        tv_title.setGravity(Gravity.CENTER);

        tv_title.setText(font_size);

        return tv_title;
    }


}



