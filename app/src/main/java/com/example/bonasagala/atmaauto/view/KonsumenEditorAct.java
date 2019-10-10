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
import com.example.bonasagala.atmaauto.controller.Api_Interface_Konsumen;
import com.example.bonasagala.atmaauto.model.Konsumen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonsumenEditorAct extends AppCompatActivity {

    private EditText mNamaKonsumen, mTelpKonsumen, mAlamatKonsumen;
    private String nama_konsumen, telp_konsumen, alamat_konsumen;
    private int id_konsumen;

    private Menu action;

    private Api_Interface_Konsumen apiInterfaceKonsumen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsumen_editor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mNamaKonsumen = findViewById(R.id.nama_konsumen);
        mTelpKonsumen = findViewById(R.id.telp_konsumen);
        mAlamatKonsumen = findViewById(R.id.alamat_konsumen);

        Intent intent = getIntent();
        id_konsumen = intent.getIntExtra("id_konsumen", 0);
        nama_konsumen = intent.getStringExtra("nama_konsumen");
        telp_konsumen = intent.getStringExtra("telp_konsumen");
        alamat_konsumen = intent.getStringExtra("alamat_konsumen");

        setDataFromIntentExtra();

    }

    private void setDataFromIntentExtra() {
        if (id_konsumen != 0) {
            readMode();
            getSupportActionBar().setTitle("Edit Data " + nama_konsumen.toString());

            mNamaKonsumen.setText(nama_konsumen);
            mTelpKonsumen.setText(telp_konsumen);
            mAlamatKonsumen.setText(alamat_konsumen);
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_create);
            requestOptions.error(R.drawable.ic_create);

        } else {
            getSupportActionBar().setTitle("Tambah Data Konsumen");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor_konsumen, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id_konsumen == 0) {
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
                imm.showSoftInput(mNamaKonsumen, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                if (id_konsumen == 0) {
                    if (TextUtils.isEmpty(mNamaKonsumen.getText().toString()) ||
                            TextUtils.isEmpty(mTelpKonsumen.getText().toString()) ||
                            TextUtils.isEmpty(mAlamatKonsumen.getText().toString())) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage("Silakan isi data dengan lengkap!");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    } else {
                        postData("insert");
                        action.findItem(R.id.menu_edit).setVisible(true);
                        action.findItem(R.id.menu_save).setVisible(false);
                        action.findItem(R.id.menu_delete).setVisible(true);

                        readMode();
                    }
                } else {
                    updateData("update", id_konsumen);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }
                return true;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(KonsumenEditorAct.this);
                dialog.setMessage("Ingin menghapus data konsumen?");
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData ("delete", id_konsumen);
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

        String nama_konsumen = mNamaKonsumen.getText().toString().trim();
        String telp_konsumen = mTelpKonsumen.getText().toString().trim();
        String alamat_konsumen = mAlamatKonsumen.getText().toString().trim();

        apiInterfaceKonsumen = ApiClient.getApiClient().create(Api_Interface_Konsumen.class);

        Call<Konsumen> call = apiInterfaceKonsumen.createKonsumen(key, nama_konsumen, telp_konsumen, alamat_konsumen);
        call.enqueue(new Callback<Konsumen>() {
            @Override
            public void onResponse(Call<Konsumen> call, Response<Konsumen> response) {
                progressDialog.dismiss();
                Log.i(KonsumenEditorAct.class.getSimpleName(), response.toString());
                String value = response.body().getValueKonsumen();
                String message = response.body().getMessageKonsumen();

                if (value.equals("1")){
                    finish();
                }else {
                    Toast.makeText(KonsumenEditorAct.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Konsumen> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(KonsumenEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData(final String key, final int id_konsumen) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String nama_konsumen = mNamaKonsumen.getText().toString().trim();
        String telp_konsumen = mTelpKonsumen.getText().toString().trim();
        String alamat_konsumen = mAlamatKonsumen.getText().toString().trim();

        apiInterfaceKonsumen = ApiClient.getApiClient().create(Api_Interface_Konsumen.class);

        Call<Konsumen> call = apiInterfaceKonsumen.updateKonsumen(key, id_konsumen, nama_konsumen, telp_konsumen, alamat_konsumen);

        call.enqueue(new Callback<Konsumen>() {
            @Override
            public void onResponse(Call<Konsumen> call, Response<Konsumen> response) {
                progressDialog.dismiss();

                Log.i(KonsumenEditorAct.class.getSimpleName(), response.toString());

                String value = response.body().getValueKonsumen();
                String message = response.body().getMessageKonsumen();

                if (value.equals("1")){
                    Toast.makeText(KonsumenEditorAct.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(KonsumenEditorAct.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Konsumen> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(KonsumenEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData(final String key, final int id_konsumen) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menghapus...");
        progressDialog.show();

        readMode();

        apiInterfaceKonsumen = ApiClient.getApiClient().create(Api_Interface_Konsumen.class);

        Call<Konsumen> call = apiInterfaceKonsumen.hapusKonsumen(key, id_konsumen);

        call.enqueue(new Callback<Konsumen>() {
            @Override
            public void onResponse(Call<Konsumen> call, Response<Konsumen> response) {
                progressDialog.dismiss();

                Log.i(SupplierEditorAct.class.getSimpleName(), response.toString());

                String value = response.body().getValueKonsumen();
                String message = response.body().getMessageKonsumen();

                if (value.equals("1")){
                    Toast.makeText(KonsumenEditorAct.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(KonsumenEditorAct.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Konsumen> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(KonsumenEditorAct.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void editMode () {
        mNamaKonsumen.setFocusableInTouchMode(true);
        mTelpKonsumen.setFocusableInTouchMode(true);
        mAlamatKonsumen.setFocusableInTouchMode(true);
    }

    private void readMode () {
        mNamaKonsumen.setFocusableInTouchMode(false);
        mTelpKonsumen.setFocusableInTouchMode(false);
        mAlamatKonsumen.setFocusableInTouchMode(false);
    }


}


