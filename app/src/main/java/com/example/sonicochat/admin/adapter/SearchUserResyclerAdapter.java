package com.example.sonicochat.admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sonicochat.R;
import com.example.sonicochat.database.Chatwindo;
import com.example.sonicochat.database.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchUserResyclerAdapter extends FirebaseRecyclerAdapter<Users, SearchUserResyclerAdapter.UserModelViewHolder> {

    private Context context;

    public SearchUserResyclerAdapter(@NonNull FirebaseRecyclerOptions<Users> options, Context context) {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item1, parent, false);
        return new UserModelViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserModelViewHolder holder, int position, @NonNull Users user) {
        if (user != null) {
            holder.username.setText(user.getUserName());
            holder.email.setText(user.getMail());
            Picasso.get().load(user.getProfilepic()).into(holder.userimg);

            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, Chatwindo.class);
                intent.putExtra("nameeee", user.getUserName());
                intent.putExtra("receiverImg", user.getProfilepic());
                intent.putExtra("uid", user.getUserId());
                context.startActivity(intent);
            });
        } else {
            Log.e("AdapterError", "User object at position " + position + " is null");
        }
    }

    public static class UserModelViewHolder extends RecyclerView.ViewHolder {
        CircleImageView userimg;
        TextView username, email;

        public UserModelViewHolder(@NonNull View itemView) {
            super(itemView);
            userimg = itemView.findViewById(R.id.userimg);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.userstatus);
        }
    }
}
