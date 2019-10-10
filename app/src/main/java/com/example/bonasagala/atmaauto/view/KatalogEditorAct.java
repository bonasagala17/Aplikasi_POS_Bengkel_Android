package com.example.bonasagala.atmaauto.view;

import android.app.AlertDialog;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.controller.ApiClient;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Sparepart;
import com.example.bonasagala.atmaauto.model.Sparepart;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KatalogEditorAct extends AppCompatActivity {

    private EditText mNamaSparepart, mMerkSparepart, mTipeSparepart, mHargaBeliSparepart, mHargaJualSparepart, mStokSparepart, mStokOptimalSparepart, mLetakSparepart;
    private CircleImageView mGambarSparepart;
    private FloatingActionButton mFabPilihGambar;
    private Spinner mSpinnerLetak, mSpinnerRuang;

    private String nama_sparepart, merk_sparepart, tipe_sparepart, gambar_sparepart, code_sparepart, txt_letak, txt_ruang, inputKodeSparepart;

    private int id_sparepart, hargabeli_sparepart, hargajual_sparepart, stok_sparepart, stokoptimal_sparepart;

    private Menu action;
    private Bitmap bitmap;

    private Api_Interface_Sparepart apiInterfaceSparepart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog_editor);

        String.valueOf(hargabeli_sparepart);
        String.valueOf(hargajual_sparepart);
        String.valueOf(stok_sparepart);
        String.valueOf(stokoptimal_sparepart);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mNamaSparepart = findViewById(R.id.nama_sparepart);
        mMerkSparepart = findViewById(R.id.merk_sparepart);
        mTipeSparepart = findViewById(R.id.tipe_sparepart);
        mHargaBeliSparepart = findViewById(R.id.hargabeli_sparepart);
        mHargaJualSparepart = findViewById(R.id.hargajual_sparepart);
        mStokSparepart = findViewById(R.id.stok_sparepart);
        mStokOptimalSparepart = findViewById(R.id.stokoptimal_sparepart);
        mLetakSparepart = findViewById(R.id.code_sparepart);
        mSpinnerLetak = findViewById(R.id.spinner_letakSparepart);
        mSpinnerRuang = findViewById(R.id.spinner_ruangSparepart);


        mGambarSparepart = findViewById(R.id.gambar_sparepart);
        mFabPilihGambar = findViewById(R.id.fabChoosePic);

        mFabPilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });

        Intent intent = getIntent();
        id_sparepart = intent.getIntExtra("id_sparepart", 0);
        nama_sparepart = intent.getStringExtra("nama_sparepart");
        merk_sparepart = intent.getStringExtra("merk_sparepart");
        tipe_sparepart = intent.getStringExtra("tipe_sparepart");
        hargabeli_sparepart = intent.getIntExtra("hargabeli_sparepart", 0);
        hargajual_sparepart = intent.getIntExtra("hargajual_sparepart", 0);
        stok_sparepart = intent.getIntExtra("stok_sparepart", 0);
        stokoptimal_sparepart = intent.getIntExtra("stokoptimal_sparepart",0);
        gambar_sparepart = intent.getStringExtra("gambar_sparepart");
        txt_letak = intent.getStringExtra("txt_letak");
        txt_ruang = intent.getStringExtra("txt_ruang");
        code_sparepart = intent.getStringExtra("code_sparepart");

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {
        if (id_sparepart != 0) {
            readMode();
            getSupportActionBar().setTitle("Edit Sparepart " + nama_sparepart.toString());

            mNamaSparepart.setText(nama_sparepart);
            mMerkSparepart.setText(merk_sparepart);
            mTipeSparepart.setText(tipe_sparepart);
            mHargaBeliSparepart.setText(String.valueOf(hargabeli_sparepart));
            mHargaJualSparepart.setText(String.valueOf(hargajual_sparepart));
            mStokSparepart.setText(String.valueOf(stok_sparepart));
            mStokOptimalSparepart.setText(String.valueOf(stokoptimal_sparepart));

            String txt_letak = code_sparepart.substring(0,3);
            Log.d("letak", txt_letak);

            ArrayAdapter adapLetak = (ArrayAdapter) mSpinnerLetak.getAdapter();
            int letakPosition = adapLetak.getPosition(txt_letak);
            mSpinnerLetak.setSelection(letakPosition);

            String txt_ruang;
            if(code_sparepart.length() == 11){
                txt_ruang = code_sparepart.substring(4, 8);
            }
            else {
                txt_ruang = code_sparepart.substring(4,7);
            }
            Log.d("ruang", txt_ruang);

            ArrayAdapter adapRuang = (ArrayAdapter) mSpinnerRuang.getAdapter();
            int ruangPosition = adapRuang.getPosition(txt_ruang);
            mSpinnerRuang.setSelection(ruangPosition);

            mLetakSparepart.setText(code_sparepart);


            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_create);
            requestOptions.error(R.drawable.ic_create);

            Glide.with(KatalogEditorAct.this)
                    .load(gambar_sparepart)
                    .apply(requestOptions)
                    .into(mGambarSparepart);


        }
        else {
            getSupportActionBar().setTitle("Tambah Sparepart");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getSupportActionBar().setTitle("Data Sparepart");

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                this.finish();

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Galeri"), 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mGambarSparepart.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



    private void readMode() {
        mNamaSparepart.setFocusableInTouchMode(false);
        mMerkSparepart.setFocusableInTouchMode(false);
        mTipeSparepart.setFocusableInTouchMode(false);
        mHargaBeliSparepart.setFocusableInTouchMode(false);
        mHargaJualSparepart.setFocusableInTouchMode(false);
        mStokSparepart.setFocusableInTouchMode(false);
        mStokOptimalSparepart.setFocusableInTouchMode(false);
        mLetakSparepart.setFocusableInTouchMode(false);
        mFabPilihGambar.setVisibility(View.INVISIBLE);
    }
}
