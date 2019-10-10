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
import com.example.bonasagala.atmaauto.controller.Api_Interface_JS_SP;
import com.example.bonasagala.atmaauto.controller.Api_Interface_JasaService;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Pegawai;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Sparepart;
import com.example.bonasagala.atmaauto.model.JS_SP;
import com.example.bonasagala.atmaauto.model.JasaService;
import com.example.bonasagala.atmaauto.model.Pegawai;
import com.example.bonasagala.atmaauto.model.Sparepart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenjualanJS_SPeditor extends AppCompatActivity {

    private EditText mJumlah, mSubtotal;
    private Spinner spinnerTransaksiPembayaran, spinnerJasaService, spinnerSparepart, spinnerMontir;
    private List<String> listSpinnerTransaksiPembayaran, listSpinnerSparepart, listSpinnerMontir, listSpinnerJasaService;

    private String jumlah, subtotal, id_jasaservice, id_montir, id_sparepart, id_transaksi_pembayaran;
    private int id_detail_transaksi_sparepart, id_detail_transaksi_jasaservice;

    private Api_Interface_JS_SP apiInterfaceJsSp;

    private Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan_js__speditor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        listSpinnerTransaksiPembayaran = new ArrayList<>();
        listSpinnerSparepart = new ArrayList<>();
        listSpinnerMontir = new ArrayList<>();
        listSpinnerJasaService = new ArrayList<>();

        spinnerTransaksiPembayaran = findViewById(R.id.spinnerTransaksiPembayaran);
        spinnerSparepart = findViewById(R.id.spinnerSparepart);
        spinnerMontir =findViewById(R.id.spinnerMontir);
        spinnerJasaService = findViewById(R.id.spinnerJasaService);

        mJumlah = findViewById(R.id.jumlah);
        mSubtotal = findViewById(R.id.subtotal);

        Intent intent = getIntent();
        id_detail_transaksi_sparepart = intent.getIntExtra("id_detail_transaksi_sparepart", 0);
        id_detail_transaksi_jasaservice = intent.getIntExtra("id_detail_transaksi_jasaservice", 0);
        id_jasaservice = intent.getStringExtra("id_jasaservice");
        id_sparepart = intent.getStringExtra("id_sparepart");
        id_transaksi_pembayaran = intent.getStringExtra("id_transaksi_pembayaran");
        id_montir = intent.getStringExtra("id_montir");
        jumlah = intent.getStringExtra("jumlah");
        subtotal = intent.getStringExtra("subtotal");

        setDataFromIntentExtra();
        loadSpinnerTransaksiPembayaran();
        loadSpinnerSparepart();
        loadSpinnerJasaService();
        loadSpinnerMontir();
    }



    private void setDataFromIntentExtra() {
        if (id_detail_transaksi_sparepart != 0) {
            readMode();
            //getSupportActionBar().setTitle("Edit " + id_pembelian.toString());

            mSubtotal.setText(subtotal);
            mJumlah.setText(jumlah);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_create);
            requestOptions.error(R.drawable.ic_create);
        }
        else {
            getSupportActionBar().setTitle("Tambah Data Penjualan Jasa");

        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor_detail_penjualansparepart, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);
        if (id_detail_transaksi_sparepart == 0){

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
                imm.showSoftInput(mSubtotal, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                if (id_detail_transaksi_sparepart == 0) {
                    if (/*spinnerTransaksiPembayaran.getSelectedItemPosition()==0 ||*/
                            spinnerSparepart.getSelectedItemPosition()==0 ||
                                    spinnerJasaService.getSelectedItemPosition()==0 ||
                                    spinnerMontir.getSelectedItemPosition()==0 ||
                                    TextUtils.isEmpty(mJumlah.getText().toString()) ||
                                    TextUtils.isEmpty(mSubtotal.getText().toString())){
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

                    updateData("update", id_detail_transaksi_sparepart, id_detail_transaksi_jasaservice);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }

                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(PenjualanJS_SPeditor.this);
                dialog.setMessage("Menghapus Detail Penjualan Jasa?");

                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData("delete", id_detail_transaksi_sparepart, id_detail_transaksi_jasaservice);
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

    private void loadSpinnerTransaksiPembayaran() {
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PenjualanJS_SPeditor.this,
                        android.R.layout.simple_spinner_item,listSpinnerSparepart);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSparepart.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Sparepart>> call, Throwable t) {

            }
        });
    }

    private void loadSpinnerJasaService() {
        Api_Interface_JasaService apiInterfaceJasaService = ApiClient.getApiClient().create(Api_Interface_JasaService.class);
        Call<List<JasaService>> listCall = apiInterfaceJasaService.getJasaService();

        listCall.enqueue(new Callback<List<JasaService>>() {
            @Override
            public void onResponse(Call<List<JasaService>> call, Response<List<JasaService>> response) {
                List<JasaService> jasaserviceModels = response.body();
                for(int i=0; i < jasaserviceModels.size(); i++ ){
                    int id_jasaservice = jasaserviceModels.get(i).getId_jasaservice();
                    String nama_jasaservice = jasaserviceModels.get(i).getNama_jasaservcie();
                    String inputJasaSerice = id_jasaservice+ " - "+nama_jasaservice;
                    listSpinnerJasaService.add(String.valueOf(inputJasaSerice));
                }
                listSpinnerJasaService.add(0,"-PILIH JASA SERVICE-");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PenjualanJS_SPeditor.this,
                        android.R.layout.simple_spinner_item,listSpinnerJasaService);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerJasaService.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<JasaService>> call, Throwable t) {

            }
        });
    }

    private void loadSpinnerMontir() {
        Api_Interface_Pegawai apiInterfacePegawai = ApiClient.getApiClient().create(Api_Interface_Pegawai.class);
        //Api_interface_Montir apiInterfaceMontir = ApiClient.getApiClient().create(Api_interface_Montir.class);
        // Call<List<Montir>> listCall = apiInterfaceMontir.getMontir();
        Call<List<Pegawai>> listCallPegawai = apiInterfacePegawai.getPegawai();

        listCallPegawai.enqueue(new Callback<List<Pegawai>>() {
            @Override
            public void onResponse(Call<List<Pegawai>> call, Response<List<Pegawai>> response) {
                List<Pegawai> pegawaiModels = response.body();
                for(int i=0; i < pegawaiModels.size(); i++ ){
                    int id_pegawai = pegawaiModels.get(i).getId_pegawai();
                    String nama_pegawai =pegawaiModels.get(i).getNama_pegawai();
                    String inputPegawai = id_pegawai+ " - "+nama_pegawai;
                    listSpinnerMontir.add(String.valueOf(inputPegawai));
                }
                listSpinnerMontir.add(0,"-PILIH PEGAWAI-");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PenjualanJS_SPeditor.this,
                        android.R.layout.simple_spinner_item,listSpinnerMontir);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMontir.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Pegawai>> call, Throwable t) {

            }
        });
    }

    private void postData(String key) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        readMode();

        String id_sparepart = spinnerSparepart.getSelectedItem().toString();
        String id_jasaservice = spinnerJasaService.getSelectedItem().toString();
        String id_transaksi_pembayaran = spinnerTransaksiPembayaran.getSelectedItem().toString();
        String id_montir = spinnerMontir.getSelectedItem().toString();
        String jumlah = mJumlah.getText().toString().trim();
        String subtotal = mSubtotal.getText().toString().trim();

        apiInterfaceJsSp = ApiClient.getApiClient().create(Api_Interface_JS_SP.class);

        Call<JS_SP> call = apiInterfaceJsSp.createPenjualanJS_SP(key, id_jasaservice, id_sparepart, id_transaksi_pembayaran, id_montir, jumlah, subtotal);
        call.enqueue(new Callback<JS_SP>() {
            @Override
            public void onResponse(Call<JS_SP> call, Response<JS_SP> response) {
                progressDialog.dismiss();
                Log.i(PenjualanJS_SPeditor.class.getSimpleName(), response.toString());
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    finish();
                }else {
                    Toast.makeText(PenjualanJS_SPeditor.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JS_SP> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PenjualanJS_SPeditor.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData(String key, final int id_detail_transaksi_sparepart, final int id_detail_transaksi_jasaservice) {

    }

    private void deleteData(String key, final int id_detail_transaksi_sparepart, final int id_detail_transaksi_jasaservice) {
    }

    private void readMode() {
    }

    private void editMode() {
    }
}
