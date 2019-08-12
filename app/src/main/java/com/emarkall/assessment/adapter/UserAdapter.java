package com.emarkall.assessment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.emarkall.assessment.R;
import com.emarkall.assessment.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomVH> {

    private List<User> users;
    private Context context;

    public UserAdapter(Context context){
        this.users= new ArrayList<>();
        this.context= context;
    }

    @NonNull
    @Override
    public CustomVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomVH(LayoutInflater.from(context).inflate(R.layout.user_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomVH holder, int position) {
        User user= users.get(holder.getAdapterPosition());
        if(user != null){
            holder.txtFirstName.setText(user.getFirstName());
            holder.txtLastName.setText(user.getLastName());
            Glide.with(context).load(user.getAvatarUrl()).into(holder.imgAvatar);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class CustomVH extends RecyclerView.ViewHolder{

        private ImageView imgAvatar;
        private TextView txtFirstName, txtLastName;
        public CustomVH(@NonNull View itemView) {
            super(itemView);
            imgAvatar= itemView.findViewById(R.id.imgAvatar);
            txtFirstName= itemView.findViewById(R.id.txtFirstName);
            txtLastName= itemView.findViewById(R.id.txtLastName);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void addAll(List<User> users) {
        this.users.addAll(users);
    }
}
