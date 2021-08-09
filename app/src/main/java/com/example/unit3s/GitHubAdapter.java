package com.example.unit3s;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GitHubAdapter extends RecyclerView.Adapter<GitHubViewHolder> {

    private ArrayList<ResponseModel> responseModels;


    public GitHubAdapter(ArrayList<ResponseModel> responseModels) {
        this.responseModels = responseModels;

    }

    @NonNull
    @NotNull
    @Override
    public GitHubViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return  new GitHubViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull GitHubViewHolder holder, int position) {

        ResponseModel responseModel = responseModels.get(position);
        holder.setData(responseModel);
    }

    @Override
    public int getItemCount() {
        return responseModels.size();
    }

    public void updateData(ArrayList<ResponseModel> responseModels){
        this.responseModels = responseModels;
        notifyDataSetChanged();
    }
}
