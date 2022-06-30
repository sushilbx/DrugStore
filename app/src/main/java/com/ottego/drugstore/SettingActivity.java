package com.ottego.drugstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        TextView tvSignout = findViewById(R.id.tvSettingSignOut);
        {

            tvSignout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent myIntent = new Intent(SettingActivity.this, LoginActivity.class);
                    startActivity(myIntent);
                }


            });
        }
    }
}