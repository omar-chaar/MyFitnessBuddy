package com.example.myfitnessbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import org.w3c.dom.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private static String URL = "https://fenestrated-attorne.000webhostapp.com/OmarChaarMobileProject2/profile.php";
    TextView name, created, age, height, sex;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.tvUsername);
        created = findViewById(R.id.tvCreated);
        age = findViewById(R.id.tvAge);
        height = findViewById(R.id.tvHeight);
        sex = findViewById(R.id.tvSex);
        logout = findViewById(R.id.btLogout);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Selected Menu
        bottomNavigationView.setSelectedItemId(R.id.profile);
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
                        startActivity(new Intent(getApplicationContext(), Summary.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });
        name.setText("Hello "+ SessionData.username + "." );
        loadProfile();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionData.id = -1;
                SessionData.username = "";
                Toast.makeText(ProfileActivity.this, "Logging out!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });


    }
    public void loadProfile(){
        final String mId = String.valueOf(SessionData.id);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                String mCreated = jsonObject.getString("created_at");
                                String mAge = jsonObject.getString("age");
                                String mHeight = jsonObject.getString("height");
                                String mSex = jsonObject.getString("sex");
                                created.setText("Account created at: " + mCreated);
                                age.setText("Age: " + mAge + "years.");
                                height.setText("Height: " + mHeight + "cm.");
                                sex.setText("Sex: "+ mSex + ".");

                            } else {
                                String mCreated = jsonObject.getString("created_at");
                                created.setText("Account created at: " + mCreated +".");
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ProfileActivity.this, "Error loading!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this, "Error loading!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", mId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}