package com.example.quebragalho.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.quebragalho.ManageServiceActivity;
import com.example.quebragalho.R;
import com.example.quebragalho.SignupActivity;
import com.example.quebragalho.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ArrayList<String> services = new ArrayList<String>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        ListView listView = view.findViewById(R.id.home_list_view);

        loadServices();
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                services
        );

        listView.setAdapter(listViewAdapter);

        listView.setVisibility(services.size() > 0 ? View.VISIBLE : View.INVISIBLE);
        FloatingActionButton button = view.findViewById(R.id.create_service_floating_button);

        view.findViewById(R.id.current_proposals_text).setVisibility(services.size() <= 0 ? View.VISIBLE : View.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateServiceActivity();
            }
        });
        return view;
    }

    private void openCreateServiceActivity() {
        Intent intent = new Intent(getActivity(), ManageServiceActivity.class);
        startActivity(intent);
    }

    private void loadServices() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String storedService = getResources().getString(R.string.stored_services);
        String normal = sharedPref.getString(getString(R.string.stored_services), storedService);
        if (!normal.equals(storedService)) {
            services.add(storedService);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}