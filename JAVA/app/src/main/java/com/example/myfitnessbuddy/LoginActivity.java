package com.example.myfitnessbuddy;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    //Variables for video background
    private VideoView videoBG;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;

    //Login details
    private EditText username, password;
    private Button btn_login, registerBtn;
    private static String URL_LOGIN = "https://fenestrated-attorne.000webhostapp.com/OmarChaarMobileProject2/login.php";
    ProgressBar Pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btn_login = findViewById(R.id.btLogInButton);
        registerBtn = findViewById(R.id.btSignInButton);
        Pbar = findViewById(R.id.pb);
        Pbar.setVisibility(View.GONE);
        //video background on the first screen
        videoBG = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoBG.setVideoURI(uri);
        videoBG.start();

        videoBG.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                mMediaPlayer.setLooping(true);
                if (mCurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }

            }
        });

        //Buttons code
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.getText().toString().trim().isEmpty() && !password.getText().toString().trim().isEmpty()) {
                    login();
                    Pbar.setVisibility(View.VISIBLE);
                    btn_login.setVisibility(View.GONE);
                    registerBtn.setVisibility(View.GONE);
                    videoBG.setVisibility(View.GONE);

                } else {
                    Toast.makeText(LoginActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    public void login() {

        final String username = this.username.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        StringRequest request = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String id = jsonObject.getString("id");

                            if (success.equals("1")) {
                                SessionData.id = Integer.parseInt(id);
                                SessionData.username = username;
                                Toast.makeText(LoginActivity.this, "Welcome " + username + "!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, Summary.class));

                            }
                            Pbar.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            registerBtn.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();

                            if (e.toString().contains("No value for id")) {
                                Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                                Pbar.setVisibility(View.GONE);
                                btn_login.setVisibility(View.VISIBLE);
                                registerBtn.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(LoginActivity.this, "Error Logging!" + e.toString(), Toast.LENGTH_LONG).show();
                                Pbar.setVisibility(View.GONE);
                                btn_login.setVisibility(View.VISIBLE);
                                registerBtn.setVisibility(View.VISIBLE);
                            }
                            Pbar.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            registerBtn.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error Logging!" + error.toString(), Toast.LENGTH_LONG).show();
                Pbar.setVisibility(View.GONE);
                btn_login.setVisibility(View.VISIBLE);
                registerBtn.setVisibility(View.VISIBLE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mCurrentVideoPosition = mMediaPlayer.getCurrentPosition();
        videoBG.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoBG.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }


}