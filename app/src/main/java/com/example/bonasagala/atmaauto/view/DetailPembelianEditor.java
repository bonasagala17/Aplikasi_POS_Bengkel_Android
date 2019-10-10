package com.example.bonasagala.atmaauto.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.controller.ApiClient;
import com.example.bonasagala.atmaauto.controller.Api_Interface_DetailPembelian;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Pembelian;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Sparepart;
import com.example.bonasagala.atmaauto.model.Detail_PembelianSparepart;
import com.example.bonasagala.atmaauto.model.Pembelian_Sparepart;
import com.example.bonasagala.atmaauto.model.Sparepart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPembelianEditor extends AppCompatActivity {

    private EditText mHargaBeli, mJumlah;
    private Spinner spinnerPembelian, spinnerSparepart;
    private List<String> listSpinner, listSpinnerSparepart;

    private String hargabeli, jumlah, id_pembelian, id_sparepart;
    private int id_detail_pembelian;

    private Api_Interface_DetailPembelian apiInterfaceDetailPembelian;

    private Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pembelian_editor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        listSpinner = new ArrayList<>();
        listSpinnerSparepart = new ArrayList<>();
        spinnerPembelian = findViewById(R.id.spinnerPembelian);
        spinnerSparepart = findViewById(R.id.spinnerSparepart);
        mHargaBeli = findViewById(R.id.hargabeli);
        mJumlah = findViewById(R.id.jumlah);

        Intent intent = getIntent();
        id_detail_pembelian = intent.getIntExtra("id_detail_pembelian", 0);
        id_pembelian = intent.getStringExtra("id_pembelian");
        id_sparepart = intent.getStringExtra("id_sparepart");
        hargabeli = intent.getStringExtra("hargabeli");
        jumlah = intent.getStringExtra("jumlah");

        setDataFromIntentExtra();
        loadSpinnerPembelian();
        loadSpinnerSparepart();
    }

    private void setDataFromIntentExtra() {
        if (id_detail_pembelian != 0) {
            readMode();
            //getSupportActionBar().setTitle("Edit " + id_pembelian.toString());

            mHargaBeli.setText(hargabeli);
            mJumlah.setText(jumlah);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_create);
            requestOptions.error(R.drawable.ic_create);
        }
        else {
            getSupportActionBar().setTitle("Tambah Data Detail Pembelian");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor_detailpembelian, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id_detail_pembelian == 0){
            action.findItem(R.id.menu_edit).setVisible(false);
            action.findItem(R.id.menu_delete).setVisible(false);
            action.findItem(R.id.menu_save).setVisible(true);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();

                return true;

            case R.id.menu_edit:
                //Edit

                editMode();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mHargaBeli, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                if (id_detail_pembelian == 0) {
                    if (spinnerSparepart.getSelectedItemPosition()==0 ||
                            /*spinnerPembelian.getSelectedItemPosition()==0 ||*/
                            TextUtils.isEmpty(mHargaBeli.getText().toString()) ||
                            TextUtils.isEmpty(mJumlah.getText().toString())){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage("Silahkan isi data dengan lengkap!");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }

                    else {
                        postData("insert");
                        action.findItem(R.id.menu_edit).setVisible(true);
                        action.findItem(R.id.menu_save).setVisible(false);
                        action.findItem(R.id.menu_delete).setVisible(true);

                        readMode();
                    }
                }

                else {

                    updateData("update", id_detail_pembelian);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }

                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailPembelianEditor.this);
                dialog.setMessage("Menghapus Detail Pembelian?");

                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData("delete", id_detail_pembelian);
                    }
                });
                dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadSpinnerPembelian() {
        Api_Interface_Pembelian apiInterfacePembelian = ApiClient.getApiClient().create(Api_Interface_Pembelian.class);
        Call<List<Pembelian_Sparepart>> listCall = apiInterfacePembelian.getPembelian();

        listCall.enqueue(new Callback<List<Pembelian_Sparepart>>() {
            @Override
            public void onResponse(Call<List<Pembelian_Sparepart>> call, Response<List<Pembelian_Sparepart>> response) {
                List<Pembelian_Sparepart> Pembelian_SparepartModels = response.body();
                for(int i=0; i < Pembelian_SparepartModels.size(); i++ ){
                    int id_pembelian = Pembelian_SparepartModels.get(i).getId_pembelian();
                    listSpinner.add(String.valueOf(id_pembelian));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailPembelianEditor.this,
                        android.R.layout.simple_spinner_item,listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPembelian.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Pembelian_Sparepart>> call, Throwable t) {

            }
        });
    }

    private void loadSpinnerSparepart() {
        Api_Interface_Sparepart apiInterfaceSparepart = ApiClient.getApiClient().create(Api_Interface_Sparepart.class);
        Call<List<Sparepart>> listCall = apiInterfaceSparepart.getSparepart();

        listCall.enqueue(new Callback<List<Sparepart>>() {
            @Override
            public void onResponse(Call<List<Sparepart>> call, Response<List<Sparepart>> response) {
                List<Sparepart> sparepartModels = response.body();
                for(int i=0; i < sparepartModels.size(); i++ ){
                    int id_sparepart = sparepartModels.get(i).getId_sparepart();
                    String nama_sparepart =sparepartModels.get(i).getNama_sparepart();
                    String inputSparepart = id_sparepart+ " - "+nama_sparepart;
                    listSpinnerSparepart.add(String.valueOf(inputSparepart));
                }
                listSpinnerSparepart.add(0,"-PILIH SPAREPART-");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailPembelianEditor.this,
                        android.R.layout.simple_spinner_item,listSpinnerSparepart);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSparepart.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Sparepart>> call, Throwable t) {

            }
        });
    }

    private void postData(final String key) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        readMode();

        String id_pembelian = spinnerPembelian.getSelectedItem().toString();
        String id_sparepart = spinnerSparepart.getSelectedItem().toString();
        String hargabeli = mHargaBeli.getText().toString().trim();
        String jumlah = mJumlah.getText().toString().trim();

        apiInterfaceDetailPembelian = ApiClient.getApiClient().create(Api_Interface_DetailPembelian.class);

        Call<Detail_PembelianSparepart> call = apiInterfaceDetailPembelian.createDetailPembelian(key, id_pembelian, id_sparepart, hargabeli, jumlah);
        call.enqueue(new Callback<Detail_PembelianSparepart>() {
            @Override
            public void onResponse(Call<Detail_PembelianSparepart> call, Response<Detail_PembelianSparepart> response) {
                progressDialog.dismiss();
                Log.i(KonsumenEditorAct.class.getSimpleName(), response.toString());
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    finish();
                }else {
                    Toast.makeText(DetailPembelianEditor.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Detail_PembelianSparepart> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailPembelianEditor.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData(final String key, final int id_detail_pembelian) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mengupdate...");
        progressDialog.show();

        readMode();

        String id_pembelian = spinnerPembelian.getSelectedItem().toString();
        String id_sparepart = spinnerSparepart.getSelectedItem().toString();
        String hargabeli = mHargaBeli.getText().toString().trim();
        String jumlah = mJumlah.getText().toString().trim();

        apiInterfaceDetailPembelian = ApiClient.getApiClient().create(Api_Interface_DetailPembelian.class);
        Call<Detail_PembelianSparepart> call = apiInterfaceDetailPembelian.updateDetailPembelian(key, id_detail_pembelian, id_pembelian, id_sparepart, hargabeli, jumlah);

        call.enqueue(new Callback<Detail_PembelianSparepart>() {
            @Override
            public void onResponse(Call<Detail_PembelianSparepart> call, Response<Detail_PembelianSparepart> response) {
                progressDialog.dismiss();
                Log.i(KonsumenEditorAct.class.getSimpleName(), response.toString());
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    finish();
                }else {
                    Toast.makeText(DetailPembelianEditor.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Detail_PembelianSparepart> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailPembelianEditor.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteData(final String key, final int id_detail_pembelian) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        readMode();

        apiInterfaceDetailPembelian = ApiClient.getApiClient().create(Api_Interface_DetailPembelian.class);

        Call<Detail_PembelianSparepart> call = apiInterfaceDetailPembelian.hapusDetailPembelian(key, id_detail_pembelian);

        call.enqueue(new Callback<Detail_PembelianSparepart>() {
            @Override
            public void onResponse(Call<Detail_PembelianSparepart> call, Response<Detail_PembelianSparepart> response) {
                progressDialog.dismiss();

                Log.i(SupplierEditorAct.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(DetailPembelianEditor.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DetailPembelianEditor.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Detail_PembelianSparepart> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailPembelianEditor.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void readMode() {
        spinnerPembelian.setEnabled(false);
        spinnerPembelian.setClickable(false);
        spinnerSparepart.setEnabled(false);
        spinnerSparepart.setClickable(false);
        mJumlah.setFocusableInTouchMode(false);
        mHargaBeli.setFocusableInTouchMode(false);
    }

    private void editMode() {
        spinnerPembelian.setEnabled(true);
        spinnerPembelian.setClickable(true);
        spinnerSparepart.setEnabled(true);
        spinnerSparepart.setClickable(true);
        mJumlah.setFocusableInTouchMode(true);
        mHargaBeli.setFocusableInTouchMode(true);
    }
}
