package com.ottego.drugstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

public class SignupActivity extends AppCompatActivity {
    TextInputEditText tietSignupFullName;
    TextInputEditText tietSignupEmail;
    TextInputEditText tietSignupMob;
    TextInputEditText tietSignupPassword;
    MaterialButton mbSignupSubmit;
    String url = Utils.URL + "admin_signup";
    String enterfullname = "";
    String email = "";
    String mobile ="";
    String password = "";


    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


            tietSignupFullName = findViewById(R.id.tietSignupFullName);
            tietSignupEmail = findViewById(R.id.tietSignupEmail);
            tietSignupMob = findViewById(R.id.tietSignupMob);
            tietSignupPassword=findViewById(R.id.tietLoginPassword);
            mbSignupSubmit= findViewById(R.id.mbSignupSubmit);


            sessionManager = new SessionManager(SignupActivity.this);

            mbSignupSubmit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    enterfullname = tietSignupFullName.getText().toString().trim();
                    email = tietSignupEmail.getText().toString().trim();
                    mobile = tietSignupMob.getText().toString().trim();
                    password = tietSignupPassword.getText().toString().trim();
                    signup();
                }

            });

    }
    void signup() {
        final ProgressDialog progressDialog = ProgressDialog.show(SignupActivity.this, null, "Processing...", false, false);

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

                        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(SignupActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SignupActivity.this, "Sorry, something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                error.printStackTrace();
                Toast.makeText(SignupActivity.this, " Please try again.", Toast.LENGTH_SHORT).show();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("enterfullname", mobile);
                params.put("email", mobile);
                params.put("mobile", mobile);
                params.put("password", password);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.myGetMySingleton(SignupActivity.this).myAddToRequest(stringRequest);
    }



}
