package com.example.sonicochat.tabslayout;

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

public class Tab5 extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab5, container, false);
        ArrayList<MyPhone> phones = new ArrayList<>();
        phones.add(new MyPhone(R.drawable.plus1, "OnePlus 9 Pro"));
        phones.add(new MyPhone(R.drawable.plus2, "OnePlus 9"));
        phones.add(new MyPhone(R.drawable.plus3, "OnePlus 9R"));
        phones.add(new MyPhone(R.drawable.plus4, "OnePlus 8 Pro"));
        phones.add(new MyPhone(R.drawable.plus5, "OnePlus 8T"));
        phones.add(new MyPhone(R.drawable.plus6, "OnePlus 8"));
        phones.add(new MyPhone(R.drawable.plus7, "OnePlus 7 Pro"));
        phones.add(new MyPhone(R.drawable.plus8, "OnePlus 7T"));
        phones.add(new MyPhone(R.drawable.plus9, "OnePlus 7"));
        phones.add(new MyPhone(R.drawable.plus10, "OnePlus Nord 2"));
        phones.add(new MyPhone(R.drawable.plus11, "OnePlus Nord CE"));
        phones.add(new MyPhone(R.drawable.plus12, "OnePlus Nord N200"));
        phones.add(new MyPhone(R.drawable.plus13, "OnePlus 6T"));
        phones.add(new MyPhone(R.drawable.plus14, "OnePlus 6"));
        phones.add(new MyPhone(R.drawable.plus15, "OnePlus 5T"));
        phones.add(new MyPhone(R.drawable.plus16, "OnePlus 5"));
        phones.add(new MyPhone(R.drawable.plus17, "OnePlus Nord N10 5G"));
        phones.add(new MyPhone(R.drawable.plus18, "OnePlus Nord N100"));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(phones, new OnImageClickListener() {
            public void onImageClick(MyPhone phone) {
                Intent intent = new Intent(getContext(), DetailsPhone.class);
                intent.putExtra("IMGTAB5", phone.getImage());
                intent.putExtra("TXTTAB5", phone.getText());
                startActivity(intent);
            }
        });        recyclerView = v.findViewById(R.id.tab5RecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return v;
    }
}