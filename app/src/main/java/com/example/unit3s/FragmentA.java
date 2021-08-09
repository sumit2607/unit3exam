package com.example.unit3s;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentA extends Fragment {

    private Button mbtnSearch;
    private EditText mSearch;
    private RecyclerView recyclerView;
    private ArrayList<ResponseModel> responseModelArrayList = new ArrayList<>();
    private GitHubAdapter gitHubAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mbtnSearch = view.findViewById(R.id.btnGetData);
        mSearch = view.findViewById(R.id.etSearch);
        recyclerView = view.findViewById(R.id.recyclerView);
        mbtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setrecycleview();
                displayData();
                Toast.makeText(getActivity(),"Hey its working",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setrecycleview() {
        ApiService apiService = NetWork.getInstance().create(ApiService.class);
        Call<ArrayList<ResponseModel>> call = apiService.getData(mSearch.getText().toString());
        call.enqueue(new Callback<ArrayList<ResponseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseModel>> call, Response<ArrayList<ResponseModel>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    responseModelArrayList = response.body();
                    gitHubAdapter.updateData(responseModelArrayList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseModel>> call, Throwable t) {

            }
        });
    }

    private void displayData() {
        gitHubAdapter = new GitHubAdapter(responseModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(gitHubAdapter);
    }
}