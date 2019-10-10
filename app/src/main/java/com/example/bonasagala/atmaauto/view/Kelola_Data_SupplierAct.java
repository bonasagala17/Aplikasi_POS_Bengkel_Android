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
import com.example.bonasagala.atmaauto.controller.Api_Interface_Supplier;
import com.example.bonasagala.atmaauto.model.Supplier;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelola_Data_SupplierAct extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SupplierAdapter supplieradapter;
    private List<Supplier> suppliersList;
    Api_Interface_Supplier apiInterfaceSupplier;
    SupplierAdapter.RecyclerViewSupplierClickListener listenerSupplier;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola__data__supplier);

        apiInterfaceSupplier = ApiClient.getApiClient().create(Api_Interface_Supplier.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listenerSupplier = new SupplierAdapter.RecyclerViewSupplierClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Kelola_Data_SupplierAct.this, SupplierEditorAct.class);
                intent.putExtra("id_supplier", suppliersList.get(position).getId_supplier());
                intent.putExtra("nama_supplier", suppliersList.get(position).getNama_supplier());
                intent.putExtra("alamat_supplier",suppliersList.get(position).getAlamat_supplier());
                intent.putExtra("telp_supplier",suppliersList.get(position).getTelp_supplier());
                intent.putExtra("nama_sales",suppliersList.get(position).getNama_sales());
                intent.putExtra("telp_sales",suppliersList.get(position).getTelp_sales());
                startActivity(intent);
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Kelola_Data_SupplierAct.this, SupplierEditorAct.class));
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_supplier, menu);

        getSupportActionBar().setTitle("Data Supplier");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Mencari Supplier...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                supplieradapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                supplieradapter.getFilter().filter(newText);
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

    public void getSupplier(){
        Call<List<Supplier>> call = apiInterfaceSupplier.getSupplier();
        call.enqueue(new Callback<List<Supplier>>(){
            public void onResponse(Call<List<Supplier>> call, Response<List<Supplier>> response) {
                progressBar.setVisibility(View.GONE);
                suppliersList = response.body();
                Log.i(Kelola_Data_SupplierAct.class.getSimpleName(), response.body().toString());
                supplieradapter = new SupplierAdapter(suppliersList,  listenerSupplier);
                recyclerView.setAdapter(supplieradapter);
                supplieradapter.notifyDataSetChanged();
            }
            public void onFailure(Call<List<Supplier>> call, Throwable t) {
                Toast.makeText(Kelola_Data_SupplierAct.this, "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        getSupplier();
    }
}
