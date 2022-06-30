package com.ottego.drugstore;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    EditText editText;
    Button btnSearch;
    public static final String weburl ="http://www.google.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button mbSetting = findViewById(R.id.mbSettingButton);
        BottomNavigationView bottom_navigation = findViewById(R.id.bottom_navigation);
        editText = findViewById(R.id.editText);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                String term = editText.getText().toString();
                intent.putExtra(SearchManager.QUERY, term);
                startActivity(intent);
            }



        });


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
        mbSetting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(myIntent);
            }
        });
    }
    public void  loadWebPage (View view) {

        WebViewFragment webViewFragment = new WebViewFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.rlFragmentContainer, webViewFragment).commit();
    }
}

