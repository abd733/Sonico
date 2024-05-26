package com.example.sonicochat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sonicochat.tabslayout.OnImageClickListener;

import java.util.ArrayList;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    ArrayList<MyPhone> phones;
    private OnImageClickListener listener;

    public RecyclerViewAdapter(ArrayList<MyPhone> phones, OnImageClickListener listener) {
        this.phones = phones;
        this.listener = listener;
    }

    public RecyclerViewAdapter(ArrayList<MyPhone> phones) {
        this.phones = phones;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageItem1);
            txt = itemView.findViewById(R.id.textItem1);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("RecyclerViewAdapter", "Clic sur l'image simple détecté");
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 /*  if (listener != null) {
                      //  int imageResourceId = getAdapterPosition();
                         int imageResourceId = phones.get(getAdapterPosition()).getImage();
                        //listener.onImageClick(imageResourceId);
                       // if (imageResourceId != RecyclerView.NO_POSITION){
                            listener.onImageClick(imageResourceId);
                        //}
                    }*/
                    if (listener != null) {
                        MyPhone phone = phones.get(getAdapterPosition());
                        listener.onImageClick(phone);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyPhone phone = phones.get(position);
        holder.img.setImageResource(phone.getImage());
        holder.txt.setText(phone.getText());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onImageClick(phone);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return phones.size();
    }

}
