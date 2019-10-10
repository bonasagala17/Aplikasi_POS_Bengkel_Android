package com.example.bonasagala.atmaauto.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bonasagala.atmaauto.R;

public class SuksesMasukAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses_masuk);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //merubah activity ke activity lain
                Intent goToHome = new Intent(SuksesMasukAct.this, HomeAct.class);
                startActivity(goToHome);
                finish();
            }
        }, 2000);
    }
}
