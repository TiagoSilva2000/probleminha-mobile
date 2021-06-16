package com.example.quebragalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
                //Context context = getBaseContext();
                //SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.stored_services), Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPref.edit();
                //editor.putString(getString(R.string.stored_services), "Nome do Serviço criado");
                //editor.apply();
                //openServiceViewActivity();
                finish();
            }
        });
    }
    private void openServiceViewActivity() {
        Intent intent = new Intent(this, ServiceViewActivity.class);
        startActivity(intent);
    }
}