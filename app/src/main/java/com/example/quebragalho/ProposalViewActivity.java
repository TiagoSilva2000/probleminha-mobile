package com.example.quebragalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProposalViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_view);

        Button button = findViewById(R.id.accept_proposal_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFinishServiceActivity();
            }
        });
    }

    private void openFinishServiceActivity() {
        Intent intent = new Intent(this, FinishServiceActivity.class);
        startActivity(intent);
    }

}