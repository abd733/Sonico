package com.example.sonicochat.database;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sonicochat.MainActivity2;
import com.example.sonicochat.R;
import com.example.sonicochat.admin.ChatMainActivity;
import com.example.sonicochat.admin.LoginPhoneNumberActivity;
import com.example.sonicochat.admin.SplashActivity;
import com.example.sonicochat.admin.utils.FirebaseUtils;
import com.google.firebase.FirebaseApp;

public class Splash extends AppCompatActivity {
    ImageView logo;
    TextView name, own1, own2;
    Animation topAnim, bottomAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        getSupportActionBar().hide();
        logo = findViewById(R.id.logoimg);
        name = findViewById(R.id.logonameimg);
        own1 = findViewById(R.id.ownone);
        own2 = findViewById(R.id.owntwo);
        FirebaseApp.initializeApp(this);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo.setAnimation(topAnim);
        name.setAnimation(bottomAnim);
        own1.setAnimation(bottomAnim);
        own2.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirebaseUtils.isLoggedIn()){
                    startActivity(new Intent(Splash.this, MainActivity2.class));
                }else {
                    Intent intent = new Intent(Splash.this, Login.class);
                    startActivity(intent);
                }
                finish();
            }
        },4000);
    }
}
