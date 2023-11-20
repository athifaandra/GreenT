package com.example.banksampah;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;


public class DetailNewsActivity extends AppCompatActivity {
    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        TextView titleNews = findViewById(R.id.title_news);
        ImageView imageNews = findViewById(R.id.imageNews);
        TextView detailNewsTextView = findViewById(R.id.detail_news_textview);

        News news = getIntent().getParcelableExtra(ITEM_EXTRA);
        if (news != null) {
            Glide.with(this)
                    .load(news.getPhoto())
                    .into(imageNews);
            titleNews.setText(news.getTitle());

            // Memisahkan teks detail menjadi paragraf
            String[] paragraphs = news.getDetail().split("\n");
            StringBuilder formattedDetail = new StringBuilder();

            // Menggabungkan setiap paragraf ke dalam StringBuilder
            for (String paragraph : paragraphs) {
                formattedDetail.append(paragraph).append("\n\n");
            }

            detailNewsTextView.setText(formattedDetail.toString());
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail News");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}