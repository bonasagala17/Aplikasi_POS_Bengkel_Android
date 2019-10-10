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
import com.example.bonasagala.atmaauto.controller.Api_Interface_DetailPenjualanSparepart;
import com.example.bonasagala.atmaauto.model.Detail_PenjualanSparepart;
import com.example.bonasagala.atmaauto.model.Sparepart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelola_Data_Detail_Penjualan_Sparepart extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DetailPenjualanSparepart_Adapter detailPenjualanSparepart_adapter;
    private List<Detail_PenjualanSparepart> detail_penjualanSparepartList;

    Api_Interface_DetailPenjualanSparepart apiInterfaceDetailPenjualanSparepart;
    DetailPenjualanSparepart_Adapter.RecyclerViewDetailPenjualanSparepartClickListener listenerDetailPenjualanSparepart;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__data__detail__penjualan__sparepart);

        apiInterfaceDetailPenjualanSparepart = ApiClient.getApiClient().create(Api_Interface_DetailPenjualanSparepart.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listenerDetailPenjualanSparepart = new DetailPenjualanSparepart_Adapter.RecyclerViewDetailPenjualanSparepartClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Kelola_Data_Detail_Penjualan_Sparepart.this, DetailPenjualanSparepart_Editor.class);
                intent.putExtra("id_detail_transaksi_sparepart", detail_penjualanSparepartList.get(position).getId_detail_transaksi_sparepart());
                intent.putExtra("id_sparepart", detail_penjualanSparepartList.get(position).getId_sparepart());
                intent.putExtra("id_transaksi_pembayaran",detail_penjualanSparepartList.get(position).getId_transaksi_pembayaran());
                intent.putExtra("id_montir",detail_penjualanSparepartList.get(position).getId_montir());
                intent.putExtra("jumlah",detail_penjualanSparepartList.get(position).getJumlah());
                intent.putExtra("subtotal",detail_penjualanSparepartList.get(position).getSubtotal());
                startActivity(intent);
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Kelola_Data_Detail_Penjualan_Sparepart.this, DetailPenjualanSparepart_Editor.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_detail_penjualansparepart, menu);

        getSupportActionBar().setTitle("Data Detail Penjualan");

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
                detailPenjualanSparepart_adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                detailPenjualanSparepart_adapter.getFilter().filter(newText);
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

    public void getDetailPenjualanSparepart(){
        Call<List<Detail_PenjualanSparepart>> call = apiInterfaceDetailPenjualanSparepart.getDetailPenjualanSparepart();
        call.enqueue(new Callback<List<Detail_PenjualanSparepart>>() {
            @Override
            public void onResponse(Call<List<Detail_PenjualanSparepart>> call, Response<List<Detail_PenjualanSparepart>> response) {
                progressBar.setVisibility(View.GONE);
                detail_penjualanSparepartList = response.body();
                Log.i(Kelola_Data_DetailPembelianAct.class.getSimpleName(), response.body().toString());
                detailPenjualanSparepart_adapter = new DetailPenjualanSparepart_Adapter(detail_penjualanSparepartList,  listenerDetailPenjualanSparepart);
                recyclerView.setAdapter(detailPenjualanSparepart_adapter);
                detailPenjualanSparepart_adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Detail_PenjualanSparepart>> call, Throwable t) {
                Toast.makeText(Kelola_Data_Detail_Penjualan_Sparepart.this, "rp : "+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getDetailPenjualanSparepart();
    }
}
