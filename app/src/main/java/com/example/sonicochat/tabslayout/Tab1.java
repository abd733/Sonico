package com.example.sonicochat.tabslayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sonicochat.DetailsPhone;
import com.example.sonicochat.MyPhone;
import com.example.sonicochat.R;
import com.example.sonicochat.RecyclerViewAdapter;

import java.util.ArrayList;

public class Tab1 extends Fragment {

    private static final int PHONE_KEY = 1;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Ici, tu dois inflater et retourner la vue de ton fragment
        View rootView = inflater.inflate(R.layout.fragment_tab1, container, false);
        //return rootView;

        ArrayList<MyPhone> phones = new ArrayList<>();
        phones.add(new MyPhone(R.drawable.samsung1,"Motorola Edge+"));
        phones.add(new MyPhone(R.drawable.samsung2,"Galaxy S21+"));
        phones.add(new MyPhone(R.drawable.samsung3,"Galaxy S21"));
        phones.add(new MyPhone(R.drawable.samsung4,"Galaxy S20 Ultra"));
        phones.add(new MyPhone(R.drawable.samsung5,"Galaxy S20+"));
        //phones.add(new MyPhone(R.drawable.samsung6,"phone 16"));
        phones.add(new MyPhone(R.drawable.samsung7,"Galaxy Note 20"));
        phones.add(new MyPhone(R.drawable.samsung8,"Galaxy Note 10+ 5G"));
        phones.add(new MyPhone(R.drawable.samsung9,"Galaxy Note 10+"));
        phones.add(new MyPhone(R.drawable.samsung10,"Galaxy Note 8"));
        phones.add(new MyPhone(R.drawable.samsung11,"Galaxy A72"));
        phones.add(new MyPhone(R.drawable.samsung12,"Galaxy A52 5G"));
        phones.add(new MyPhone(R.drawable.samsung13,"Galaxy A52"));
        phones.add(new MyPhone(R.drawable.samsung14,"Galaxy A42 5G"));
        phones.add(new MyPhone(R.drawable.samsung15,"Galaxy A32 5G"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(phones, new OnImageClickListener() {
           /* @Override
            public void onImageClick(int imageResourceId) {
                Intent intent = new Intent(getContext(), DetailsPhone.class);
                intent.putExtra("IMAGE_ID", imageResourceId);
                startActivity(intent);
            }*/
           public void onImageClick(MyPhone phone) {
               Intent intent = new Intent(getContext(), DetailsPhone.class);
               intent.putExtra("IMGTAB1", phone.getImage());
               intent.putExtra("TXTTAB1", phone.getText());
               startActivity(intent);
           }
        });



        recyclerView = rootView.findViewById(R.id.tab1RecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

   /* @Override
    public void onImageClick(int imageResourceId) {
        if (imageResourceId == R.drawable.samsung1) {
            Intent intent = new Intent(getContext(), DetailsPhone.class);
            intent.putExtra(String.valueOf(PHONE_KEY),imageResourceId);
            startActivity(intent);
        } else if (imageResourceId == R.drawable.samsung2) {
            Toast.makeText(getContext(),"samsung 2 is clicked",Toast.LENGTH_LONG).show();
        }
    }*/
}