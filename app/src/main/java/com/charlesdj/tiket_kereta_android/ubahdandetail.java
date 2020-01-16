package com.charlesdj.tiket_kereta_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import Server.ConfigUrl;

public class ubahdandetail extends AppCompatActivity {

    EditText kodebooking, kotaasal, kotatujuan, tanggalberangkat, namastasiun, jadwalkeberangkatan, jadwaltiba, kelaspenumpang, keretaapi, hargatiket;
    Button btnKirim;

    private RequestQueue mRequestQueue;

    private ProgressDialog pDialog;

    Intent intent;
    String detailorupdate, _id, strKodebooking, strKotaasal, strKotatujuan, strtanggalberangkat, strNamastasiun, strJadwalkeberangkatan, strJadwaltiba, strKelaspenumpang, strKeretaapi, strhatgatiket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahdandetail);
        mRequestQueue = Volley.newRequestQueue(this);

        getSupportActionBar().hide();

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        kodebooking = (EditText) findViewById(R.id.kodebooking);
        kotaasal = (EditText) findViewById(R.id.kotaasal);
        kotatujuan = (EditText) findViewById(R.id.kotatujuan);
        tanggalberangkat = (EditText) findViewById(R.id.TanggalBerangkat);
        namastasiun = (EditText) findViewById(R.id.namastasiun);
        jadwalkeberangkatan = (EditText) findViewById(R.id.jadwalkeberangkatan);
        jadwaltiba = (EditText) findViewById(R.id.jadwaltiba);
        kelaspenumpang = (EditText) findViewById(R.id.klspenumpang);
        keretaapi = (EditText) findViewById(R.id.keretaapi);
        hargatiket = (EditText) findViewById(R.id.Harga);
        btnKirim = (Button) findViewById(R.id.btnKirim);


        intent = getIntent();
        detailorupdate          = intent.getStringExtra("detail");
        _id                     = intent.getStringExtra("_id");
        strKodebooking          = intent.getStringExtra("kodebooking");
        strKotaasal             = intent.getStringExtra("kotaasal");
        strKotatujuan           = intent.getStringExtra("kotatujuan");
        strtanggalberangkat     = intent.getStringExtra("tanggalberangkat");
        strNamastasiun          = intent.getStringExtra("namastasiun");
        strJadwalkeberangkatan  = intent.getStringExtra("jadwalkeberangkatan");
        strJadwaltiba           = intent.getStringExtra("jadwaltiba");
        strKelaspenumpang       = intent.getStringExtra("kelaspenumpang");
        strKeretaapi            = intent.getStringExtra("keretaapi");
        strhatgatiket           = intent.getStringExtra("hargatiket");

        kodebooking.setText(strKodebooking);
        kotaasal.setText(strKotaasal);
        kotatujuan.setText(strKotatujuan);
        tanggalberangkat.setText(strtanggalberangkat);
        namastasiun.setText(strNamastasiun);
        jadwalkeberangkatan.setText(strJadwalkeberangkatan);
        jadwaltiba.setText(strJadwaltiba);
        kelaspenumpang.setText(strKelaspenumpang);
        keretaapi.setText(strKeretaapi);
        hargatiket.setText(strhatgatiket);

        if(detailorupdate.equals("detail")){
            kodebooking.setEnabled(false);
            kotaasal.setEnabled(false);
            kotatujuan.setEnabled(false);
            tanggalberangkat.setEnabled(false);
            namastasiun.setEnabled(false);
            jadwalkeberangkatan.setEnabled(false);
            jadwaltiba.setEnabled(false);
            kelaspenumpang.setEnabled(false);
            keretaapi.setEnabled(false);
            hargatiket.setEnabled(false);
            btnKirim.setVisibility(View.GONE);
        }
        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strKodebooking = kodebooking.getText().toString();
                String strKotaasal = kotaasal.getText().toString();
                String strKotatujuan = kotatujuan.getText().toString();
                String strTanggalberangkat = tanggalberangkat.getText().toString();
                String strNamastasiun = namastasiun.getText().toString();
                String strJadwalkeberangkatan = jadwalkeberangkatan.getText().toString();
                String strJadwaltiba = jadwaltiba.getText().toString();
                String strKelaspenumpang = kelaspenumpang.getText().toString();
                String strKeretaapi = keretaapi.getText().toString();
                String strHargatiket = hargatiket.getText().toString();
                if (strKodebooking.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Kode Booking Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strKotaasal.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama Kota Asal Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strKotatujuan.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama Kota Tujuan Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strTanggalberangkat.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Tanggal Berangkat Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strNamastasiun.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama Stasiun Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strJadwalkeberangkatan.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Jadwal Keberangkatan Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strJadwaltiba.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Jadwal Tiba Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strKelaspenumpang.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Kelas Penumpang Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strKeretaapi.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama Kereta Api Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strHargatiket.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Harga Tiket Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }
                ubahMatakuliah(strKodebooking, strKotaasal, strKotatujuan,strTanggalberangkat, strNamastasiun, strJadwalkeberangkatan, strJadwaltiba, strKelaspenumpang, strKeretaapi, strHargatiket);
            }
        });


    }

    private void ubahMatakuliah(String kodebooking, String kotaasal, String kotatujuan, String tanggalberangkat, String namastasiun, String jadwalkeberangkatan, String jadwaltiba, String kelaspenumpang, String keretaapi, String hargatiket){

        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("kodebooking", kodebooking);
        params.put("kotaasal", kotaasal);
        params.put("kotatujuan", kotatujuan);
        params.put("tanggalberangkat", tanggalberangkat);
        params.put("namastasiun", namastasiun);
        params.put("jadwalkeberangkatan", jadwalkeberangkatan);
        params.put("jadwaltiba", jadwaltiba);
        params.put("kelaspenumpang", kelaspenumpang);
        params.put("keretaapi", keretaapi);
        params.put("hargatiket", hargatiket);

        pDialog.setMessage("Mohon tunggu");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.PUT, ConfigUrl.Cektiket + _id, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            String msg;
                            if(status == true){
                                msg = response.getString("pesan");
                            }else {
                                msg = response.getString("pesan");
                                Intent i = new Intent(ubahdandetail.this, ListPemesananTiket.class);
                                startActivity(i);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), msg,
                                    Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        // add the request object to the queue to be executed
        // ApplicationController.getInstance().addToRequestQueue(req);
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(ubahdandetail.this, ListPemesananTiket.class);
        a.putExtra("dari", strKotaasal);
        a.putExtra("ke", strKotatujuan);
        a.putExtra("tanggal", strtanggalberangkat);
        a.putExtra("kelas", strKelaspenumpang);
        startActivity(a);
        finish();
    }
}
