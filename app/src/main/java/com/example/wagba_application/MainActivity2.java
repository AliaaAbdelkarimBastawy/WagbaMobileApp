package com.example.wagba_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
ViewPager VP;
LinearLayout DotLayout;
Button skipBtn;
Intent intent01;
TextView[] dots;
ViewPagerAdapter viewPagerAdapter;
SharedPreferences mSharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setElevation(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mSharedPref = getSharedPreferences("SharedPref", MODE_PRIVATE);
        boolean isFirstTime = mSharedPref.getBoolean("firstTime", true);
        if(isFirstTime)
        {
            SharedPreferences.Editor editor = mSharedPref.edit();
            editor.putBoolean("firstTime", false);
            editor.commit();
        }

        else
        {
            Intent intent = new Intent(MainActivity2.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        skipBtn = findViewById(R.id.btn1);
        intent01 = new Intent (MainActivity2.this, LoginActivity.class);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent01);
                finish();
            }
        });

        VP = findViewById(R.id.viewpager);
        DotLayout = findViewById(R.id.linearLayout);

        viewPagerAdapter = new ViewPagerAdapter(this);

        VP.setAdapter(viewPagerAdapter);
        dotsIndicator(0);
        VP.addOnPageChangeListener(viewListener);

    }

    public void  dotsIndicator (int position)
    {
        dots= new TextView[3];
        DotLayout.removeAllViews();
        for(int i = 0; i< dots.length; i++)
        {
            dots[i]= new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.black));
            DotLayout.addView(dots[i]);
        }

        dots[position].setTextColor((getResources().getColor(R.color.teal_200)));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            dotsIndicator(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}