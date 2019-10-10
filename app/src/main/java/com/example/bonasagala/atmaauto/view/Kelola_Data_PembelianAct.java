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
import com.example.bonasagala.atmaauto.controller.Api_Interface_Pembelian;
import com.example.bonasagala.atmaauto.model.Pembelian_Sparepart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelola_Data_PembelianAct extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PembelianAdapter pemmbelianadapter;
    private List<Pembelian_Sparepart> pembeliansList;
    Api_Interface_Pembelian apiInterfacePembelian;
    PembelianAdapter.RecyclerViewPembelianClickListener listenerPembelian;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__data__pembelian);

        apiInterfacePembelian = ApiClient.getApiClient().create(Api_Interface_Pembelian.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listenerPembelian = new PembelianAdapter.RecyclerViewPembelianClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Kelola_Data_PembelianAct.this, PembelianEditorAct.class);
                intent.putExtra("id_pembelian", pembeliansList.get(position).getId_pembelian());
                intent.putExtra("id_supplier", pembeliansList.get(position).getId_supplier());
                intent.putExtra("tanggal_pembelian",pembeliansList.get(position).getTanggal_pembelian());
                intent.putExtra("total_pembayaran",pembeliansList.get(position).getTotal_pembayaran());
                intent.putExtra("status",pembeliansList.get(position).getStatus());
                startActivity(intent);
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Kelola_Data_PembelianAct.this, PembelianEditorAct.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_pembelian, menu);

        getSupportActionBar().setTitle("Data Pembelian");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Data Transaksi...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pemmbelianadapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pemmbelianadapter.getFilter().filter(newText);
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

    public void getPembelian(){
        Call<List<Pembelian_Sparepart>> call = apiInterfacePembelian.getPembelian();
        call.enqueue(new Callback<List<Pembelian_Sparepart>>() {
            @Override
            public void onResponse(Call<List<Pembelian_Sparepart>> call, Response<List<Pembelian_Sparepart>> response) {
                progressBar.setVisibility(View.GONE);
                pembeliansList = response.body();
                Log.i(Kelola_Data_PembelianAct.class.getSimpleName(), response.body().toString());
                pemmbelianadapter = new PembelianAdapter(pembeliansList,  listenerPembelian);
                recyclerView.setAdapter(pemmbelianadapter);
                pemmbelianadapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Pembelian_Sparepart>> call, Throwable t) {
                Toast.makeText(Kelola_Data_PembelianAct.this, "rp : "+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getPembelian();
    }
}
