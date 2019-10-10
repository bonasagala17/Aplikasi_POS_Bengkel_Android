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
import com.example.bonasagala.atmaauto.controller.Api_Interface_DetailPembelian;
import com.example.bonasagala.atmaauto.model.Detail_PembelianSparepart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelola_Data_DetailPembelianAct extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DetailPembelianAdapter detailPembelianadapter;
    private List<Detail_PembelianSparepart> detailPembelianSparepartsList;
    Api_Interface_DetailPembelian apiInterfaceDetailPembelian;
    DetailPembelianAdapter.RecyclerViewDetailPembelianClickListener listenerDetailPembelian;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__data__detail_pembelian);

        apiInterfaceDetailPembelian = ApiClient.getApiClient().create(Api_Interface_DetailPembelian.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listenerDetailPembelian = new DetailPembelianAdapter.RecyclerViewDetailPembelianClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Kelola_Data_DetailPembelianAct.this, DetailPembelianEditor.class);
                intent.putExtra("id_detail_pembelian", detailPembelianSparepartsList.get(position).getId_detail_pembelian());
                intent.putExtra("id_pembelian", detailPembelianSparepartsList.get(position).getId_pembelian());
                intent.putExtra("id_sparepart",detailPembelianSparepartsList.get(position).getId_sparepart());
                intent.putExtra("hargabeli",detailPembelianSparepartsList.get(position).getHargabeli());
                intent.putExtra("jumlah",detailPembelianSparepartsList.get(position).getJumlah());
                startActivity(intent);
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Kelola_Data_DetailPembelianAct.this, DetailPembelianEditor.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_detailpembelian, menu);

        getSupportActionBar().setTitle("Data Pembelian");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Detail Pembelian...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                detailPembelianadapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                detailPembelianadapter.getFilter().filter(newText);
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

    public void getDetailPembelian(){
        Call<List<Detail_PembelianSparepart>> call = apiInterfaceDetailPembelian.getDetailPembelian();
        call.enqueue(new Callback<List<Detail_PembelianSparepart>>() {
            @Override
            public void onResponse(Call<List<Detail_PembelianSparepart>> call, Response<List<Detail_PembelianSparepart>> response) {
                progressBar.setVisibility(View.GONE);
                detailPembelianSparepartsList = response.body();
                Log.i(Kelola_Data_DetailPembelianAct.class.getSimpleName(), response.body().toString());
                detailPembelianadapter = new DetailPembelianAdapter(detailPembelianSparepartsList,  listenerDetailPembelian);
                recyclerView.setAdapter(detailPembelianadapter);
                detailPembelianadapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Detail_PembelianSparepart>> call, Throwable t) {
                Toast.makeText(Kelola_Data_DetailPembelianAct.this, "rp : "+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getDetailPembelian();
    }
}
