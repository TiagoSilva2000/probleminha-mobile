package com.example.quebragalho.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.quebragalho.SignupActivity;
import com.example.quebragalho.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    private String[] mockedOptions = {"Probleminhas Resolvidos", "Probleminhas Pendentes", "Avalie o nosso app!", "Compartilhe!", "Configurações"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        ListView listView = view.findViewById(R.id.profile_list_view);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mockedOptions
        );

        listView.setAdapter(listViewAdapter);

        Button button = view.findViewById(R.id.exit_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        //intent = new Intent(getActivity(), ServiceIndexActivity.class);
                        //break;
                    case 1:
                        intent = new Intent(getActivity(), ServiceIndexActivity.class);
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "Redirecionando você para Play Store...",
                                Toast.LENGTH_SHORT).show();
                        return;
                    case 3:
                        Toast.makeText(getActivity(), "Link da Play Store copiado para o clipboard!",
                                Toast.LENGTH_SHORT).show();
                        return;
                    case 4:
                        intent = new Intent(getActivity(), SettingsActivity.class);
                        break;
                }
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