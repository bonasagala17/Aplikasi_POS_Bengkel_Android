package com.example.bonasagala.atmaauto.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.controller.ApiClient;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Penjualan;
import com.example.bonasagala.atmaauto.model.Penjualan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelola_Data_Penjualan extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PenjualanAdapter penjualanAdapter;
    private List<Penjualan> penjualanSparepartsList;
    Api_Interface_Penjualan apiInterfacePenjualanSparepart;
    PenjualanAdapter.RecyclerViewPenjualanSparepartClickListener listenerPenjualanSparepart;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__data__penjualant);

        apiInterfacePenjualanSparepart = ApiClient.getApiClient().create(Api_Interface_Penjualan.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listenerPenjualanSparepart = new PenjualanAdapter.RecyclerViewPenjualanSparepartClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Kelola_Data_Penjualan.this, Penjualan_EditorAct.class);
                intent.putExtra("id_transaksi_pembayaran", penjualanSparepartsList.get(position).getId_transaksi_pembayaran());
                intent.putExtra("id_konsumen", penjualanSparepartsList.get(position).getId_konsumen());
                intent.putExtra("id_cabang",penjualanSparepartsList.get(position).getId_cabang());
                intent.putExtra("no_transaksi",penjualanSparepartsList.get(position).getNo_transaksi());
                intent.putExtra("tanggal_transaksi",penjualanSparepartsList.get(position).getTanggal_transaksi());
                intent.putExtra("diskon",penjualanSparepartsList.get(position).getDiskon());
                intent.putExtra("total_transaksi",penjualanSparepartsList.get(position).getTotal_transaksi());
                intent.putExtra("status_pembayaran",penjualanSparepartsList.get(position).getStatus_pembayaran());
                startActivity(intent);
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Kelola_Data_Penjualan.this, Penjualan_EditorAct.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_penjualan_sparepart, menu);

        getSupportActionBar().setTitle("Data Penjualan");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Data Penjualan...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                penjualanAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                penjualanAdapter.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getPenjulanSparepart(){
        Call<List<Penjualan>> call = apiInterfacePenjualanSparepart.getPenjulanSparepart();
        call.enqueue(new Callback<List<Penjualan>>() {
            @Override
            public void onResponse(Call<List<Penjualan>> call, Response<List<Penjualan>> response) {
                progressBar.setVisibility(View.GONE);
                penjualanSparepartsList = response.body();
                Log.i(Kelola_Data_Penjualan.class.getSimpleName(), response.body().toString());
                penjualanAdapter = new PenjualanAdapter(penjualanSparepartsList,  listenerPenjualanSparepart);
                recyclerView.setAdapter(penjualanAdapter);
                penjualanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Penjualan>> call, Throwable t) {
                Toast.makeText(Kelola_Data_Penjualan.this, "rp : "+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getPenjulanSparepart();
    }
}
