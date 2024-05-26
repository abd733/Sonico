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

public class Tab8 extends Fragment {
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab8, container, false);
        ArrayList<MyPhone> phones = new ArrayList<>();
        phones.add(new MyPhone(R.drawable.sony1, "Sony Xperia 1 III"));
        phones.add(new MyPhone(R.drawable.sony2, "Sony Xperia 1 II"));
        phones.add(new MyPhone(R.drawable.sony3, "Sony Xperia 1"));
        phones.add(new MyPhone(R.drawable.sony4, "Sony Xperia 5 III"));
        phones.add(new MyPhone(R.drawable.sony5, "Sony Xperia 5 II"));
        phones.add(new MyPhone(R.drawable.sony6, "Sony Xperia 5"));
        phones.add(new MyPhone(R.drawable.sony7, "Sony Xperia 10 III"));
        phones.add(new MyPhone(R.drawable.sony8, "Sony Xperia 10 II"));
        phones.add(new MyPhone(R.drawable.sony9, "Sony Xperia 10"));
        phones.add(new MyPhone(R.drawable.sony10, "Sony Xperia 5 Compact (no official release yet as of 2022)"));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(phones, new OnImageClickListener() {
            public void onImageClick(MyPhone phone) {
                Intent intent = new Intent(getContext(), DetailsPhone.class);
                intent.putExtra("IMGTAB8", phone.getImage());
                intent.putExtra("TXTTAB8", phone.getText());
                startActivity(intent);
            }
        });        recyclerView = v.findViewById(R.id.tab8RecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return v;
    }
}