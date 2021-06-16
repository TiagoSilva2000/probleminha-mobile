package com.example.quebragalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NegotiateServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negotiate_service);

        Button sendProposalButton = findViewById(R.id.send_proposal_button);

        sendProposalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                openFreelancerIndexActivity();
            }
        });

        Button acceptProposalButton = findViewById(R.id.accept_proposal_button);

        acceptProposalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFreelancerIndexActivity();
            }
        });
    }

    private void openFreelancerIndexActivity() {
        Intent intent = new Intent(this, FreelancerIndexActivity.class);
        startActivity(intent);
    }

}