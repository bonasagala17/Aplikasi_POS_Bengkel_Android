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
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.controller.ApiClient;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Supplier;
import com.example.bonasagala.atmaauto.model.Supplier;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupplierEditorAct extends AppCompatActivity {

    private EditText mNamaSupplier, mAlamatSupplier, mTelpSupplier, mNamaSales, mTelpSales;
    private String nama_supplier, alamat_supplier, telp_supplier, nama_sales, telp_sales;
    private int id_supplier;

    private Menu action;

    private Api_Interface_Supplier apiInterfaceSupplier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_editor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mNamaSupplier = findViewById(R.id.nama_supplier);
        mAlamatSupplier = findViewById(R.id.alamat_supplier);
        mTelpSupplier = findViewById(R.id.telp_supplier);
        mNamaSales = findViewById(R.id.nama_sales);
        mTelpSales = findViewById(R.id.telp_sales);

        Intent intent = getIntent();
        id_supplier = intent.getIntExtra("id_supplier", 0);
        nama_supplier = intent.getStringExtra("nama_supplier");
        alamat_supplier = intent.getStringExtra("alamat_supplier");
        telp_supplier = intent.getStringExtra("telp_supplier");
        nama_sales = intent.getStringExtra("nama_sales");
        telp_sales = intent.getStringExtra("telp_sales");

        setDataFromIntentExtra();

    }

    private void setDataFromIntentExtra() {
        if (id_supplier != 0) {
            readMode();
            getSupportActionBar().setTitle("Edit Supplier " + nama_supplier.toString());

            mNamaSupplier.setText(nama_supplier);
            mAlamatSupplier.setText(alamat_supplier);
            mTelpSupplier.setText(telp_supplier);
            mNamaSales.setText(nama_sales);
            mTelpSales.setText(telp_sales);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_create);
            requestOptions.error(R.drawable.ic_create);

        }else {
            getSupportActionBar().setTitle("Tambah Supplier");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor_supplier, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id_supplier == 0){
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
                imm.showSoftInput(mNamaSupplier, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                if (id_supplier == 0) {
                    if (TextUtils.isEmpty(mNamaSupplier.getText().toString()) ||
                            TextUtils.isEmpty(mAlamatSupplier.getText().toString()) ||
                            TextUtils.isEmpty(mTelpSupplier.getText().toString()) ||
                            TextUtils.isEmpty(mNamaSales.getText().toString()) ||
                            TextUtils.isEmpty(mTelpSales.getText().toString())){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage("Silakan isi data dengan lengkap!");
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
                    updateData("update", id_supplier);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }
                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(SupplierEditorAct.this);
                dialog.setMessage("Ingin menghapus supplier?");
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData ("delete", id_supplier);
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

    private void postData(final String key) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        readMode();

        String nama_supplier = mNamaSupplier.getText().toString().trim();
        String alamat_supplier = mAlamatSupplier.getText().toString().trim();
        String telp_supplier = mTelpSupplier.getText().toString().trim();
        String nama_sales = mNamaSales.getText().toString().trim();
        String telp_sales = mTelpSales.getText().toString().trim();

        apiInterfaceSupplier = ApiClient.getApiClient().create(Api_Interface_Supplier.class);

        Call<Supplier> call = apiInterfaceSupplier.createSupplier(key, nama_supplier, alamat_supplier, telp_supplier, nama_sales, telp_sales);
        call.enqueue(new Callback<Supplier>() {
            @Override
            public void onResponse(Call<Supplier> call, Response<Supplier> response) {
                progressDialog.dismiss();
                Log.i(SupplierEditorAct.class.getSimpleName(), response.toString());
                String value = response.body().getValueSupplier();
                String message = response.body().getMassageSupplier();

                if (value.equals("1")){
                    finish();
                }else {
                    Toast.makeText(SupplierEditorAct.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Supplier> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SupplierEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData(final String key, final int id_supplier) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String nama_supplier = mNamaSupplier.getText().toString().trim();
        String alamat_supplier = mAlamatSupplier.getText().toString().trim();
        String telp_supplier = mTelpSupplier.getText().toString().trim();
        String nama_sales = mNamaSales.getText().toString().trim();
        String telp_sales = mTelpSales.getText().toString().trim();

        apiInterfaceSupplier = ApiClient.getApiClient().create(Api_Interface_Supplier.class);

        Call<Supplier> call = apiInterfaceSupplier.updateSupplier(key, id_supplier, nama_supplier, alamat_supplier, telp_supplier, nama_sales, telp_sales);

        call.enqueue(new Callback<Supplier>() {
            @Override
            public void onResponse(Call<Supplier> call, Response<Supplier> response) {
                progressDialog.dismiss();

                Log.i(SupplierEditorAct.class.getSimpleName(), response.toString());

                String value = response.body().getValueSupplier();
                String message = response.body().getMassageSupplier();

                if (value.equals("1")){
                    Toast.makeText(SupplierEditorAct.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(SupplierEditorAct.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Supplier> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SupplierEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteData(final String key, final int id_supplier) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menghapus...");
        progressDialog.show();

        readMode();

        apiInterfaceSupplier = ApiClient.getApiClient().create(Api_Interface_Supplier.class);

        Call<Supplier> call = apiInterfaceSupplier.hapusSupplier(key, id_supplier);

        call.enqueue(new Callback<Supplier>() {
            @Override
            public void onResponse(Call<Supplier> call, Response<Supplier> response) {
                progressDialog.dismiss();

                Log.i(SupplierEditorAct.class.getSimpleName(), response.toString());

                String value = response.body().getValueSupplier();
                String message = response.body().getMassageSupplier();

                if (value.equals("1")){
                    Toast.makeText(SupplierEditorAct.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SupplierEditorAct.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Supplier> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SupplierEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void editMode() {
        mNamaSupplier.setFocusableInTouchMode(true);
        mAlamatSupplier.setFocusableInTouchMode(true);
        mTelpSupplier.setFocusableInTouchMode(true);
        mNamaSales.setFocusableInTouchMode(true);
        mTelpSales.setFocusableInTouchMode(true);
    }

    private void readMode() {
        mNamaSupplier.setFocusableInTouchMode(false);
        mAlamatSupplier.setFocusableInTouchMode(false);
        mTelpSupplier.setFocusableInTouchMode(false);
        mNamaSales.setFocusableInTouchMode(false);
        mTelpSales.setFocusableInTouchMode(false);
    }
}
