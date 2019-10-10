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
import com.example.bonasagala.atmaauto.controller.Api_Interface_Konsumen;
import com.example.bonasagala.atmaauto.model.Konsumen;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelola_Data_KonsumenAct extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private KonsumenAdapter konsumenadapter;
    private List<Konsumen> konsumensList;
    Api_Interface_Konsumen apiInterfaceKonsumen;
    KonsumenAdapter.RecyclerViewKonsumenClickListener listenerKonsumen;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__data__konsumen);

        apiInterfaceKonsumen = ApiClient.getApiClient().create(Api_Interface_Konsumen.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listenerKonsumen = new KonsumenAdapter.RecyclerViewKonsumenClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Kelola_Data_KonsumenAct.this, KonsumenEditorAct.class);
                intent.putExtra("id_konsumen", konsumensList.get(position).getId_konsumen());
                intent.putExtra("nama_konsumen", konsumensList.get(position).getNama_konsumen());
                intent.putExtra("telp_konsumen",konsumensList.get(position).getTelp_konsumen());
                intent.putExtra("alamat_konsumen",konsumensList.get(position).getAlamat_konsumen());
                startActivity(intent);
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Kelola_Data_KonsumenAct.this, KonsumenEditorAct.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_konsumen, menu);

        getSupportActionBar().setTitle("Data Konsumen");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Konsumen...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                konsumenadapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                konsumenadapter.getFilter().filter(newText);
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

    public void getKonsumen(){
        Call<List<Konsumen>> call = apiInterfaceKonsumen.getKonsumen();
        call.enqueue(new Callback<List<Konsumen>>() {
            @Override
            public void onResponse(Call<List<Konsumen>> call, Response<List<Konsumen>> response) {
                progressBar.setVisibility(View.GONE);
                konsumensList = response.body();
                Log.i(Kelola_Data_KonsumenAct.class.getSimpleName(), response.body().toString());
                konsumenadapter = new KonsumenAdapter(konsumensList,  listenerKonsumen);
                recyclerView.setAdapter(konsumenadapter);
                konsumenadapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Konsumen>> call, Throwable t) {
                Toast.makeText(Kelola_Data_KonsumenAct.this, "rp : "+
                            t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getKonsumen();
    }
}
