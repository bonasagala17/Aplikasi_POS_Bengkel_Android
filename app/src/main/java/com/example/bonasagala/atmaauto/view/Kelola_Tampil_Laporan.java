package com.example.bonasagala.atmaauto.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bonasagala.atmaauto.R;

public class Kelola_Tampil_Laporan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__tampil__laporan);

        Button btn_pendapatanBulanan = (Button)findViewById(R.id.btn_pendapatanBulanan);
        btn_pendapatanBulanan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://192.168.19.140/atmaautok1/mobile_laporan_pendapatan_tahunan.php"));
                startActivity(browserIntent);
            }
        });

        Button btn_pendapatanTahunan = (Button)findViewById(R.id.btn_pendapatanTahunan);
        btn_pendapatanTahunan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://192.168.19.140/atmaautok1/mobile_laporan_pendapatan_bulanan.php"));
                startActivity(browserIntent);
            }
        });

        Button btn_pengeluaran = (Button)findViewById(R.id.btn_pengeluaran);
        btn_pengeluaran.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://192.168.19.140/atmaautok1/mobile_laporan_pengeluaran.php"));
                startActivity(browserIntent);
            }
        });

        Button btn_penjualanJasa = (Button)findViewById(R.id.btn_penjualanJasa);
        btn_penjualanJasa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://192.168.19.140/atmaautok1/mobile_laporan_penjualan_jasa.php"));
                startActivity(browserIntent);
            }
        });

        Button btn_sisaStok = (Button)findViewById(R.id.btn_sisaStok);
        btn_sisaStok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://192.168.19.140/atmaautok1/mobile_laporan_sisa_stok.php"));
                startActivity(browserIntent);
            }
        });

        Button btn_sparepartTerlaris = (Button)findViewById(R.id.btn_sparepartTerlaris);
        btn_sparepartTerlaris.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://192.168.19.140/atmaautok1/mobile_laporan_sparepart_terlaris.php"));
                startActivity(browserIntent);
            }
        });

    }
}
