package com.alexblackmore.recyclerviewpractise;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView myTV;
    TextView myTV2;
    TextView myTV3;
    ImageView myIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        myTV = findViewById(R.id.detail_recyclerview_TV_wdg);
        myTV2 = findViewById(R.id.detail_recyclerview_TV_wdg2);
        myTV3 = findViewById(R.id.detail_recyclerview_TV_wdg3);
        myIV = findViewById(R.id.detail_sportsImageIV_wdg);

        myTV.setText(getIntent().getStringExtra("title"));
        myTV2.setText(getIntent().getStringExtra("info"));
        myTV3.setText(getIntent().getStringExtra("desc"));
        Glide.with(this).load(getIntent().getIntExtra("image_resource", 0)).into(myIV);

    }
}
