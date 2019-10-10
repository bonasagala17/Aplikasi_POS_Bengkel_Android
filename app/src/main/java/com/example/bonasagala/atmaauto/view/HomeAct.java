package com.example.bonasagala.atmaauto.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bonasagala.atmaauto.Menu_History_Act;
import com.example.bonasagala.atmaauto.R;

public class HomeAct extends AppCompatActivity {

    ImageView img_cabang, img_spareparts, img_supplier, img_konsumen, img_pembelian, img_penjualan_sparepart, img_history, img_laporan;

    private TextView nama_pegawai, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        img_cabang = findViewById(R.id.img_cabang);
        img_supplier = findViewById(R.id.img_supplier);
        img_spareparts = findViewById(R.id.img_spareparts);
        img_konsumen = findViewById(R.id.img_konsumen);
        img_pembelian = findViewById(R.id.img_pembelian);
        img_penjualan_sparepart = findViewById(R.id.img_penjualan_sparepart);
        img_history = findViewById(R.id.img_history);
        img_laporan = findViewById(R.id.img_laporan);

        nama_pegawai = findViewById(R.id.nama_pegawai);
        role = findViewById(R.id.role);

        Intent i = getIntent();
        String extraNama_pegawai = i.getStringExtra("NAMA_PEGAWAI");
        String extraRole = i.getStringExtra("ROLE");

        nama_pegawai.setText(extraNama_pegawai);
        role.setText(extraRole);


        img_cabang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoKelolaCabang = new Intent(HomeAct.this, Kelola_Data_CabangAct.class);
                startActivity(gotoKelolaCabang);
            }
        });

        img_supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoKelolaSupplier = new Intent(HomeAct.this, Kelola_Data_SupplierAct.class);
                startActivity(gotoKelolaSupplier);
            }
        });

        img_spareparts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoKelolaSparepart = new Intent(HomeAct.this, Kelola_Data_SparepartAct.class);
                startActivity(gotoKelolaSparepart);
            }
        });

        img_konsumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoKelolaKonsumen = new Intent(HomeAct.this, Kelola_Data_KonsumenAct.class);
                startActivity(gotoKelolaKonsumen);
            }
        });

        img_pembelian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoKelolaPembelian = new Intent(HomeAct.this, Kelola_Data_PembelianAct.class);
                startActivity(gotoKelolaPembelian);
            }
        });

        img_penjualan_sparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoKelolaPenjualanSparepart = new Intent(HomeAct.this, Kelola_Data_Penjualan.class);
                startActivity(gotoKelolaPenjualanSparepart);
            }
        });

        img_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohistory = new Intent(HomeAct.this, Menu_History_Act.class);
                startActivity(gotohistory);
            }
        });

        img_laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotolaporan = new Intent(HomeAct.this, Kelola_Tampil_Laporan.class);
                startActivity(gotolaporan);
            }
        });


    }
}
