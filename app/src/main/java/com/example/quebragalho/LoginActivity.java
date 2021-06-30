package com.example.quebragalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.io.IOUtil;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        Button loginButton = findViewById(R.id.login_button_id);
        TextView recoverPasswordText = findViewById(R.id.forgot_password_answer);
        TextView signupText = findViewById(R.id.signup_answer);


        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignup();
            }
        });

        recoverPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgot();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "http://10.0.2.2:5001/auth/login";
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);


                JSONObject json = null;

                String resp=null;
                try {
                    JSONObject j = new JSONObject();
                    j.put("email", getEmail());
                    j.put("password", getPassword());
                    URL url1 = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");
                    try(OutputStream os = conn.getOutputStream()) {
                        String content = j.toString();
                        byte[] input = content.getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }
                    conn.connect();
                    InputStream inputStream = conn.getInputStream();
                    resp = IOUtil.toString(inputStream);
                    json = new JSONObject(resp);

                    System.out.println(json);
                    JSONObject user = (JSONObject) json.get("user");
                    Log.i("Teste", user.toString());
                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(getString(R.string.user_id_key), (int)user.get("id"));
                    editor.putString(getString(R.string.user_role_key), (String) user.get("type"));

                    editor.apply();
                    openApp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private String getEmail() {
        EditText et = findViewById(R.id.email_input);
        return et.getText().toString();
    }

    private String getPassword() {
        EditText et = findViewById(R.id.password_input);
        return et.getText().toString();
    }


    private void openApp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openForgot() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    private void openSignup() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}