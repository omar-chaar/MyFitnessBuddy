package com.example.myfitnessbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewEntryActivity extends AppCompatActivity {
    private Button submitButton;
    private EditText caloriesin, caloriesout, weight;
    private static String URL = "https://fenestrated-attorne.000webhostapp.com/OmarChaarMobileProject2/newentry.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        caloriesin = findViewById(R.id.etCaloriesIn);
        caloriesout = findViewById(R.id.etCaloriesOut);
        weight = findViewById(R.id.etWeight);
        submitButton = findViewById(R.id.btSubmit);
        //Code for bottom menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Selected Menu
        bottomNavigationView.setSelectedItemId(R.id.new_entry);
        //Listener for changing activities
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.new_entry:
                        return true;
                    case R.id.summary:
                        startActivity(new Intent(getApplicationContext(), Summary.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        //Button code
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!caloriesin.getText().toString().trim().isEmpty() && !caloriesin.getText().toString().trim().isEmpty() && !weight.getText().toString().trim().isEmpty()) {
                    newEntry();
                } else {
                    Toast.makeText(NewEntryActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void newEntry() {
        final String mCaloriesout = this.caloriesout.getText().toString().trim();
        final String mCaloriesin = this.caloriesin.getText().toString().trim();
        final String mWeight = this.weight.getText().toString().trim();
        final String mId = String.valueOf(SessionData.id);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            caloriesin.getText().clear();
                            caloriesout.getText().clear();
                            weight.getText().clear();
                            Toast.makeText(NewEntryActivity.this, "You added a new entry!", Toast.LENGTH_LONG).show();
                            /*if (success.equals("1")) {
                                Toast.makeText(NewEntryActivity.this, "You added a new entry!", Toast.LENGTH_LONG).show();
                                caloriesin.getText().clear();
                                caloriesout.getText().clear();
                                weight.getText().clear();
                            }*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(NewEntryActivity.this, "Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NewEntryActivity.this, "Error! 2" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("caloriesin", mCaloriesin);
                params.put("caloriesout", mCaloriesout);
                params.put("weight", mWeight);
                params.put("id", mId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}