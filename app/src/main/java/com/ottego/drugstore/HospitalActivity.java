package com.ottego.drugstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.ottego.drugstore.model.ContactModel;
import com.ottego.drugstore.model.GetHospitalModel;
import com.ottego.drugstore.model.GetProductModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HospitalActivity extends AppCompatActivity {
    String url = "http://ottego.com/api/store/hospital_list";
    RecyclerView recyclerView;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        sessionManager = new SessionManager(this);
        recyclerView = findViewById(R.id.recyclerHospital);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getHospital();
    }
    private void getHospital() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response" ,response);
                Gson gson = new Gson();

                GetHospitalModel hospitalList = gson.fromJson(response, GetHospitalModel.class);
                if (hospitalList.status) {

                    HospitalAdaptor adapter = new HospitalAdaptor(HospitalActivity.this, hospitalList.data);
                    recyclerView.setAdapter(adapter);
                }

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responseListener, errorListener) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", sessionManager.getApiKey());
                params.put("api_secret", sessionManager.getApiSecret());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.myGetMySingleton(HospitalActivity.this).myAddToRequest(stringRequest);
    }
}