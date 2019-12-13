package com.example.aplikasikrs.Admin;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.aplikasikrs.Admin.Model.DefaultResult;
import com.example.aplikasikrs.Network.DataDosenService;
import com.example.aplikasikrs.R;

import java.io.ByteArrayOutputStream;
import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateDosenActivity extends AppCompatActivity {

    EditText edtNama1, edtNidn1, edtAlamat1, edtEmail1, edtGelar1;
    DataDosenService service;
    ProgressDialog progressDialog;
    String textNama, textNIDN, textAlamat, textEmail, textGelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dosen);
        this.setTitle("SI KRS - Hai Admin");


        Button btnDaftarKrs= (Button) findViewById(R.id.btnSimpanDosen1);
        btnDaftarKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNama1 = (EditText) findViewById(R.id.edtNamaMhs);
                String strNama = edtNama1.getText().toString();

                if(TextUtils.isEmpty(strNama)) {
                    edtNama1.setError("Silahkan Mengisi Nama Dosen");
                    return;
                }
                edtNidn1 = (EditText) findViewById(R.id.edtNim);
                String strNidn = edtNidn1.getText().toString();

                if(TextUtils.isEmpty(strNidn)) {
                    edtNidn1.setError("Silahkan Mengisi NIDN Dosen");
                    return;
                }
                edtAlamat1 = (EditText) findViewById(R.id.edtAlamatMhs);
                String strAlamat = edtAlamat1.getText().toString();

                if(TextUtils.isEmpty(strAlamat)) {
                    edtAlamat1.setError("Silahkan Mengisi Alamat Dosen");
                    return;
                }
                edtEmail1 = (EditText) findViewById(R.id.edtEmailMhs);
                String strEmail = edtEmail1.getText().toString();

                if(TextUtils.isEmpty(strEmail)) {
                    edtEmail1.setError("Gunakan Email Fakultas");
                    return;
                }
                edtGelar1 = (EditText) findViewById(R.id.edtGelar1);
                String strGelar = edtGelar1.getText().toString();

                if(TextUtils.isEmpty(strGelar)) {
                    edtGelar1.setError("Silahkan Mengisi Gelar Dosen");
                    return;
                }

                Intent intent = new Intent(CreateDosenActivity.this, RecyclerViewDaftarDosen.class);
                startActivity(intent);
            }
        });
    }


    private void insertDosen() {

        retrofit2.Call<DefaultResult> call = service.insertDosen("Dendy", "001", "Jogja",
                "dendy@dendy.com", "S.kom", "dendy.jpg", "1");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(retrofit2.Call<DefaultResult> call, Response<DefaultResult> response) {
                System.out.println(response.body().getStatus());
            }


            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                System.out.println("message : " + t.getMessage());
                Toast.makeText(CreateDosenActivity.this,
                        "Something went wrong... please try later!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void insertDosenWithPhoto() {
        File sdcard = Environment.getExternalStorageDirectory();

        //get filenya
        File file = new File(sdcard, "/Download/mantul.jpg");
        String imageToSend = null;
        if (file.exists()) {
            if (!checkpermission()) {
                ActivityCompat
                        .requestPermissions(
                                CreateDosenActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
            Bitmap mantulBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            mantulBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            byte[] bytes = baos.toByteArray();
            imageToSend = Base64.encodeToString(bytes, Base64.DEFAULT);
        }


        retrofit2.Call<DefaultResult> call = service.insertDosenWithPhoto("Dendy", "001", "Jogja",
                "dendy@dendy.com", "S.kom", "dendy.jpg", "72170106");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(retrofit2.Call<DefaultResult> call, Response<DefaultResult> response) {
                System.out.println(response.body().getStatus());
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                System.out.println("message : " + t.getMessage());
                Toast.makeText(CreateDosenActivity.this,
                        "Something went wrong... please try later!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean checkpermission() {
        int result = ContextCompat.checkSelfPermission(CreateDosenActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
