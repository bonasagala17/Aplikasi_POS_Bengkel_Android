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
import com.example.bonasagala.atmaauto.controller.Api_Interface_JasaService;
import com.example.bonasagala.atmaauto.controller.Api_Interface_PenjualanJasaService;
import com.example.bonasagala.atmaauto.model.Penjualan_JasaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelola_Data_Penjualan_JasaService extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PenjualanJasaService_Adapter detailPenjualanJasaService_adapter;
    private List<Penjualan_JasaService> detail_penjualanJasaServiceList;
    Api_Interface_PenjualanJasaService apiInterfaceDetailPenjualanJasaService;
    PenjualanJasaService_Adapter.RecyclerViewPenjualanJasaServiceClickListener listenerDetailPenjualanJasaService;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__data__penjualan__jasa_service);

        apiInterfaceDetailPenjualanJasaService = ApiClient.getApiClient().create(Api_Interface_PenjualanJasaService.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listenerDetailPenjualanJasaService = new PenjualanJasaService_Adapter.RecyclerViewPenjualanJasaServiceClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Kelola_Data_Penjualan_JasaService.this, PenjualanJasaService_Editor.class);
                intent.putExtra("id_detail_transaksi_jasaservice", detail_penjualanJasaServiceList.get(position).getId_detail_transaksi_jasaservice());
                intent.putExtra("id_jasaservice", detail_penjualanJasaServiceList.get(position).getId_jasaservice());
                intent.putExtra("id_transaksi_pembayaran",detail_penjualanJasaServiceList.get(position).getId_transaksi_pembayaran());
                intent.putExtra("id_montir",detail_penjualanJasaServiceList.get(position).getId_montir());
                intent.putExtra("jumlah",detail_penjualanJasaServiceList.get(position).getJumlah());
                intent.putExtra("subtotal",detail_penjualanJasaServiceList.get(position).getSubtotal());
                startActivity(intent);
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Kelola_Data_Penjualan_JasaService.this, PenjualanJasaService_Editor.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_penjualan_jasaservice, menu);

        getSupportActionBar().setTitle("Data Penjualan Jasa Service");

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
                detailPenjualanJasaService_adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                detailPenjualanJasaService_adapter.getFilter().filter(newText);
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

    public void get_penjualanJasaService(){
        Call<List<Penjualan_JasaService>> call = apiInterfaceDetailPenjualanJasaService.get_penjualanJasaService();
        call.enqueue(new Callback<List<Penjualan_JasaService>>() {
            @Override
            public void onResponse(Call<List<Penjualan_JasaService>> call, Response<List<Penjualan_JasaService>> response) {
                progressBar.setVisibility(View.GONE);
                detail_penjualanJasaServiceList = response.body();
                Log.i(Kelola_Data_Penjualan_JasaService.class.getSimpleName(), response.body().toString());
                detailPenjualanJasaService_adapter = new PenjualanJasaService_Adapter(detail_penjualanJasaServiceList,  listenerDetailPenjualanJasaService);
                recyclerView.setAdapter(detailPenjualanJasaService_adapter);
                detailPenjualanJasaService_adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Penjualan_JasaService>> call, Throwable t) {
                Toast.makeText(Kelola_Data_Penjualan_JasaService.this, "rp : "+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        get_penjualanJasaService();
    }
}
