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

public class Tab7 extends Fragment {
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_tab7, container, false);
        ArrayList<MyPhone> phones = new ArrayList<>();
        phones.add(new MyPhone(R.drawable.motorola1, "Motorola Edge 30 Pro"));
        phones.add(new MyPhone(R.drawable.motorola2, "Motorola Edge 30"));
        phones.add(new MyPhone(R.drawable.motorola3, "Motorola Edge 20 Pro"));
        phones.add(new MyPhone(R.drawable.motorola4, "Motorola Edge 20"));
        phones.add(new MyPhone(R.drawable.motorola5, "Moto G Stylus (2022)"));
        phones.add(new MyPhone(R.drawable.motorola6, "Moto G Power (2022)"));
        phones.add(new MyPhone(R.drawable.motorola7, "Moto G Pure"));
        phones.add(new MyPhone(R.drawable.motorola8, "Moto G Play (2022)"));
        phones.add(new MyPhone(R.drawable.motorola9, "Moto G Power (2021)"));
        phones.add(new MyPhone(R.drawable.motorola10, "Moto G Stylus (2021)"));
        phones.add(new MyPhone(R.drawable.motorola11, "Moto G Play (2021)"));
        phones.add(new MyPhone(R.drawable.motorola12, "Moto G Power (2020)"));
        phones.add(new MyPhone(R.drawable.motorola13, "Moto G Stylus (2020)"));
        phones.add(new MyPhone(R.drawable.motorola14, "Moto G8 Power Lite"));
        phones.add(new MyPhone(R.drawable.motorola15, "Motorola Razr 5G"));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(phones, new OnImageClickListener() {
            public void onImageClick(MyPhone phone) {
                Intent intent = new Intent(getContext(), DetailsPhone.class);
                intent.putExtra("IMGTAB7", phone.getImage());
                intent.putExtra("TXTTAB7", phone.getText());
                startActivity(intent);
            }
        });        recyclerView = v.findViewById(R.id.tab7RecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return v;
    }
}