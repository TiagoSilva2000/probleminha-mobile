package com.example.probleminha.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.probleminha.ManageServiceActivity;
import com.example.probleminha.ProposalViewActivity;
import com.example.probleminha.R;
import com.example.probleminha.SignupActivity;
import com.example.probleminha.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.commons.io.IOUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ArrayList<String> services = new ArrayList<String>();
    private ArrayList<JSONObject> items = new ArrayList<JSONObject>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        FloatingActionButton button = view.findViewById(R.id.create_service_floating_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateServiceActivity();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new JSONParse().execute();
            }
        }, 500);

        ListView listView = view.findViewById(R.id.home_list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProposalViewActivity.class);
                intent.putExtra("service", items.get(position).toString());
                startActivity(intent);
            }
        });
        return view;
    }

    private void openCreateServiceActivity() {
        Intent intent = new Intent(getActivity(), ManageServiceActivity.class);
        startActivity(intent);
    }

    private void loadServices(JSONArray json) throws JSONException {
        for (int i = 0; i < json.length(); i++) {
            services.add(json.getJSONObject(i).getString("title"));
            items.add(json.getJSONObject(i));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog= new ProgressDialog(getActivity());

            //  pDialog.setProgressStyle(R.style.CustomActionBarTheme);

            pDialog.setMessage("Obtendo Dados");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONArray json = null;
            json= Json();
            Log.i("Json", "json");
            int count=0;
            try {
                loadServices(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                return json.getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                View view = binding.getRoot();
                pDialog.dismiss();
                ListView listView = view.findViewById(R.id.home_list_view);

                ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        services
                );

                listView.setAdapter(listViewAdapter);
                listView.setVisibility(services.size() > 0 ? View.VISIBLE : View.INVISIBLE);
                view.findViewById(R.id.current_proposals_text).setVisibility(services.size() <= 0 ? View.VISIBLE : View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public JSONArray Json(){
        String resp=null;
        try {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            int user_id = sharedPref.getInt(getString(R.string.user_id_key), 6);
            String user_role = sharedPref.getString(getString(R.string.user_role_key), "customer");
            String path = getString(R.string.api_base_url) + "/job/" + user_role +"/" + user_id;
            URL url1 = new URL(path);

            Log.i("MYURL", path);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream inputStream = conn.getInputStream();
            resp = IOUtil.toString(inputStream);
            JSONArray json = new JSONArray(resp);
            Log.i("Teste", json.toString());
            return json;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}

