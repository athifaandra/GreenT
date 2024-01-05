package com.example.banksampah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    ViewPager mSLideViewPager;
    Button backButton, skipButton;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backButton = findViewById(R.id.backButton);
        skipButton = findViewById(R.id.SkipButton);
        mSLideViewPager = findViewById(R.id.slideViewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        mSLideViewPager.setAdapter(viewPagerAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0) > 0) {
                    mSLideViewPager.setCurrentItem(getitem(-1), true);
                }
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPage = mSLideViewPager.getCurrentItem();
                int lastPageIndex = viewPagerAdapter.getCount() - 1;

                if (currentPage == lastPageIndex) {
                    Intent i = new Intent(MainActivity.this, login.class);
                    startActivity(i);
                    finish();
                } else {
                    mSLideViewPager.setCurrentItem(currentPage + 1, true);
                }
            }
        });

        mSLideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position > 0) {
                    backButton.setVisibility(View.VISIBLE);
                } else {
                    backButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private int getitem(int i) {
        return mSLideViewPager.getCurrentItem() + i;
    }
}
