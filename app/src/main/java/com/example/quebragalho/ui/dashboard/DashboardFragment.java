package com.example.quebragalho.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.quebragalho.R;
import com.example.quebragalho.ServiceIndexActivity;
import com.example.quebragalho.ServiceViewActivity;
import com.example.quebragalho.databinding.FragmentDashboardBinding;
import com.example.quebragalho.ui.profile.SettingsActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private ListView jobsList = null;
    private ArrayAdapter<String> adapter = null;
    private String[] mockedJobs = {"Primeiro", "Segundo", "Terceiro", "Terceiro", "Terceiro", "Terceiro", "Terceiro", };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View view= binding.getRoot();

        //View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ListView listView = view.findViewById(R.id.services_list_view);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mockedJobs
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ServiceViewActivity.class);;
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}