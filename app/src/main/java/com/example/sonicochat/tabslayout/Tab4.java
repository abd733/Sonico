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

public class Tab4 extends Fragment {
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab4, container, false);

        ArrayList<MyPhone> phones = new ArrayList<>();
        phones.add(new MyPhone(R.drawable.huawei1, "Huawei P50"));
        phones.add(new MyPhone(R.drawable.huawei2, "Huawei P40"));
        phones.add(new MyPhone(R.drawable.huawei3, "Huawei P30"));
        phones.add(new MyPhone(R.drawable.huawei4, "Huawei P20"));
        phones.add(new MyPhone(R.drawable.huawei5, "Huawei P10"));
        phones.add(new MyPhone(R.drawable.huawei6, "Huawei Mate 40"));
        phones.add(new MyPhone(R.drawable.huawei7, "Huawei Mate 30"));
        phones.add(new MyPhone(R.drawable.huawei8jpg, "Huawei Mate 20"));
        phones.add(new MyPhone(R.drawable.huawei9, "Huawei Mate 10"));
        phones.add(new MyPhone(R.drawable.huawei10, "Huawei Nova 8"));
        phones.add(new MyPhone(R.drawable.huawei11, "Huawei Nova 7"));
        phones.add(new MyPhone(R.drawable.huawei12, "Huawei Nova 6"));
        phones.add(new MyPhone(R.drawable.huawei13, "Huawei Nova 5"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(phones, new OnImageClickListener() {
            public void onImageClick(MyPhone phone) {
                Intent intent = new Intent(getContext(), DetailsPhone.class);
                intent.putExtra("IMGTAB4", phone.getImage());
                intent.putExtra("TXTTAB4", phone.getText());
                startActivity(intent);
            }
        });        recyclerView = v.findViewById(R.id.tab4RecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return v;
    }
}