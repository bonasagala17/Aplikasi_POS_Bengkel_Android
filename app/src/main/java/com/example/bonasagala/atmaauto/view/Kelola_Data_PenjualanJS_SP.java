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
import com.example.bonasagala.atmaauto.controller.Api_Interface_JS_SP;
import com.example.bonasagala.atmaauto.model.JS_SP;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelola_Data_PenjualanJS_SP extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Penjualan_JS_SP_Adapter adapterPenjualanJS_SP;
    private List<JS_SP> detail_penjualanJS_SP;
    Api_Interface_JS_SP apiInterfacePenjualanJS_SP;
    Penjualan_JS_SP_Adapter.RecyclerViewPenjualanJS_SPClickListener listenerPenjualanJS_SP;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__data__penjualan_js__sp);

        apiInterfacePenjualanJS_SP = ApiClient.getApiClient().create(Api_Interface_JS_SP.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listenerPenjualanJS_SP = new Penjualan_JS_SP_Adapter.RecyclerViewPenjualanJS_SPClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Kelola_Data_PenjualanJS_SP.this, PenjualanJS_SPeditor.class);
                intent.putExtra("id_detail_transaksi_jasaservice", detail_penjualanJS_SP.get(position).getId_detail_transaksi_jasaservice());
                intent.putExtra("id_jasaservice", detail_penjualanJS_SP.get(position).getId_jasaservice());
                intent.putExtra("id_sparepart", detail_penjualanJS_SP.get(position).getId_sparepart());
                intent.putExtra("id_transaksi_pembayaran",detail_penjualanJS_SP.get(position).getId_transaksi_pembayaran());
                intent.putExtra("id_montir",detail_penjualanJS_SP.get(position).getId_montir());
                intent.putExtra("jumlah",detail_penjualanJS_SP.get(position).getJumlah());
                intent.putExtra("subtotal",detail_penjualanJS_SP.get(position).getSubtotal());
                startActivity(intent);
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Kelola_Data_PenjualanJS_SP.this, PenjualanJS_SPeditor.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_penjualan_js_sp, menu);

        getSupportActionBar().setTitle("Data Penjualan Jasa");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Detail Detail Penjualan...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterPenjualanJS_SP.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterPenjualanJS_SP.getFilter().filter(newText);
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

    public void getPenjualanJS_SP(){
        Call<List<JS_SP>> call = apiInterfacePenjualanJS_SP.getPenjualanJS_SP();

        call.enqueue(new Callback<List<JS_SP>>() {
            @Override
            public void onResponse(Call<List<JS_SP>> call, Response<List<JS_SP>> response) {
                progressBar.setVisibility(View.GONE);
                detail_penjualanJS_SP = response.body();
                Log.i(Kelola_Data_Penjualan_JasaService.class.getSimpleName(), response.body().toString());
                adapterPenjualanJS_SP = new Penjualan_JS_SP_Adapter(detail_penjualanJS_SP,  listenerPenjualanJS_SP);
                recyclerView.setAdapter(adapterPenjualanJS_SP);
                adapterPenjualanJS_SP.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<JS_SP>> call, Throwable t) {
                Toast.makeText(Kelola_Data_PenjualanJS_SP.this, "rp : "+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getPenjualanJS_SP();
    }
}
