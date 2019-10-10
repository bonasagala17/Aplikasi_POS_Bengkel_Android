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
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.controller.ApiClient;
import com.example.bonasagala.atmaauto.controller.Api_Interface_JasaService;
import com.example.bonasagala.atmaauto.controller.Api_Interface_KendaraanCustomer;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Pegawai;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Penjualan;
import com.example.bonasagala.atmaauto.controller.Api_Interface_PenjualanJasaService;
import com.example.bonasagala.atmaauto.controller.Api_interface_Montir;
import com.example.bonasagala.atmaauto.model.JasaService;
import com.example.bonasagala.atmaauto.model.Kendaraan_Customer;
import com.example.bonasagala.atmaauto.model.Montir;
import com.example.bonasagala.atmaauto.model.Pegawai;
import com.example.bonasagala.atmaauto.model.Penjualan;
import com.example.bonasagala.atmaauto.model.Penjualan_JasaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenjualanJasaService_Editor extends AppCompatActivity {

    private EditText mTransaksiPembayaran, mJumlah, mSubtotal;
    private Spinner spinnerTransaksiPembayaran, spinnerJasaService, spinnerMontir, spinnerKendaraanCustomer;
    private List<String> listSpinnerTransaksiPembayaran, listSpinnerJasaService, listSpinnerMontir, listSpinnerKendaraanCustomer;

    private String jumlah, subtotal, id_montir, id_jasaservice, id_transaksi_pembayaran, id_kendaraan_customer;
    private int id_detail_transaksi_jasaservice;

    private Api_Interface_PenjualanJasaService apiInterfaceDetailPenjualanJasaServicet;

    private Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan_jasa_service__editor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        listSpinnerTransaksiPembayaran = new ArrayList<>();
        listSpinnerJasaService = new ArrayList<>();
        listSpinnerMontir = new ArrayList<>();
        listSpinnerKendaraanCustomer = new ArrayList<>();

        spinnerTransaksiPembayaran = findViewById(R.id.spinnerTransaksiPembayaran);
        spinnerJasaService = findViewById(R.id.spinnerJasaService);
        spinnerMontir =findViewById(R.id.spinnerMontir);
        spinnerKendaraanCustomer =findViewById(R.id.spinnerKendaraanCustomer);

        mJumlah = findViewById(R.id.jumlah);
        mSubtotal = findViewById(R.id.subtotal);
        mTransaksiPembayaran = findViewById(R.id.transaksi_pembayaran);

        Intent intent = getIntent();
        id_detail_transaksi_jasaservice = intent.getIntExtra("id_detail_transaksi_jasaservice", 0);
        id_jasaservice = intent.getStringExtra("id_jasaservice");
        id_transaksi_pembayaran = intent.getStringExtra("id_transaksi_pembayaran");

        //kendaraanCustomer

        id_montir = intent.getStringExtra("id_montir");
        jumlah = intent.getStringExtra("jumlah");
        subtotal = intent.getStringExtra("subtotal");

        setDataFromIntentExtra();
        loadSpinnerTransaksiPembayaran();
        loadSpinnerJasaService();
        loadSpinnerMontir();
        loadSpinnerKendaraanCustomer();

    }

    private void setDataFromIntentExtra() {
        if (id_detail_transaksi_jasaservice != 0) {
            readMode();
            //getSupportActionBar().setTitle("Edit " + id_pembelian.toString());

            mSubtotal.setText(subtotal);
            mJumlah.setText(jumlah);
            mTransaksiPembayaran.setText(id_transaksi_pembayaran);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_create);
            requestOptions.error(R.drawable.ic_create);
        }
        else {
            hideTotal();
            getSupportActionBar().setTitle("Tambah Data Penjualan Jasa Service");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor_penjualan_jasaservice, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id_detail_transaksi_jasaservice == 0){
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

                if (id_detail_transaksi_jasaservice == 0) {
                    if (/*spinnerTransaksiPembayaran.getSelectedItemPosition()==0 ||*/
                            /*TextUtils.isEmpty(mTransaksiPembayaran.getText().toString()) ||*/
                            spinnerJasaService.getSelectedItemPosition()==0 ||
                            spinnerMontir.getSelectedItemPosition()==0 /*||
                            TextUtils.isEmpty(mJumlah.getText().toString())*/
                            /*TextUtils.isEmpty(mSubtotal.getText().toString())*/){
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

                    updateData("update", id_detail_transaksi_jasaservice);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    editMode();
                }

                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(PenjualanJasaService_Editor.this);
                dialog.setMessage("Menghapus Detail Penjualan Jasa Service?");

                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData("delete", id_detail_transaksi_jasaservice);
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
        Api_Interface_Penjualan apiInterfacePenjualan = ApiClient.getApiClient().create(Api_Interface_Penjualan.class);
        Call<List<Penjualan>> listCall = apiInterfacePenjualan.getPenjulanSparepart();

        listCall.enqueue(new Callback<List<Penjualan>>() {
            @Override
            public void onResponse(Call<List<Penjualan>> call, Response<List<Penjualan>> response) {
                List<Penjualan> penjualan_Models = response.body();
                for(int i = 0; i < penjualan_Models.size(); i++ ){
                    int id_transaksi_pembayaran = penjualan_Models.get(i).getId_transaksi_pembayaran();
                    listSpinnerTransaksiPembayaran.add(String.valueOf(id_transaksi_pembayaran));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PenjualanJasaService_Editor.this,
                        android.R.layout.simple_spinner_item,listSpinnerTransaksiPembayaran);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTransaksiPembayaran.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Penjualan>> call, Throwable t) {

            }
        });
    }
    private void loadSpinnerKendaraanCustomer() {
        Api_Interface_KendaraanCustomer apiInterfaceKendaraanCustomer = ApiClient.getApiClient().create(Api_Interface_KendaraanCustomer.class);
        Call<List<Kendaraan_Customer>> listCall = apiInterfaceKendaraanCustomer.getKendaraanCustomer();

        listCall.enqueue(new Callback<List<Kendaraan_Customer>>() {
            @Override
            public void onResponse(Call<List<Kendaraan_Customer>> call, Response<List<Kendaraan_Customer>> response) {
                List<Kendaraan_Customer> kendaraanCustomersModels = response.body();
                for(int i=0; i < kendaraanCustomersModels.size(); i++ ){
                    int id_kendaraan_customer = kendaraanCustomersModels.get(i).getId_kendaraan_customer();
                    String plat_nomor = kendaraanCustomersModels.get(i).getPlat_nomor();
                    String inputKendaraanCustomer = id_kendaraan_customer+ " - "+plat_nomor;
                    listSpinnerKendaraanCustomer.add(String.valueOf(inputKendaraanCustomer));
                }
                listSpinnerKendaraanCustomer.add(0,"-PILIH KENDARAAN-");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PenjualanJasaService_Editor.this,
                        android.R.layout.simple_spinner_item,listSpinnerKendaraanCustomer);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKendaraanCustomer.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Kendaraan_Customer>> call, Throwable t) {

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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PenjualanJasaService_Editor.this,
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PenjualanJasaService_Editor.this,
                        android.R.layout.simple_spinner_item,listSpinnerMontir);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMontir.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Pegawai>> call, Throwable t) {

            }
        });
    }

    private void postData(final String key) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        readMode();

        String id_jasaservice = spinnerJasaService.getSelectedItem().toString();
        String id_transaksi_pembayaran = spinnerTransaksiPembayaran.getSelectedItem().toString();
        String id_kendaraan_customer = spinnerKendaraanCustomer.getSelectedItem().toString();
        String id_montir = spinnerMontir.getSelectedItem().toString();
        String jumlah = mJumlah.getText().toString().trim();
        String subtotal = mSubtotal.getText().toString().trim();

        apiInterfaceDetailPenjualanJasaServicet = ApiClient.getApiClient().create(Api_Interface_PenjualanJasaService.class);

        Call<Penjualan_JasaService> call = apiInterfaceDetailPenjualanJasaServicet.create_penjualanJasaService(key, id_jasaservice, id_transaksi_pembayaran, id_kendaraan_customer, id_montir, jumlah, subtotal);
        call.enqueue(new Callback<Penjualan_JasaService>() {
            @Override
            public void onResponse(Call<Penjualan_JasaService> call, Response<Penjualan_JasaService> response) {
                progressDialog.dismiss();
                Log.i(PenjualanJasaService_Editor.class.getSimpleName(), response.toString());
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    finish();
                }else {
                    Toast.makeText(PenjualanJasaService_Editor.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Penjualan_JasaService> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PenjualanJasaService_Editor.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id_detail_transaksi_jasaservice) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        readMode();

        String id_jasaservice = spinnerJasaService.getSelectedItem().toString();
        String id_transaksi_pembayaran = spinnerTransaksiPembayaran.getSelectedItem().toString();
        String id_montir = spinnerMontir.getSelectedItem().toString();
        String jumlah = mJumlah.getText().toString().trim();
        String subtotal = mSubtotal.getText().toString().trim();

        apiInterfaceDetailPenjualanJasaServicet = ApiClient.getApiClient().create(Api_Interface_PenjualanJasaService.class);

        Call<Penjualan_JasaService> call = apiInterfaceDetailPenjualanJasaServicet.updateDetailPenjualanJasaService(key, id_detail_transaksi_jasaservice, id_jasaservice, id_transaksi_pembayaran, id_montir, jumlah, subtotal);
        call.enqueue(new Callback<Penjualan_JasaService>() {
            @Override
            public void onResponse(Call<Penjualan_JasaService> call, Response<Penjualan_JasaService> response) {
                progressDialog.dismiss();
                Log.i(PenjualanJasaService_Editor.class.getSimpleName(), response.toString());
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    finish();
                }else {
                    Toast.makeText(PenjualanJasaService_Editor.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Penjualan_JasaService> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PenjualanJasaService_Editor.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void deleteData(final String key, final int id_detail_transaksi_jasaservice) {
    }

    private void readMode() {
        spinnerJasaService.setEnabled(false);
        spinnerJasaService.setClickable(false);
        spinnerTransaksiPembayaran.setEnabled(false);
        spinnerTransaksiPembayaran.setClickable(false);
        spinnerMontir.setEnabled(false);
        spinnerMontir.setClickable(false);
        spinnerKendaraanCustomer.setEnabled(false);
        spinnerKendaraanCustomer.setClickable(false);
        mJumlah.setFocusableInTouchMode(false);
        mSubtotal.setFocusableInTouchMode(false);
        mTransaksiPembayaran.setFocusableInTouchMode(true);
    }

    private void editMode() {
        spinnerJasaService.setEnabled(true);
        spinnerJasaService.setClickable(true);
        spinnerTransaksiPembayaran.setEnabled(true);
        spinnerTransaksiPembayaran.setClickable(true);
        spinnerMontir.setEnabled(true);
        spinnerMontir.setClickable(true);
        spinnerKendaraanCustomer.setEnabled(true);
        spinnerKendaraanCustomer.setClickable(true);
        mJumlah.setFocusableInTouchMode(true);
        mSubtotal.setFocusableInTouchMode(true);
        mTransaksiPembayaran.setFocusableInTouchMode(false);
    }

    private void hideTotal(){
        mTransaksiPembayaran.setFocusableInTouchMode(false);
        mTransaksiPembayaran.setVisibility(View.GONE);
    }

}
