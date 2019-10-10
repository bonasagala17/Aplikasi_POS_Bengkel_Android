package com.example.bonasagala.atmaauto.view;

import android.app.SearchManager;
import android.content.Context;
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
import com.example.bonasagala.atmaauto.controller.Api_Interface_HistoryMasuk;
import com.example.bonasagala.atmaauto.model.History_Masuk;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelola_Data_History_Masuk_Act extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HistoryMasukAdapter historyMasukAdapter;
    private List<History_Masuk> historyMasukList;

    Api_Interface_HistoryMasuk apiInterfaceHistoryMasuk;
    HistoryMasukAdapter.RecyclerViewHistoryMasukClickListener listenerHistoryMasuk;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__data__history__masuk);

        apiInterfaceHistoryMasuk = ApiClient.getApiClient().create(Api_Interface_HistoryMasuk.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_history_masuk, menu);

        getSupportActionBar().setTitle("Data History Masuk");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari History...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                historyMasukAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                historyMasukAdapter.getFilter().filter(newText);
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

    public void getHistoryMasuk(){
        Call<List<History_Masuk>> call = apiInterfaceHistoryMasuk.getHistoryMasuk();
        call.enqueue(new Callback<List<History_Masuk>>() {
            @Override
            public void onResponse(Call<List<History_Masuk>> call, Response<List<History_Masuk>> response) {
                progressBar.setVisibility(View.GONE);
                historyMasukList = response.body();
                Log.i(Kelola_Data_KonsumenAct.class.getSimpleName(), response.body().toString());
                historyMasukAdapter = new HistoryMasukAdapter(historyMasukList,  listenerHistoryMasuk);
                recyclerView.setAdapter(historyMasukAdapter);
                historyMasukAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<History_Masuk>> call, Throwable t) {
                Toast.makeText(Kelola_Data_History_Masuk_Act.this, "rp : "+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getHistoryMasuk();
    }
}
