package com.example.quebragalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ProposalsListActivity extends AppCompatActivity {
    private String[] proposalOptions = {"Proposta 1", "Proposta 2", "Proposta 3", "Proposta 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposals_list);
        Button selectFreelancerButton = findViewById(R.id.freelancer_index_sort_button);

        selectFreelancerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProposalViewActivity();
            }
        });

        ListView listView = findViewById(R.id.proposal_list_view);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                proposalOptions
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openProposalViewActivity();
            }
        });
    }

    private void openProposalViewActivity() {
        Intent intent = new Intent(this, ProposalViewActivity.class);
        startActivity(intent);
    }
}