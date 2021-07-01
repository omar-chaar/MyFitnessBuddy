package com.example.myfitnessbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Summary extends AppCompatActivity {
    String URL_LINK = "https://fenestrated-attorne.000webhostapp.com/OmarChaarMobileProject2/summary.php";
    TextView tvSummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSummary = findViewById(R.id.tvSummary);
        //Initializing variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Code for bottom navigation
        bottomNavigationView.setSelectedItemId(R.id.summary);
        //Listener for changing activities
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.new_entry:
                        startActivity(new Intent(getApplicationContext(), NewEntryActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.summary:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        loadSummary();

    }

    //Taking json file when loads
    public void loadSummary() {
        final String id = String.valueOf(SessionData.id);
        StringRequest request = new StringRequest(Request.Method.POST, URL_LINK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            String displayString = "";

                            for (int i = 0; i < jsonArray.length() ; i++){
                                JSONObject tempObj =  jsonArray.getJSONObject(i);
                                displayString = displayString.concat("Summary of day: ");
                                displayString = displayString.concat(String.valueOf(tempObj.get("date")));
                                displayString = displayString.concat(", weight: ");
                                displayString = displayString.concat(String.valueOf(tempObj.get("weight")));
                                displayString = displayString.concat(", calories eaten: ");
                                displayString = displayString.concat(String.valueOf(tempObj.get("calories")));
                                displayString = displayString.concat(", calories burned: ");
                                displayString = displayString.concat(String.valueOf(tempObj.get("burnedCalories")));
                                displayString = displayString.concat("\n");
                                displayString = displayString.concat("\n");
                            }
                            tvSummary.setText(displayString);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Summary.this, "Error Loading!" + error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}
