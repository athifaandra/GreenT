package com.example.banksampah;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class DetailNewsActivity extends AppCompatActivity {
    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        Toolbar toolbar = findViewById(R.id.appbar_widget_news);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView titleNews = findViewById(R.id.title_news);
        ImageView imageNews = findViewById(R.id.imageNews);
        TextView detailNewsTextView = findViewById(R.id.detail_news_textview);

        News news = getIntent().getParcelableExtra(ITEM_EXTRA);
        if (news != null) {
            Glide.with(this)
                    .load(news.getPhotoUrl())
                    .into(imageNews);
            titleNews.setText(news.getTitle());
            detailNewsTextView.setText(news.getDetail());

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(news.getTitle());
            }
        }
    }
}
