package com.example.aplikasikrs.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasikrs.R;

public class CreateMhsActivity extends AppCompatActivity {
    EditText edtNama1, edtNim, edtAlamat1, edtEmail1, edtGelar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mhs);
        this.setTitle("SI KRS - Hai Admin");

//        Button btnSimpanKrs = (Button)findViewById(R.id.btnCreateMhs);
//        btnSimpanKrs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CreateMhsActivity.this, HomeAdmin.class);
//                startActivity(intent);
//            }
//        });

        Button btnSimpan = (Button)findViewById(R.id.btnCreateMhs);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNama1 = (EditText) findViewById(R.id.edtNamaMhs);
                String strNama = edtNama1.getText().toString();

                if(TextUtils.isEmpty(strNama)) {
                    edtNama1.setError("Silahkan Mengisi Nama Dosen");
                    return;
                }
                edtNim = (EditText) findViewById(R.id.edtNim);
                String strNidn = edtNim.getText().toString();

                if(TextUtils.isEmpty(strNidn)) {
                    edtNim.setError("Silahkan Mengisi NIDN Dosen");
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



                AlertDialog.Builder builder = new AlertDialog.Builder(CreateMhsActivity.this);

                builder.setMessage("Apakah anda yakin untuk menyimpan?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CreateMhsActivity.this, "Batal Simpan", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(CreateMhsActivity.this, HomeAdmin.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog dialog = builder.create(); dialog.show();
            }
        });
    }
}
