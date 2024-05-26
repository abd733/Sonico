package com.example.sonicochat.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sonicochat.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdpter extends RecyclerView.Adapter<UserAdpter.ViewHolder> {
    Context context;
    ArrayList<Users> usersArrayList;

    public UserAdpter(Context context, ArrayList<Users> usersArrayList) {
        this.context = context;
        this.usersArrayList = usersArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users user = usersArrayList.get(position);
        holder.username.setText(user.userName);
        holder.email.setText(user.mail);
        Picasso.get().load(user.profilepic).into(holder.userimg);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, Chatwindo.class);
            intent.putExtra("nameeee", user.getUserName());
            intent.putExtra("receiverImg", user.getProfilepic());
            intent.putExtra("uid", user.getUserId());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView userimg;
        TextView username, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userimg = itemView.findViewById(R.id.userimg);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.userstatus);
        }
    }
}
