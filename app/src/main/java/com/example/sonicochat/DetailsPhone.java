package com.example.sonicochat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sonicochat.database.Chatwindo;
import com.example.sonicochat.database.MainActivity;
import com.example.sonicochat.databinding.ActivityDetailsPhoneBinding;

public class DetailsPhone extends AppCompatActivity {
    ActivityDetailsPhoneBinding binding;
    ImageView btnchat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details_phone);
        binding = ActivityDetailsPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_details_phone);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnchat = findViewById(R.id.chats_btn_details);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);*/
        int tab0 = getIntent().getIntExtra("IMGTAB0", 0);
        String txt0 = getIntent().getStringExtra("TXTTAB0");
        int tab1 = getIntent().getIntExtra("IMGTAB1", 0);
        String txt1 = getIntent().getStringExtra("TXTTAB1");
        int tab2 = getIntent().getIntExtra("IMGTAB2", 0);
        String txt2 = getIntent().getStringExtra("TXTTAB2");
        int tab3 = getIntent().getIntExtra("IMGTAB3", 0);
        String txt3 = getIntent().getStringExtra("TXTTAB3");
        int tab4 = getIntent().getIntExtra("IMGTAB4", 0);
        String txt4 = getIntent().getStringExtra("TXTTAB4");
        int tab5 = getIntent().getIntExtra("IMGTAB5", 0);
        String txt5 = getIntent().getStringExtra("TXTTAB5");
        int tab6 = getIntent().getIntExtra("IMGTAB6", 0);
        String txt6 = getIntent().getStringExtra("TXTTAB6");
        int tab7 = getIntent().getIntExtra("IMGTAB7", 0);
        String txt7 = getIntent().getStringExtra("TXTTAB7");
        int tab8 = getIntent().getIntExtra("IMGTAB8", 0);
        String txt8 = getIntent().getStringExtra("TXTTAB8");
        int tab9 = getIntent().getIntExtra("IMGTAB9", 0);
        String txt9 = getIntent().getStringExtra("TXTTAB9");
        int tab10 = getIntent().getIntExtra("IMGTAB10", 0);
        String txt10 = getIntent().getStringExtra("TXTTAB10");
        ImageView imageView = findViewById(R.id.details_img);
        TextView tv_Details = findViewById(R.id.txtDetails);
      if (tab0 != 0) {
            imageView.setImageResource(tab0);
            tv_Details.setText(txt0);
            // Set other details based on the tab ID, if needed
        }else if (tab1 != 0) {
            imageView.setImageResource(tab1);
            tv_Details.setText(txt1);
            // Set other details based on the tab ID, if needed
        }else if (tab2 != 0) {
            imageView.setImageResource(tab2);
            tv_Details.setText(txt2);

          // Set other details based on the tab ID, if needed
        }else if (tab3 != 0) {
            imageView.setImageResource(tab3);
            tv_Details.setText(txt3);

          // Set other details based on the tab ID, if needed
        }else if (tab4 != 0) {
            imageView.setImageResource(tab4);
            tv_Details.setText(txt4);
            // Set other details based on the tab ID, if needed
        }else if (tab5 != 0) {
            imageView.setImageResource(tab5);
            tv_Details.setText(txt5);
            // Set other details based on the tab ID, if needed
        }else if (tab6 != 0) {
            imageView.setImageResource(tab6);
            tv_Details.setText(txt6);
            // Set other details based on the tab ID, if needed
        }else if (tab7 != 0) {
            imageView.setImageResource(tab7);
            tv_Details.setText(txt7);
            // Set other details based on the tab ID, if needed
        }else if (tab8 != 0) {
            imageView.setImageResource(tab8);
            tv_Details.setText(txt8);
            // Set other details based on the tab ID, if needed
        }else if (tab9 != 0) {
            imageView.setImageResource(tab9);
             tv_Details.setText(txt9);
            // Set other details based on the tab ID, if needed
        }else if (tab10 != 0) {
          imageView.setImageResource(tab10);
          tv_Details.setText(txt10);
          // Set other details based on the tab ID, if needed
      }
      btnchat.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(DetailsPhone.this, Chatwindo.class);
              startActivity(intent);
          }
      });
    }
}