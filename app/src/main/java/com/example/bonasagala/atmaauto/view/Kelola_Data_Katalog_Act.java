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
import com.example.bonasagala.atmaauto.controller.Api_Interface_Sparepart;
import com.example.bonasagala.atmaauto.model.Sparepart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelola_Data_Katalog_Act extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SparepartAdapter sparepartAdapter;
    private List<Sparepart> sparepartList;
    Api_Interface_Sparepart apiInterfaceSparepart;
    SparepartAdapter.RecyclerViewSparepartClickListener listenerSparepart;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__data__katalog);

        apiInterfaceSparepart = ApiClient.getApiClient().create(Api_Interface_Sparepart.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listenerSparepart = new SparepartAdapter.RecyclerViewSparepartClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Kelola_Data_Katalog_Act.this, KatalogEditorAct.class);
                intent.putExtra("id_sparepart", sparepartList.get(position).getId_sparepart());
                intent.putExtra("nama_sparepart", sparepartList.get(position).getNama_sparepart());
                intent.putExtra("merk_sparepart", sparepartList.get(position).getMerk_sparepart());
                intent.putExtra("tipe_sparepart", sparepartList.get(position).getTipe_sparepart());
                intent.putExtra("hargabeli_sparepart", sparepartList.get(position).getHargabeli_sparepart());
                intent.putExtra("hargajual_sparepart", sparepartList.get(position).getHargajual_sparepart());
                intent.putExtra("stok_sparepart", sparepartList.get(position).getStok_sparepart());
                intent.putExtra("stokoptimal_sparepart", sparepartList.get(position).getStokoptimal_sparepart());
                intent.putExtra("gambar_sparepart", sparepartList.get(position).getGambar_sparepart());
                intent.putExtra("code_sparepart", sparepartList.get(position).getLetak_sparepart());
                startActivity(intent);
            }
        };

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_sparepart, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Sparepart...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                sparepartAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sparepartAdapter.getFilter().filter(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false,false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getSparepart(){
        Call<List<Sparepart>> call = apiInterfaceSparepart.getSparepart();
        call.enqueue(new Callback<List<Sparepart>>() {
            @Override
            public void onResponse(Call<List<Sparepart>> call, Response<List<Sparepart>> response) {
                progressBar.setVisibility(View.GONE);
                sparepartList = response.body();
                Log.i(Kelola_Data_SparepartAct.class.getSimpleName(), response.body().toString());
                sparepartAdapter = new SparepartAdapter(sparepartList, Kelola_Data_Katalog_Act.this, listenerSparepart);
                recyclerView.setAdapter(sparepartAdapter);
                sparepartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Sparepart>> call, Throwable t) {
                Toast.makeText(Kelola_Data_Katalog_Act.this, "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume(){
        super.onResume();
        getSparepart();
    }
}
