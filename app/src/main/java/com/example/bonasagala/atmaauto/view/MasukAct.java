package com.example.bonasagala.atmaauto.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.controller.ApiClient;
import com.example.bonasagala.atmaauto.controller.Api_Interface_Masuk;
import com.example.bonasagala.atmaauto.controller.Api_Interface_MasukCS;
import com.example.bonasagala.atmaauto.model.Masuk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasukAct extends AppCompatActivity {

    Button btn_masuk;
    Button btn_masukcs;
    private EditText mUsername, mPassword;
    private ProgressDialog dialog;
    Api_Interface_Masuk apiInterfaceLogin;
    Api_Interface_MasukCS apiInterfaceMasukCS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        btn_masuk = findViewById(R.id.btn_masuk);
        btn_masukcs = findViewById(R.id.btn_masukcs);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);

        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogin();
            }
        });

        btn_masukcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLoginCS();
            }
        });
    }

    public void requestLoginCS() {
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        //////

        if(mUsername.getText().toString().isEmpty() || mPassword.getText().toString().isEmpty())
        {
            Toast.makeText(MasukAct.this, "Mohon isi data dengan lengkap",
                    Toast.LENGTH_SHORT).show();
        }else {
            dialog = new ProgressDialog(MasukAct.this);
            dialog.setTitle("Silakan Tunggu");
            dialog.setMessage("Memuat...");
            dialog.show();

            apiInterfaceMasukCS = ApiClient.getApiClient().create(Api_Interface_MasukCS.class);

            Call<Masuk> call = apiInterfaceMasukCS.loginRequestCS(username, password);
            call.enqueue(new Callback<Masuk>() {
                @Override
                public void onResponse(Call<Masuk> call, Response<Masuk> response) {
                    if (response.isSuccessful()) {
                        dialog.dismiss();
                        String nama_pegawai  = response.body().getNama_pegawai();
                        String role  = response.body().getRole();

                        Toast.makeText(MasukAct.this, "Berhasil masuk", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MasukAct.this, HomeCSact.class);
                        i.putExtra("NAMA_PEGAWAI", nama_pegawai);
                        i.putExtra("ROLE", role);
                        startActivity(i);
                    } else {
                        dialog.dismiss();
                        Toast.makeText(MasukAct.this, "Username atau Password Anda Salah",
                                Toast.LENGTH_SHORT).show();

                        dialog.cancel();
                    }
                }

                @Override
                public void onFailure(Call<Masuk> call, Throwable t) {
                    Log.d("TAG", t.toString());
                    Toast.makeText(MasukAct.this, "Username atau Password Anda Salah", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
        }
    }

    public void requestLogin() {

        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        //////

        if(mUsername.getText().toString().isEmpty() || mPassword.getText().toString().isEmpty())
        {
            Toast.makeText(MasukAct.this, "Mohon isi data dengan lengkap",
                    Toast.LENGTH_SHORT).show();
        }else {
            dialog = new ProgressDialog(MasukAct.this);
            dialog.setTitle("Silakan Tunggu");
            dialog.setMessage("Memuat...");
            dialog.show();

            apiInterfaceLogin = ApiClient.getApiClient().create(Api_Interface_Masuk.class);

            Call<Masuk> call = apiInterfaceLogin.loginRequest(username, password);
            call.enqueue(new Callback<Masuk>() {
                @Override
                public void onResponse(Call<Masuk> call, Response<Masuk> response) {
                    if (response.isSuccessful()) {
                        dialog.dismiss();
                        String nama_pegawai  = response.body().getNama_pegawai();
                        String role  = response.body().getRole();

                        Toast.makeText(MasukAct.this, "Berhasil masuk", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MasukAct.this, HomeAct.class);
                        i.putExtra("NAMA_PEGAWAI", nama_pegawai);
                        i.putExtra("ROLE", role);
                        startActivity(i);
                    } else {
                        dialog.dismiss();
                        Toast.makeText(MasukAct.this, "Username atau Password Anda Salah",
                                Toast.LENGTH_SHORT).show();

                        dialog.cancel();
                    }
                }

                @Override
                public void onFailure(Call<Masuk> call, Throwable t) {
                    Log.d("TAG", t.toString());
                    Toast.makeText(MasukAct.this, "Username atau Password Anda Salah", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
        }
    }
}
