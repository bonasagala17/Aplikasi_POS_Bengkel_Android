package com.example.bonasagala.atmaauto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bonasagala.atmaauto.view.Kelola_Data_History_Keluar_Act;
import com.example.bonasagala.atmaauto.view.Kelola_Data_History_Masuk_Act;

public class Menu_History_Act extends AppCompatActivity {

    Button btn_historyMasuk, btn_historyKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__history_);

        btn_historyMasuk = findViewById(R.id.btn_historyMasuk);
        btn_historyKeluar = findViewById(R.id.btn_historyKeluar);

        btn_historyMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohistorymasuk = new Intent(Menu_History_Act.this, Kelola_Data_History_Masuk_Act.class);
                startActivity(gotohistorymasuk);
            }
        });

        btn_historyKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohistorykeluar = new Intent(Menu_History_Act.this, Kelola_Data_History_Keluar_Act.class);
                startActivity(gotohistorykeluar);
            }
        });
    }
}
