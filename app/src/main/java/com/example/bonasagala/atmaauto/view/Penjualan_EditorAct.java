package com.example.bonasagala.atmaauto.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.controller.ApiClient;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Cabang;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Konsumen;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Penjualan;
import com.example.bonasagala.atmaauto.model.Cabang;
import com.example.bonasagala.atmaauto.model.Konsumen;
import com.example.bonasagala.atmaauto.model.Penjualan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Penjualan_EditorAct extends AppCompatActivity {

    private EditText mNo_Transaksi, mTanggalTransaksi, mDiskon, mTotalTransaksi, mStatusPembayaran;
    private Spinner spinnerKonsumen, spinnerCabang, mSpinnerPenjualan;
    private List<String> listSpinnerKonsumen, listSpinnerCabang;

    Calendar myCalendar = Calendar.getInstance();

    private String id_konsumen, id_cabang, no_transaksi, tanggal_transaksi, diskon, total_transaksi, status_pembayaran, txt_penjualan, jenis_penjualan;
    private int id_transaksi_pembayaran;

    private Api_Interface_Penjualan apiInterfacePenjualanSparepart;

    private Menu action;

    Button btn_detailPenjualanSparepart;
    Button btn_detailPenjualanJasaService;
    Button btn_detailPenjualanJS_SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan_editor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        btn_detailPenjualanSparepart = findViewById(R.id.btn_detailPenjualanSparepart);
        btn_detailPenjualanSparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetailpenjualanSparepart = new Intent(Penjualan_EditorAct.this,Kelola_Data_Detail_Penjualan_Sparepart.class);
                startActivity(gotoDetailpenjualanSparepart);
            }
        });

        btn_detailPenjualanJasaService = findViewById(R.id.btn_detailPenjualanJasaService);
        btn_detailPenjualanJasaService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotoDetailpenjualanJasaService = new Intent(Penjualan_EditorAct.this,Kelola_Data_Penjualan_JasaService.class);
                //gotoDetailpenjualanJasaService.putExtra("id_transaksi_pembayaran",id_transaksi_pembayaran);
                startActivity(gotoDetailpenjualanJasaService);
            }
        });


        btn_detailPenjualanJS_SP = findViewById(R.id.btn_detailPenjualanJS_SP);
        btn_detailPenjualanJS_SP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoJS_SP = new Intent(Penjualan_EditorAct.this,Kelola_Data_PenjualanJS_SP.class);
                startActivity(gotoJS_SP);
            }
        });


        listSpinnerKonsumen = new ArrayList<>();
        listSpinnerCabang = new ArrayList<>();
        spinnerKonsumen = findViewById(R.id.spinnerKonsumen);
        spinnerCabang = findViewById(R.id.spinnerCabang);

        mNo_Transaksi = findViewById(R.id.no_transaksi);
        mTanggalTransaksi = findViewById(R.id.tanggal_transaksi);
        mDiskon = findViewById(R.id.diskon);
        mTotalTransaksi = findViewById(R.id.total_transaksi);
        mStatusPembayaran = findViewById(R.id.status_pembayaran);

        mTanggalTransaksi = findViewById(R.id.tanggal_transaksi);

        mSpinnerPenjualan = findViewById(R.id.spinner_penjualan);
        String text = mSpinnerPenjualan.getSelectedItem().toString();


        mTanggalTransaksi.setFocusableInTouchMode(false);
        mTanggalTransaksi.setFocusable(false);
        mTanggalTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Penjualan_EditorAct.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Intent intent = getIntent();
        id_transaksi_pembayaran = intent.getIntExtra("id_transaksi_pembayaran", 0);
        id_konsumen = intent.getStringExtra("id_konsumen");
        id_cabang = intent.getStringExtra("id_cabang");
        no_transaksi = intent.getStringExtra("no_transaksi");
        tanggal_transaksi = intent.getStringExtra("tanggal_transaksi");
        diskon = intent.getStringExtra("diskon");
        total_transaksi = intent.getStringExtra("total_transaksi");
        status_pembayaran = intent.getStringExtra("status_pembayaran");
        jenis_penjualan = intent.getStringExtra("jenis_penjualan");

        setDataFromIntentExtra();
        loadSpinnerKonsumen();
        loadSpinnerCabang();
    }

    private void setDataFromIntentExtra() {
        if (id_transaksi_pembayaran != 0) {
            readMode();

            mNo_Transaksi.setText(no_transaksi);
            mTanggalTransaksi.setText(tanggal_transaksi);
            mDiskon.setText(diskon);
            mTotalTransaksi.setText(total_transaksi);
            mStatusPembayaran.setText(status_pembayaran);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_create);
            requestOptions.error(R.drawable.ic_create);
        }
        else {
            hideTotal();
            getSupportActionBar().setTitle("Tambah Data Penjualan");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor_penjualan_sparepart, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id_transaksi_pembayaran == 0){
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
                hideSpinnerPenjualan();
                editMode();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mTanggalTransaksi, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                if (id_transaksi_pembayaran == 0) {
                    if (spinnerCabang.getSelectedItemPosition()==0 ||
                            spinnerKonsumen.getSelectedItemPosition()==0 /*||*/
                            /*TextUtils.isEmpty(mNo_Transaksi.getText().toString()) ||*/
                            /*TextUtils.isEmpty(mTanggalTransaksi.getText().toString()) ||*/
                            /*TextUtils.isEmpty(mDiskon.getText().toString()) ||*/
                            /*TextUtils.isEmpty(mTotalTransaksi.getText().toString())*/ /*||
                            TextUtils.isEmpty(mStatusPembayaran.getText().toString())*/){
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

                    updateData("update", id_transaksi_pembayaran);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }

                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(Penjualan_EditorAct.this);
                dialog.setMessage("Menghapus Penjualan?");

                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData("delete", id_transaksi_pembayaran);
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

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setTanggalTransaksi();
        }

    };

    private void setTanggalTransaksi() {
        String myFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mTanggalTransaksi.setText(sdf.format(myCalendar.getTime()));
    }

    private void loadSpinnerKonsumen() {
        Api_Interface_Konsumen apiInterfaceKonsumen = ApiClient.getApiClient().create(Api_Interface_Konsumen.class);
        Call<List<Konsumen>> listCall = apiInterfaceKonsumen.getKonsumen();

        listCall.enqueue(new Callback<List<Konsumen>>() {
            @Override
            public void onResponse(Call<List<Konsumen>> call, Response<List<Konsumen>> response) {
                List<Konsumen> konsumenModels = response.body();
                for(int i=0; i < konsumenModels.size(); i++ ){
                    int id_konsumen = konsumenModels.get(i).getId_konsumen();
                    String nama_konsumen = konsumenModels.get(i).getNama_konsumen();
                    String inputCabang = id_konsumen+ " - "+nama_konsumen;
                    listSpinnerKonsumen.add(String.valueOf(inputCabang));
                }
                listSpinnerKonsumen.add(0,"-PILIH KONSUMEN-");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Penjualan_EditorAct.this,
                        android.R.layout.simple_spinner_item,listSpinnerKonsumen);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKonsumen.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Konsumen>> call, Throwable t) {

            }
        });
    }

    private void loadSpinnerCabang() {
        Api_Interface_Cabang apiInterfaceCabang = ApiClient.getApiClient().create(Api_Interface_Cabang.class);
        Call<List<Cabang>> listCall = apiInterfaceCabang.getCabang();

        listCall.enqueue(new Callback<List<Cabang>>() {
            @Override
            public void onResponse(Call<List<Cabang>> call, Response<List<Cabang>> response) {
                List<Cabang> cabangModels = response.body();
                for(int i=0; i < cabangModels.size(); i++ ){
                    int id = cabangModels.get(i).getId();
                    String nama_cabang =cabangModels.get(i).getNama_cabang();
                    String inputCabang = id+ " - "+nama_cabang;
                    listSpinnerCabang.add(String.valueOf(inputCabang));
                }
                listSpinnerCabang.add(0,"-PILIH CABANG-");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Penjualan_EditorAct.this,
                        android.R.layout.simple_spinner_item,listSpinnerCabang);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCabang.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Cabang>> call, Throwable t) {

            }
        });
    }

    private void postData(final String key) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        readMode();

        String id_konsumen = spinnerKonsumen.getSelectedItem().toString();
        String id_cabang = spinnerCabang.getSelectedItem().toString();
        String no_transaksi = mNo_Transaksi.getText().toString().trim();
        String tanggal_transaksi = mTanggalTransaksi.getText().toString().trim();
        String diskon = mDiskon.getText().toString().trim();
        String total_transaksi = mTotalTransaksi.getText().toString().trim();
        String status_pembayaran = mStatusPembayaran.getText().toString().trim();
        String jenis_penjualan = mSpinnerPenjualan.getSelectedItem().toString().trim();

        apiInterfacePenjualanSparepart = ApiClient.getApiClient().create(Api_Interface_Penjualan.class);

        Call<Penjualan> call = apiInterfacePenjualanSparepart.createPenjulanSparepart(key, id_konsumen, id_cabang, no_transaksi, tanggal_transaksi, diskon, jenis_penjualan, total_transaksi, status_pembayaran);
        call.enqueue(new Callback<Penjualan>() {
            @Override
            public void onResponse(Call<Penjualan> call, Response<Penjualan> response) {
                progressDialog.dismiss();

                Log.i(Penjualan_EditorAct.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    finish();
                } else {
                    Toast.makeText(Penjualan_EditorAct.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Penjualan> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Penjualan_EditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData(final String key, final int id_transaksi_pembayaran) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String id_konsumen = spinnerKonsumen.getSelectedItem().toString();
        String id_cabang = spinnerCabang.getSelectedItem().toString();
        String no_transaksi = mNo_Transaksi.getText().toString().trim();
        String tanggal_transaksi = mTanggalTransaksi.getText().toString().trim();
        String diskon = mDiskon.getText().toString().trim();
        String total_transaksi = mTotalTransaksi.getText().toString().trim();
        String status_pembayaran = mStatusPembayaran.getText().toString().trim();

        String inputKonsumen = Character.toString(id_konsumen.charAt(0));
        String inputCabang = Character.toString(id_cabang.charAt(0));

        apiInterfacePenjualanSparepart = ApiClient.getApiClient().create(Api_Interface_Penjualan.class);

        Call<Penjualan> call = apiInterfacePenjualanSparepart.updatePenjulanSparepart(key, id_transaksi_pembayaran, inputKonsumen, inputCabang, no_transaksi, tanggal_transaksi, diskon, total_transaksi, status_pembayaran);

        call.enqueue(new Callback<Penjualan>() {
            @Override
            public void onResponse(Call<Penjualan> call, Response<Penjualan> response) {
                progressDialog.dismiss();

                Log.i(Penjualan_EditorAct.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")) {
                    Toast.makeText(Penjualan_EditorAct.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Penjualan_EditorAct.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Penjualan> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Penjualan_EditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData(final String key, final int id_penjualan_sparepart) {

    }


    private void readMode() {
        spinnerCabang.setEnabled(false);
        spinnerCabang.setClickable(false);
        spinnerKonsumen.setEnabled(false);
        spinnerKonsumen.setClickable(false);
        mNo_Transaksi.setFocusableInTouchMode(false);
        mTanggalTransaksi.setFocusableInTouchMode(false);
        mDiskon.setFocusableInTouchMode(false);
        mTotalTransaksi.setFocusableInTouchMode(false);
        mStatusPembayaran.setFocusableInTouchMode(false);
        mSpinnerPenjualan.setEnabled(false);
        mSpinnerPenjualan.setClickable(false);
    }

    private void editMode() {
        spinnerCabang.setEnabled(true);
        spinnerCabang.setClickable(true);
        spinnerKonsumen.setEnabled(true);
        spinnerKonsumen.setClickable(true);
        mNo_Transaksi.setFocusableInTouchMode(true);
        mTanggalTransaksi.setFocusableInTouchMode(true);
        mDiskon.setFocusableInTouchMode(true);
        mTotalTransaksi.setFocusableInTouchMode(true);
        mStatusPembayaran.setFocusableInTouchMode(true);
        mSpinnerPenjualan.setEnabled(true);
        mSpinnerPenjualan.setClickable(true);
    }

    private void hideTotal(){
        mTotalTransaksi.setFocusableInTouchMode(false);
        mTotalTransaksi.setVisibility(View.GONE);

    }

    private void showTotal(){
        mTotalTransaksi.setFocusableInTouchMode(true);
    }

    private void hideSpinnerPenjualan(){
        mSpinnerPenjualan.setVisibility(View.GONE);
    }
}
