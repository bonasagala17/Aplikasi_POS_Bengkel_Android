package com.example.bonasagala.atmaauto.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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
import com.example.bonasagala.atmaauto.controller.Api_Interface_Pembelian;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Supplier;
import com.example.bonasagala.atmaauto.model.Pembelian_Sparepart;
import com.example.bonasagala.atmaauto.model.Supplier;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembelianEditorAct extends AppCompatActivity {
    private EditText mTanggalPembelian, mTotalPembayaran, mStatus;
    private Spinner spinnerSupplier;
    private List<String> listSpinner;

    Calendar myCalendar = Calendar.getInstance();

    private String tanggal_pembelian, status, id_supplier, total_pembayaran;
    private int id_pembelian;

    private Api_Interface_Pembelian apiInterfacePembelian;

    private Menu action;

    Button btn_detailPembelianSParepart;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembelian_editor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        btn_detailPembelianSParepart = findViewById(R.id.btn_detailPembelianSParepart);
        btn_detailPembelianSParepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopengadaan = new Intent(PembelianEditorAct.this,Kelola_Data_DetailPembelianAct.class);
                startActivity(gotopengadaan);
            }
        });

        listSpinner = new ArrayList<>();
        spinnerSupplier = findViewById(R.id.spinnerSupplier);
        mTanggalPembelian = findViewById(R.id.tanggal_pembelian);
        mTotalPembayaran = findViewById(R.id.total_pembayaran);
        mStatus = findViewById(R.id.status);

        mTanggalPembelian = findViewById(R.id.tanggal_pembelian);

        mTanggalPembelian.setFocusableInTouchMode(false);
        mTanggalPembelian.setFocusable(false);
        mTanggalPembelian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PembelianEditorAct.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Intent intent = getIntent();
        id_pembelian = intent.getIntExtra("id_pembelian", 0);
        id_supplier = intent.getStringExtra("id_supplier");
        tanggal_pembelian = intent.getStringExtra("tanggal_pembelian");
        status = intent.getStringExtra("status");
        total_pembayaran = intent.getStringExtra("total_pembayaran");

        setDataFromIntentExtra();
        loadSpinnerSupplier();

    }

    private void setDataFromIntentExtra() {
        if (id_pembelian != 0) {
            readMode();
            //getSupportActionBar().setTitle("Edit " + id_pembelian.toString());

            mTanggalPembelian.setText(tanggal_pembelian);
            mTotalPembayaran.setText(total_pembayaran);
            mStatus.setText(status);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_create);
            requestOptions.error(R.drawable.ic_create);
        }
        else {
            getSupportActionBar().setTitle("Tambah Data Pembelian");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor_pembelian, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id_pembelian == 0){
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
                imm.showSoftInput(mStatus, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                if (id_pembelian == 0) {
                    if (spinnerSupplier.getSelectedItemPosition()==0 ||
                            TextUtils.isEmpty(mTanggalPembelian.getText().toString()) ||
                            TextUtils.isEmpty(mTotalPembayaran.getText().toString()) /*||*/
                            /*TextUtils.isEmpty(mStatus.getText().toString())*/){
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

                    updateData("update", id_pembelian);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }

                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(PembelianEditorAct.this);
                dialog.setMessage("Menghapus Pembelian?");

                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData("delete", id_pembelian);
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
            setTanggalPembelian();
        }

    };

    private void setTanggalPembelian() {
        String myFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mTanggalPembelian.setText(sdf.format(myCalendar.getTime()));
    }

    public void loadSpinnerSupplier() {
        Api_Interface_Supplier apiInterfaceSupplier = ApiClient.getApiClient().create(Api_Interface_Supplier.class);
        Call<List<Supplier>> listCall = apiInterfaceSupplier.getSupplier();

        listCall.enqueue(new Callback<List<Supplier>>() {
            @Override
            public void onResponse(Call<List<Supplier>> call, Response<List<Supplier>> response) {
                List<Supplier> supplierModels = response.body();
                for(int i=0; i < supplierModels.size(); i++ ){
                    int id_supplier = supplierModels.get(i).getId_supplier();
                    String nama_supplier =supplierModels.get(i).getNama_supplier();
                    String inputSupplier = id_supplier+ " - "+nama_supplier;
                    listSpinner.add(String.valueOf(inputSupplier));
                }
                listSpinner.add(0,"-PILIH SUPPLIER-");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PembelianEditorAct.this,
                        android.R.layout.simple_spinner_item,listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSupplier.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Supplier>> call, Throwable t) {

            }
        });
    }



    private void postData(final String key) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        readMode();

        String id_supplier = spinnerSupplier.getSelectedItem().toString();
        String tanggal_pembelian = mTanggalPembelian.getText().toString().trim();
        String total_pembayaran = mTotalPembayaran.getText().toString().trim();
        String status = mStatus.getText().toString().trim();

        apiInterfacePembelian = ApiClient.getApiClient().create(Api_Interface_Pembelian.class);

        Call<Pembelian_Sparepart> call = apiInterfacePembelian.createPembelian(key, id_supplier, tanggal_pembelian, total_pembayaran, status);

        call.enqueue(new Callback<Pembelian_Sparepart>() {
            @Override
            public void onResponse(Call<Pembelian_Sparepart> call, Response<Pembelian_Sparepart> response) {
                progressDialog.dismiss();

                Log.i(PembelianEditorAct.class.getSimpleName(), response.toString());

                String valuePembelian = response.body().getValuePembelian();
                String messagePembelian = response.body().getMassagePembelian();

                if (valuePembelian.equals("1")){
                    finish();
                } else {
                    Toast.makeText(PembelianEditorAct.this, messagePembelian, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pembelian_Sparepart> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PembelianEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id_pembelian) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String id_supplier = spinnerSupplier.getSelectedItem().toString();
        String tanggal_pembelian = mTanggalPembelian.getText().toString().trim();
        String total_pembayaran = mTotalPembayaran.getText().toString().trim();
        String status = mStatus.getText().toString().trim();

        apiInterfacePembelian = ApiClient.getApiClient().create(Api_Interface_Pembelian.class);

        Call<Pembelian_Sparepart> call = apiInterfacePembelian.updatePembelian(key, id_pembelian, id_supplier, tanggal_pembelian, total_pembayaran, status);
        call.enqueue(new Callback<Pembelian_Sparepart>() {
            @Override
            public void onResponse(Call<Pembelian_Sparepart> call, Response<Pembelian_Sparepart> response) {
                progressDialog.dismiss();

                Log.i(PembelianEditorAct.class.getSimpleName(), response.toString());

                String valuePembelian = response.body().getValuePembelian();
                String messagePembelian = response.body().getMassagePembelian();

                if (valuePembelian.equals("1")) {
                    Toast.makeText(PembelianEditorAct.this, messagePembelian, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PembelianEditorAct.this, messagePembelian, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pembelian_Sparepart> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PembelianEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData(String key, int id_pembelian) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        apiInterfacePembelian = ApiClient.getApiClient().create(Api_Interface_Pembelian.class);

        Call<Pembelian_Sparepart> call = apiInterfacePembelian.hapusPembelian(key, id_pembelian);

        call.enqueue(new Callback<Pembelian_Sparepart>() {
            @Override
            public void onResponse(Call<Pembelian_Sparepart> call, Response<Pembelian_Sparepart> response) {
                progressDialog.dismiss();

                Log.i(PembelianEditorAct.class.getSimpleName(), response.toString());

                String valuePembelian = response.body().getValuePembelian();
                String messagePembelian = response.body().getMassagePembelian();

                if (valuePembelian.equals("1")){
                    Toast.makeText(PembelianEditorAct.this, messagePembelian, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PembelianEditorAct.this, messagePembelian, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pembelian_Sparepart> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PembelianEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void readMode() {
        spinnerSupplier.setEnabled(false);
        spinnerSupplier.setClickable(false);
        mTanggalPembelian.setFocusableInTouchMode(false);
        mTotalPembayaran.setFocusableInTouchMode(false);
        mStatus.setFocusableInTouchMode(false);
    }

    private void editMode() {
        spinnerSupplier.setEnabled(true);
        spinnerSupplier.setClickable(true);
        mTanggalPembelian.setFocusableInTouchMode(true);
        mTotalPembayaran.setFocusableInTouchMode(true);
        mStatus.setFocusableInTouchMode(true);
    }
}


