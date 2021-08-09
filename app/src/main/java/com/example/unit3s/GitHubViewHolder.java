package com.example.unit3s;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

public class GitHubViewHolder extends RecyclerView.ViewHolder {

    private ImageView mimage;
    private TextView mName;
    private TextView mLogin;


    public GitHubViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        initviews(itemView);
    }

    private void initviews(View itemView) {
        mimage = itemView.findViewById(R.id.ivProfile);
        mName = itemView.findViewById(R.id.tvName);
        mLogin = itemView.findViewById(R.id.tvLogin);
    }

    public void setData(ResponseModel responseModel){

        mName.setText(responseModel.getName());
        mLogin.setText(responseModel.getOwner().getLogin());
        Glide.with(mimage).load(responseModel.getOwner().getAvatarUrl()).into(mimage);

    }
}
