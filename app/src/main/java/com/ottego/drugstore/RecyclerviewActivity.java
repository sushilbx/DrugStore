package com.ottego.drugstore;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ottego.drugstore.model.ContactModel;
import com.ottego.drugstore.model.FilmModel;
import com.ottego.drugstore.model.MovieModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecyclerviewActivity extends AppCompatActivity {

    ArrayList<ContactModel> arrayContact = new ArrayList<>();
    String url = "https://howtodoandroid.com/movielist.json";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        recyclerView = findViewById(R.id.recyclerContact);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL ,false));

        ContactModel model = new ContactModel(R.drawable.ic_home, "sushil", "8863043173");
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        arrayContact.add(model);
        /*RecyclerContactAdapter adapter = new RecyclerContactAdapter(this, arrayContact);
        recyclerView.setAdapter(adapter);*/
//        getFilms();
        getMovies();

    }

    private void getMovies() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<MovieModel>>() {
                }.getType();
                ArrayList<MovieModel> movieList = gson.fromJson(response, listType);

                MovieAdapter adapter = new MovieAdapter(RecyclerviewActivity.this, movieList);
                recyclerView.setAdapter(adapter);


            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responseListener, errorListener);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.myGetMySingleton(RecyclerviewActivity.this).myAddToRequest(stringRequest);
    }

    private void getFilms() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<FilmModel>>() {
                }.getType();
                ArrayList<FilmModel> filmList = gson.fromJson(response, listType);

                FilmAdaptor adapter = new FilmAdaptor(RecyclerviewActivity.this, filmList);
                recyclerView.setAdapter(adapter);


            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responseListener, errorListener);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.myGetMySingleton(RecyclerviewActivity.this).myAddToRequest(stringRequest);
    }
}