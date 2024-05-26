package com.example.sonicochat.database;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.sonicochat.database.Chatwindo.receiverImgUrl;
import static com.example.sonicochat.database.Chatwindo.senderImg;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sonicochat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<MsgModelclass> messagesAdpterArrayList;
    int ITEM_SEND = 1;
    int ITEM_RECIVE = 2;

    public MessagesAdpter(Context context, ArrayList<MsgModelclass> messagesAdpterArrayList) {
        this.context = context;
        this.messagesAdpterArrayList = messagesAdpterArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == ITEM_SEND) {
            View view = inflater.inflate(R.layout.sender_layout, parent, false);
            return new SenderViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.reciver_layout, parent, false);
            return new ReciverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MsgModelclass messages = messagesAdpterArrayList.get(position);
        if (holder instanceof SenderViewHolder) {
            ((SenderViewHolder) holder).bind(messages);
        } else {
            ((ReciverViewHolder) holder).bind(messages);
        }
    }

    @Override
    public int getItemCount() {
        return messagesAdpterArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        MsgModelclass messages = messagesAdpterArrayList.get(position);
        return FirebaseAuth.getInstance().getCurrentUser().getUid().equals(messages.getSenderid()) ? ITEM_SEND : ITEM_RECIVE;
    }

    class SenderViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView msgtxt;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.profilerggg);
            msgtxt = itemView.findViewById(R.id.msgsendertyp);
        }

        void bind(MsgModelclass message) {
            msgtxt.setText(message.getMessage());
            loadImage(senderImg, circleImageView);
        }
    }

    class ReciverViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView msgtxt;

        public ReciverViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.pro);
            msgtxt = itemView.findViewById(R.id.recivertextset);
        }

        void bind(MsgModelclass message) {
            msgtxt.setText(message.getMessage());
            loadImage(receiverImgUrl, circleImageView);
        }
    }

    private void loadImage(String url, CircleImageView imageView) {
        if (url != null && !url.isEmpty()) {
            Picasso.get().load(url).into(imageView);
        } else {
            imageView.setImageResource(R.drawable.person_icon); // Default image
        }
    }
}

