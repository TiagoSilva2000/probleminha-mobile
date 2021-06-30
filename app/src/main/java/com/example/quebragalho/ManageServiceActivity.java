package com.example.quebragalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import org.apache.commons.io.IOUtil;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ManageServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_service_activity);

        Button cancelButton = findViewById(R.id.cancel_create_service_button);
        Button createButton = findViewById(R.id.create_service_button);

        Spinner spinner = (Spinner) findViewById(R.id.service_tags_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.service_tags, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject json = null;
                String path = "http://10.0.2.2:5001/job/6/create";
                String resp=null;
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try {
                    JSONObject j = new JSONObject();
                    j.put("title", getInputTitle());
                    j.put("description", getDescription());
                    j.put("payment_method", "cash");
                    JSONObject proposal = new JSONObject();
                    proposal.put("message", "new");
                    proposal.put("value", getValue());
                    j.put("proposal", proposal);
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
                    JSONObject job = (JSONObject) json.get("job");
                    Log.i("Teste", job.toString());
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getInputTitle() {
        EditText et = findViewById(R.id.edit_text_service_title);

        return et.getText().toString();
    }
    private String getDescription() {
        EditText et = findViewById(R.id.edit_text_service_description);

        return et.getText().toString();
    }

    private String getValue() {
        EditText et = findViewById(R.id.edit_text_service_value);

        return et.getText().toString();
    }

    private void openServiceViewActivity() {
        Intent intent = new Intent(this, ServiceViewActivity.class);
        startActivity(intent);
    }
}