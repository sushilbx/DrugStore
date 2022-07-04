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
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    EditText editText;
    Button btnSearch;
    public static final String weburl = "http://www.google.com";


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
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.home: {
                        selectedFragment = new HomeFragment();
                        break;
                    }
                    case R.id.cart: {
                        selectedFragment = new CartFragment();
                        break;
                    }
                    case R.id.search: {
                        selectedFragment = new SearchFragment();
                        break;
                    }
                    case R.id.hospital: {
                        selectedFragment = new HospitalFragment();
                        break;
                    }
                    case R.id.account: {
                        selectedFragment = new AccountFragment();
                        break;
                    }

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.flHomeMain, selectedFragment).commit();
                return true;
            }

        });
        mbSetting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(myIntent);
            }
        });
    }

}

