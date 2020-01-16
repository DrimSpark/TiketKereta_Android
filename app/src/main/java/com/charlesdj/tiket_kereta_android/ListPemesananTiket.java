package com.charlesdj.tiket_kereta_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Server.ConfigUrl;
import adapter.AdapterTiketPemesanan;
import model.TiketPemesananModel;

public class ListPemesananTiket extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterTiketPemesanan adapter;
    ListView list;

    ArrayList<TiketPemesananModel> newsList = new ArrayList<TiketPemesananModel>();

    private RequestQueue mRequestQueue;

    Intent intent;
    String idDadri;
    String idKe;
    String idTgl;
    String idKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pemesanan_tiket);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        intent  =getIntent();

        idDadri = intent.getStringExtra("dari");
        idKe = intent.getStringExtra("ke");
        idTgl = intent.getStringExtra("tanggal");
        idKelas = intent.getStringExtra("kelas");

        Log.v(" dari = ", idDadri + " ke = " + idKe + " tanggal = " + idTgl + " kelas = " + idKelas);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterTiketPemesanan(ListPemesananTiket.this, newsList, mRequestQueue, pDialog);
        list.setAdapter(adapter);
        getAllData();

    }
    private void getAllData() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, ConfigUrl.cekpemesanan + idDadri +"/"+ idKe +"/"+ idTgl +"/" +idKelas, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    TiketPemesananModel cektiket = new TiketPemesananModel();
                                    cektiket.set_id(jsonObject.getString("_id"));
                                    cektiket.setKodebooking(jsonObject.getString("kodebooking"));
                                    cektiket.setKotaasal(jsonObject.getString("kotaasal"));
                                    cektiket.setKotatujuan(jsonObject.getString("kotatujuan"));
                                    cektiket.setTanggalberangkat(jsonObject.getString("tanggalberangkat"));
                                    cektiket.setNamastasiun(jsonObject.getString("namastasiun"));
                                    cektiket.setJadwalkeberangkatan(jsonObject.getString("jadwalkeberangkatan"));
                                    cektiket.setJadwaltiba(jsonObject.getString("jadwaltiba"));
                                    cektiket.setKelaspenumpang(jsonObject.getString("kelaspenumpang"));
                                    cektiket.setKeretaapi(jsonObject.getString("keretaapi"));
                                    cektiket.setHargatiket(jsonObject.getString("hargatiket"));
                                    newsList.add(cektiket);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
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
    public void onBackPressed(){
        Intent i = new Intent(ListPemesananTiket.this, InputTiketPemesananActivity.class);
        startActivity(i);
        finish();
    }
}
