package com.example.probleminha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.probleminha.ui.profile.SettingsActivity;

public class FreelancerIndexActivity extends AppCompatActivity {
    private String[] freelancerOptions = {"Freelancer 1", "Freelancer 2", "Freelancer 3", "Freelancer 4"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_index);

        Button selectFreelancerButton = findViewById(R.id.freelancer_index_sort_button);

        selectFreelancerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProposalListActivity();
            }
        });

        ListView listView = findViewById(R.id.freelancer_list_view);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                freelancerOptions
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               openProposalListActivity();
            }
        });
    }

    private void openProposalListActivity() {
        Intent intent = new Intent(this, ProposalsListActivity.class);
        startActivity(intent);
    }

}