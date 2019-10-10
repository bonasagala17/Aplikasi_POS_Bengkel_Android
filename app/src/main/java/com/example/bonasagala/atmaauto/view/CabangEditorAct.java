package com.example.bonasagala.atmaauto.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.controller.ApiClient;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Cabang;
import com.example.bonasagala.atmaauto.model.Cabang;

public class CabangEditorAct extends AppCompatActivity {

    private EditText mNamaCabang, mAlamatCabang;
    private String nama_cabang, alamat_cabang;
    private int id;

    private Menu action;

    private Api_Interface_Cabang apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabang_editor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mNamaCabang = findViewById(R.id.nama_cabang);
        mAlamatCabang = findViewById(R.id.alamat_cabang);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        nama_cabang = intent.getStringExtra("nama_cabang");
        alamat_cabang = intent.getStringExtra("alamat_cabang");

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {
        if (id != 0) {
            readMode();
            getSupportActionBar().setTitle("Edit " + nama_cabang.toString());

            mNamaCabang.setText(nama_cabang);
            mAlamatCabang.setText(alamat_cabang);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_create);
            requestOptions.error(R.drawable.ic_create);

        }else {
            getSupportActionBar().setTitle("Tambah Cabang");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor_cabang, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id == 0){

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
                imm.showSoftInput(mNamaCabang, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                if (id == 0) {
                    if (TextUtils.isEmpty(mNamaCabang.getText().toString()) ||
                            TextUtils.isEmpty(mAlamatCabang.getText().toString())){
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
                    updateData("update", id);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }
                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(CabangEditorAct.this);
                dialog.setMessage("Menghapus Cabang?");

                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData ("delete", id);
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

    private void postData(final String key){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyimpan...");
        progressDialog.show();

        readMode();

        String nama_cabang = mNamaCabang.getText().toString().trim();
        String alamat_cabang = mAlamatCabang.getText().toString().trim();

        apiInterface = ApiClient.getApiClient().create(Api_Interface_Cabang.class);

        Call<Cabang> call = apiInterface.createCabang(key, nama_cabang, alamat_cabang);

        call.enqueue(new Callback<Cabang>() {
            public void onResponse(Call<Cabang> call, Response<Cabang> response){
                progressDialog.dismiss();

                Log.i(CabangEditorAct.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    finish();
                } else {
                    Toast.makeText(CabangEditorAct.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure (Call<Cabang> call, Throwable t)
            {
                progressDialog.dismiss();
                Toast.makeText(CabangEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String nama_cabang = mNamaCabang.getText().toString().trim();
        String alamat_cabang = mAlamatCabang.getText().toString().trim();

        apiInterface = ApiClient.getApiClient().create(Api_Interface_Cabang.class);

        Call<Cabang> call = apiInterface.updateCabang(key, id, nama_cabang, alamat_cabang);

        call.enqueue(new Callback<Cabang>() {
            public void onResponse(Call<Cabang> call, Response<Cabang> response) {
                progressDialog.dismiss();

                Log.i(CabangEditorAct.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    Toast.makeText(CabangEditorAct.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CabangEditorAct.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<Cabang> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CabangEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteData(String key, int id) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menghapus...");
        progressDialog.show();

        readMode();

        apiInterface = ApiClient.getApiClient().create(Api_Interface_Cabang.class);

        Call<Cabang> call = apiInterface.hapusCabang(key, id);

        call.enqueue(new Callback<Cabang>() {
            public void onResponse(Call<Cabang> call, Response<Cabang> response)
            {
                progressDialog.dismiss();

                Log.i(CabangEditorAct.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    Toast.makeText(CabangEditorAct.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CabangEditorAct.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<Cabang> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CabangEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void editMode() {
        mNamaCabang.setFocusableInTouchMode(true);
        mAlamatCabang.setFocusableInTouchMode(true);
    }

    private void readMode() {
        mNamaCabang.setFocusableInTouchMode(false);
        mAlamatCabang.setFocusableInTouchMode(false);
    }
}