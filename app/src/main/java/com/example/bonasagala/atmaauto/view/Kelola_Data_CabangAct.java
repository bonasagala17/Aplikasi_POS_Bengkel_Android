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
import com.example.bonasagala.atmaauto.controller.Api_Interface_Cabang;
import com.example.bonasagala.atmaauto.model.Cabang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelola_Data_CabangAct extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CabangAdapter cabangadapter;
    private List<Cabang> cabangsList;
    Api_Interface_Cabang apiInterface;
    CabangAdapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__data__cabang);

        apiInterface = ApiClient.getApiClient().create(Api_Interface_Cabang.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new CabangAdapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Kelola_Data_CabangAct.this, CabangEditorAct.class);
                intent.putExtra("id", cabangsList.get(position).getId());
                intent.putExtra("nama_cabang", cabangsList.get(position).getNama_cabang());
                intent.putExtra("alamat_cabang", cabangsList.get(position).getAlamat_cabang());
                startActivity(intent);
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Kelola_Data_CabangAct.this, CabangEditorAct.class));
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_cabang, menu);

        getSupportActionBar().setTitle("Data Cabang");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Cabang...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                cabangadapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cabangadapter.getFilter().filter(newText);
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

    public void getCabang(){
        Call<List<Cabang>> call = apiInterface.getCabang();
        call.enqueue(new Callback<List<Cabang>>() {
            @Override
            public void onResponse(Call<List<Cabang>> call, Response<List<Cabang>> response) {
                progressBar.setVisibility(View.GONE);
                cabangsList = response.body();
                Log.i(Kelola_Data_CabangAct.class.getSimpleName(), response.body().toString());
                cabangadapter = new CabangAdapter(cabangsList,  listener);
                recyclerView.setAdapter(cabangadapter);
                cabangadapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Cabang>> call, Throwable t) {
                Toast.makeText(Kelola_Data_CabangAct.this, "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getCabang();
    }

}
