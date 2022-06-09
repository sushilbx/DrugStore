package com.ottego.drugstore;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;


public class RemoveCartFragment extends DialogFragment {
    String url = "http://ottego.com/api/store/remove_cart";
    Context context;
    SessionManager sessionManager;
    MaterialButton mbRemoveCart;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RemoveCartFragment() {

    }


    public static RemoveCartFragment newInstance(String param1, String param2) {
        RemoveCartFragment fragment = new RemoveCartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remove_cart, container, false);
        context = getContext();
        sessionManager = new SessionManager(context);
        fromXml(view);
        return view;
    }

    private void fromXml(View view) {
        mbRemoveCart = view.findViewById(R.id.mbRemoveCart);
        mbRemoveCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeCart();
                dismiss();
            }
        });
    }

    private void removeCart() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                ((CartActivity)requireActivity()).getcart();

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
                params.put("id", mParam1);
                Log.e("params", params.toString());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.myGetMySingleton(context).myAddToRequest(stringRequest);
    }
}