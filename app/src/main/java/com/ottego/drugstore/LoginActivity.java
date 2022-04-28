package com.ottego.drugstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.ottego.drugstore.model.SessionModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText tietLoginEmail;
    TextInputEditText tietLoginPassword;
    MaterialButton mbLoginSubmit;
    String url = Utils.URL + "admin_login";
    String mobile = "";
    String password = "";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tietLoginEmail = findViewById(R.id.tietLoginEmail);
        tietLoginPassword = findViewById(R.id.tietLoginPassword);
        mbLoginSubmit = findViewById(R.id.mbLoginSubmit);

        sessionManager = new SessionManager(LoginActivity.this);

        mbLoginSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mobile = tietLoginEmail.getText().toString().trim();
                password = tietLoginPassword.getText().toString().trim();
                login();
            }

        });


    }

    void login() {
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, null, "Processing...", false, false);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.e("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String code = jsonObject.getString("status");
                    if (code.equalsIgnoreCase("true")) {
                        JSONObject jsonObj = new JSONObject(jsonObject.getString("data"));

                        String id = jsonObj.getString("id");
                        String name = jsonObj.getString("name");
                        String mobile = jsonObj.getString("mobile");
                        String email = jsonObj.getString("email");
                        String gender = jsonObj.getString("gender");

                        Gson gson = new Gson();


                        SessionModel sessionModel = gson.fromJson(String.valueOf(jsonObj), SessionModel.class);


                        sessionManager.createLoginSession(sessionModel);

                        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Sorry, something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                error.printStackTrace();
                Toast.makeText(LoginActivity.this, " Please try again.", Toast.LENGTH_SHORT).show();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mobile", mobile);
                params.put("password", password);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.myGetMySingleton(LoginActivity.this).myAddToRequest(stringRequest);
    }



}
