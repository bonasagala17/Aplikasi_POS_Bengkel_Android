package com.example.bonasagala.atmaauto.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.bonasagala.atmaauto.R;

public class GetStartedAct extends AppCompatActivity {

    Button btn_masuk;
    Button btn_katalogBengkel;
    Button btn_infobengkel;
    Animation toptobottom, bottomtotop;
    TextView intro_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        toptobottom = AnimationUtils.loadAnimation(this, R.anim.toptobottom);
        bottomtotop = AnimationUtils.loadAnimation(this, R.anim.bottomtotop);

        btn_masuk = findViewById(R.id.btn_masuk);
        btn_katalogBengkel = findViewById(R.id.btn_katalogBengkel2);
        btn_infobengkel = findViewById(R.id.btn_infobengkel);
        intro_app = findViewById(R.id.intro_app);



        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomasuk = new Intent(GetStartedAct.this, MasukAct.class);
                startActivity(gotomasuk);
            }
        });

        btn_katalogBengkel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotokatalog = new Intent(GetStartedAct.this, Kelola_Data_Katalog_Act.class);
                startActivity(gotokatalog);
            }
        });

        btn_infobengkel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoinfobengkel = new Intent(GetStartedAct.this, TampilInfoBengkel.class);
                startActivity(gotoinfobengkel);
            }
        });
    }
}
