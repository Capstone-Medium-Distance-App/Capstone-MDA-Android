package com.example.capstonedesign.mainFeature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.capstonedesign.R;

public class LocationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        TextView txt_name = findViewById(R.id.txt_locationdetail_name);

        Intent intent = getIntent();
        String mTitle = intent.getStringExtra("title");
        txt_name.setText(mTitle);

    }
}