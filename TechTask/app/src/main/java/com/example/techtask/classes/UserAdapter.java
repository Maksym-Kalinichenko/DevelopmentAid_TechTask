package com.example.techtask.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.techtask.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    private List<Users> usersList;
    private Context context;

    public UserAdapter(List<Users> usersList, Context context) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.mName.setText(usersList.get(position).getName());
        holder.mLocation.setText(usersList.get(position).getLocation());
        holder.mAnswer.setText(String.valueOf(usersList.get(position).getAnswer()));
        holder.mQuestion.setText(String.valueOf(usersList.get(position).getQuestion()));
        holder.mTags.setText(usersList.get(position).getTag());
        holder.mProfile.setText(usersList.get(position).getProfile());
        Glide.with(context).load(usersList.get(position).getAvatar()).into(holder.mAvatar);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName, mLocation, mAnswer, mQuestion, mTags, mProfile;
        public ImageView mAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.userName);
            mLocation = itemView.findViewById(R.id.userLocation);
            mAnswer = itemView.findViewById(R.id.userAnswer);
            mQuestion = itemView.findViewById(R.id.userQuestion);
            mTags = itemView.findViewById(R.id.userTag);
            mProfile = itemView.findViewById(R.id.userProfile);
            mAvatar = itemView.findViewById(R.id.userAvatar);
        }
    }
}
