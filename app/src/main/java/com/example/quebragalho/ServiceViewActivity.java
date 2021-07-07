package com.example.probleminha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_view);

        Button acceptButton = findViewById(R.id.accept_service_button);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNegotiateServiceActivity();
            }
        });
    }

    private void openNegotiateServiceActivity() {
        Intent intent = new Intent(this, NegotiateServiceActivity.class);
        startActivity(intent);
    }

}