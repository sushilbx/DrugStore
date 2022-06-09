package com.ottego.drugstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button mbSignup = findViewById(R.id.SignUpButton);
        BottomNavigationView bottom_navigation = findViewById(R.id.bottom_navigation);

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home: {
                        Intent intent = new Intent(HomeActivity.this, BottomActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.product: {
                        Intent intent = new Intent(HomeActivity.this, ProductListActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.search: {
                        Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.hospital: {
                        Intent intent = new Intent(HomeActivity.this, HospitalActivity.class);
                        startActivity(intent);
                        break;
                    }

                }
                return false;
            }
        });
        mbSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this, SignupActivity.class);
                startActivity(myIntent);
            }
        });


    }
}