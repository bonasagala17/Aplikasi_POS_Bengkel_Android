package com.example.bonasagala.atmaauto.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bonasagala.atmaauto.R;

public class HomeCSact extends AppCompatActivity {

    ImageView img_pembelian, img_penjualan_sparepart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_csact);

        img_pembelian = findViewById(R.id.img_pembelian);
        img_penjualan_sparepart = findViewById(R.id.img_penjualan_sparepart);

        img_pembelian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoKelolaPembelian = new Intent(HomeCSact.this, Kelola_Data_PembelianAct.class);
                startActivity(gotoKelolaPembelian);
            }
        });

        img_penjualan_sparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoKelolaPenjualanSparepart = new Intent(HomeCSact.this, Kelola_Data_Penjualan.class);
                startActivity(gotoKelolaPenjualanSparepart);
            }
        });
    }
}
