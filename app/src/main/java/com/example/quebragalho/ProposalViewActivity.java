package com.example.probleminha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ProposalViewActivity extends AppCompatActivity {
    JSONObject j = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_view);
        Intent intent = getIntent();
        String content = intent.getStringExtra("service");

        try {
            j = new JSONObject(content);
            setValues();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Button button = findViewById(R.id.accept_proposal_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFinishServiceActivity();
            }
        });
    }

    private void setValues() throws JSONException {
        TextView view = findViewById(R.id.text_view_service_title);
        view.setText(j.getString("title"));
        view = findViewById(R.id.text_view_service_description);
        view.setText(j.getString("description"));
        view = findViewById(R.id.text_view_service_date);
        view.setText("07/07/2021");
        view = findViewById(R.id.text_view_service_value);
        view.setText("R$ 30,00");
    }
    private void openFinishServiceActivity() {
        Intent intent = new Intent(this, FinishServiceActivity.class);
        startActivity(intent);
    }

}