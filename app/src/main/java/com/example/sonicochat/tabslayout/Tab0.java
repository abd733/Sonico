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
import com.example.sonicochat.MyPhone;

import java.util.ArrayList;

public class Tab0 extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab0, container, false);

        ArrayList<MyPhone> phones = new ArrayList<>();
        phones.add(new MyPhone(R.drawable.image1,"phone 1"));
        phones.add(new MyPhone(R.drawable.image2,"phone 2"));
        phones.add(new MyPhone(R.drawable.image3,"phone 3"));
        phones.add(new MyPhone(R.drawable.image4,"phone 4"));
        phones.add(new MyPhone(R.drawable.image5,"phone 5"));
        phones.add(new MyPhone(R.drawable.image6,"phone 6"));
        phones.add(new MyPhone(R.drawable.image7,"phone 7"));
        phones.add(new MyPhone(R.drawable.image8jpg,"phone 8"));
        phones.add(new MyPhone(R.drawable.image9,"phone 9"));
        phones.add(new MyPhone(R.drawable.image10,"phone 10"));
        phones.add(new MyPhone(R.drawable.samsung1,"phone 11"));
        phones.add(new MyPhone(R.drawable.samsung2,"phone 12"));
        phones.add(new MyPhone(R.drawable.samsung3,"phone 13"));
        phones.add(new MyPhone(R.drawable.samsung4,"phone 14"));
        phones.add(new MyPhone(R.drawable.samsung5,"phone 15"));
        //phones.add(new MyPhone(R.drawable.samsung6,"phone 16"));
        phones.add(new MyPhone(R.drawable.samsung7,"phone 17"));
        phones.add(new MyPhone(R.drawable.samsung8,"phone 18"));
        phones.add(new MyPhone(R.drawable.samsung9,"phone 19"));
        phones.add(new MyPhone(R.drawable.samsung10,"phone 20"));
        phones.add(new MyPhone(R.drawable.samsung11,"phone 21"));
        phones.add(new MyPhone(R.drawable.samsung12,"phone 22"));
        phones.add(new MyPhone(R.drawable.samsung13,"phone 23"));
        phones.add(new MyPhone(R.drawable.samsung14,"phone 24"));
        phones.add(new MyPhone(R.drawable.samsung15,"phone 25"));
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
        phones.add(new MyPhone(R.drawable.redmi1, "Redmi Note 11"));
        phones.add(new MyPhone(R.drawable.redmi2, "Redmi Note 10"));
        phones.add(new MyPhone(R.drawable.redmi3, "Redmi Note 9"));
        phones.add(new MyPhone(R.drawable.redmi4, "Redmi Note 8"));
        phones.add(new MyPhone(R.drawable.redmi5, "Redmi Note 7"));
        phones.add(new MyPhone(R.drawable.redmi6, "Redmi K40"));
        phones.add(new MyPhone(R.drawable.redmi7, "Redmi K30"));
        phones.add(new MyPhone(R.drawable.redmi8, "Redmi K20"));
        phones.add(new MyPhone(R.drawable.redmi9, "Redmi K10"));
        phones.add(new MyPhone(R.drawable.redmi10, "Redmi 10"));
        phones.add(new MyPhone(R.drawable.redmi11, "Redmi 9"));
        phones.add(new MyPhone(R.drawable.redmi12, "Redmi 8"));
        phones.add(new MyPhone(R.drawable.redmi13, "Redmi 7"));
        phones.add(new MyPhone(R.drawable.redmi14, "Redmi 6"));
        phones.add(new MyPhone(R.drawable.infinix1, "Infinix Hot 11"));
        phones.add(new MyPhone(R.drawable.infinix2, "Infinix Hot 10"));
        phones.add(new MyPhone(R.drawable.infinix3, "Infinix Hot 9"));
        phones.add(new MyPhone(R.drawable.infinix4, "Infinix Hot 8"));
        phones.add(new MyPhone(R.drawable.infinix5, "Redmi Note 7"));
        phones.add(new MyPhone(R.drawable.infinix6, "Infinix Hot 7"));
        phones.add(new MyPhone(R.drawable.infinix7, "Infinix Note 11"));
        phones.add(new MyPhone(R.drawable.infinix8, "Infinix Note 10"));
        phones.add(new MyPhone(R.drawable.infinix9, "Infinix Note 9"));
        phones.add(new MyPhone(R.drawable.infinix10, "Infinix Note 8"));
        phones.add(new MyPhone(R.drawable.infinix11, "Infinix Note 7"));
        phones.add(new MyPhone(R.drawable.infinix12, "Infinix Zero 8"));
        phones.add(new MyPhone(R.drawable.infinix13, "Infinix Zero 7"));
        phones.add(new MyPhone(R.drawable.infinix14, "Infinix Zero 6"));
        phones.add(new MyPhone(R.drawable.infinix14, "Infinix Zero 5"));
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
                intent.putExtra("IMGTAB0", phone.getImage());
                intent.putExtra("TXTTAB0", phone.getText());
                startActivity(intent);
            }
        });
        recyclerView = view.findViewById(R.id.tab0RecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }
}