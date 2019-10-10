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
import com.example.bonasagala.atmaauto.controller.Api_Interface_DetailPenjualanSparepart;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Pegawai;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Penjualan;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Sparepart;
import com.example.bonasagala.atmaauto.controller.Api_interface_Montir;
import com.example.bonasagala.atmaauto.model.Detail_PenjualanSparepart;
import com.example.bonasagala.atmaauto.model.Montir;
import com.example.bonasagala.atmaauto.model.Pegawai;
import com.example.bonasagala.atmaauto.model.Penjualan;
import com.example.bonasagala.atmaauto.model.Sparepart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPenjualanSparepart_Editor extends AppCompatActivity {

    private EditText mTransaksiPembayaran, mJumlah, mSubtotal;
    private Spinner spinnerTransaksiPembayaran, spinnerSparepart, spinnerMontir;
    private List<String> listSpinnerTransaksiPembayaran, listSpinnerSparepart, listSpinnerMontir;

    private String jumlah, subtotal, id_montir, id_sparepart, id_transaksi_pembayaran;
    private int id_detail_transaksi_sparepart;

    private Api_Interface_DetailPenjualanSparepart apiInterfaceDetailPenjualanSparepart;

    private Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penjualan_sparepart__editor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        listSpinnerTransaksiPembayaran = new ArrayList<>();
        listSpinnerSparepart = new ArrayList<>();
        listSpinnerMontir = new ArrayList<>();

        spinnerTransaksiPembayaran = findViewById(R.id.spinnerTransaksiPembayaran);
        spinnerSparepart = findViewById(R.id.spinnerSparepart);
        spinnerMontir =findViewById(R.id.spinnerMontir);

        mJumlah = findViewById(R.id.jumlah);
        mSubtotal = findViewById(R.id.subtotal);
        mTransaksiPembayaran = findViewById(R.id.transaksi_pembayaran);

        Intent intent = getIntent();
        id_detail_transaksi_sparepart = intent.getIntExtra("id_detail_transaksi_sparepart", 0);
        id_sparepart = intent.getStringExtra("id_sparepart");
        id_transaksi_pembayaran = intent.getStringExtra("id_transaksi_pembayaran");
        id_montir = intent.getStringExtra("id_montir");
        jumlah = intent.getStringExtra("jumlah");
        subtotal = intent.getStringExtra("subtotal");

        setDataFromIntentExtra();
        loadSpinnerTransaksiPembayaran();
        loadSpinnerSparepart();
        loadSpinnerMontir();
    }



    private void setDataFromIntentExtra() {
        if (id_detail_transaksi_sparepart != 0) {
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
            getSupportActionBar().setTitle("Tambah Data Detail Penjualan Sparepart");
            mTransaksiPembayaran.setText(id_transaksi_pembayaran);
            mTransaksiPembayaran.setFocusableInTouchMode(false);
            mTransaksiPembayaran.setVisibility(View.GONE);

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor_detail_penjualansparepart, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);
        mTransaksiPembayaran.setText(id_transaksi_pembayaran);

        if (id_detail_transaksi_sparepart == 0){
            mTransaksiPembayaran.setText(id_transaksi_pembayaran);
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
                            spinnerMontir.getSelectedItemPosition()==0 ||
                            TextUtils.isEmpty(mJumlah.getText().toString())
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

                    updateData("update", id_detail_transaksi_sparepart);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }

                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailPenjualanSparepart_Editor.this);
                dialog.setMessage("Menghapus Detail Penjualan Sparepart?");

                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData("delete", id_detail_transaksi_sparepart);
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
        Api_Interface_Penjualan apiInterfacePenjualanSparepart = ApiClient.getApiClient().create(Api_Interface_Penjualan.class);
        Call<List<Penjualan>> listCall = apiInterfacePenjualanSparepart.getPenjulanSparepart();

        listCall.enqueue(new Callback<List<Penjualan>>() {
            @Override
            public void onResponse(Call<List<Penjualan>> call, Response<List<Penjualan>> response) {
                List<Penjualan> penjualan_Models = response.body();
                for(int i = 0; i < penjualan_Models.size(); i++ ){
                    int id_transaksi_pembayaran = penjualan_Models.get(i).getId_transaksi_pembayaran();
                    listSpinnerTransaksiPembayaran.add(String.valueOf(id_transaksi_pembayaran));
                }
                //listSpinnerTransaksiPembayaran.add(0,"-PILIH ID PENJUALAN-");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailPenjualanSparepart_Editor.this,
                        android.R.layout.simple_spinner_item,listSpinnerTransaksiPembayaran);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTransaksiPembayaran.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Penjualan>> call, Throwable t) {

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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailPenjualanSparepart_Editor.this,
                        android.R.layout.simple_spinner_item,listSpinnerSparepart);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSparepart.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Sparepart>> call, Throwable t) {

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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailPenjualanSparepart_Editor.this,
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

        String id_sparepart = spinnerSparepart.getSelectedItem().toString();
        String id_transaksi_pembayaran = spinnerTransaksiPembayaran.getSelectedItem().toString();
        String id_montir = spinnerMontir.getSelectedItem().toString();
        String jumlah = mJumlah.getText().toString().trim();
        String subtotal = mSubtotal.getText().toString().trim();

        apiInterfaceDetailPenjualanSparepart = ApiClient.getApiClient().create(Api_Interface_DetailPenjualanSparepart.class);

        Call<Detail_PenjualanSparepart> call = apiInterfaceDetailPenjualanSparepart.createDetailPenjualanSparepart(key, id_sparepart, id_transaksi_pembayaran, id_montir, jumlah, subtotal);
        call.enqueue(new Callback<Detail_PenjualanSparepart>() {
            @Override
            public void onResponse(Call<Detail_PenjualanSparepart> call, Response<Detail_PenjualanSparepart> response) {
                progressDialog.dismiss();
                Log.i(DetailPenjualanSparepart_Editor.class.getSimpleName(), response.toString());
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    finish();
                }else {
                    Toast.makeText(DetailPenjualanSparepart_Editor.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Detail_PenjualanSparepart> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailPenjualanSparepart_Editor.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData(final String key, final int id_detail_transaksi_sparepart) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String id_sparepart = spinnerSparepart.getSelectedItem().toString();
        String id_transaksi_pembayaran = spinnerTransaksiPembayaran.getSelectedItem().toString();
        String id_montir = spinnerMontir.getSelectedItem().toString();

        String inputSparepart = Character.toString(id_sparepart.charAt(0));
        String inputMontir = Character.toString(id_montir.charAt(0));


        String jumlah = mJumlah.getText().toString().trim();
        String subtotal = mSubtotal.getText().toString().trim();

        apiInterfaceDetailPenjualanSparepart = ApiClient.getApiClient().create(Api_Interface_DetailPenjualanSparepart.class);

        Call<Detail_PenjualanSparepart> call = apiInterfaceDetailPenjualanSparepart.updateDetailPenjualanSparepart(key, id_detail_transaksi_sparepart, inputSparepart, id_transaksi_pembayaran, inputMontir, jumlah, subtotal);
        call.enqueue(new Callback<Detail_PenjualanSparepart>() {
            @Override
            public void onResponse(Call<Detail_PenjualanSparepart> call, Response<Detail_PenjualanSparepart> response) {
                progressDialog.dismiss();
                Log.i(DetailPenjualanSparepart_Editor.class.getSimpleName(), response.toString());
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    finish();
                }else {
                    Toast.makeText(DetailPenjualanSparepart_Editor.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Detail_PenjualanSparepart> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailPenjualanSparepart_Editor.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void deleteData(final String key, final int id_detail_transaksi_sparepart) {
    }

    private void editMode() {
        spinnerSparepart.setEnabled(true);
        spinnerSparepart.setClickable(true);
        spinnerTransaksiPembayaran.setEnabled(true);
        spinnerTransaksiPembayaran.setClickable(true);
        spinnerMontir.setEnabled(true);
        spinnerMontir.setClickable(true);
        mJumlah.setFocusableInTouchMode(true);
        mSubtotal.setFocusableInTouchMode(true);
        mTransaksiPembayaran.setFocusableInTouchMode(false);
    }

    private void readMode() {
        spinnerSparepart.setEnabled(false);
        spinnerSparepart.setClickable(false);
        spinnerTransaksiPembayaran.setEnabled(false);
        spinnerTransaksiPembayaran.setClickable(false);
        spinnerMontir.setEnabled(false);
        spinnerMontir.setClickable(false);
        mJumlah.setFocusableInTouchMode(false);
        mSubtotal.setFocusableInTouchMode(false);
        mTransaksiPembayaran.setFocusableInTouchMode(false);
    }

}
