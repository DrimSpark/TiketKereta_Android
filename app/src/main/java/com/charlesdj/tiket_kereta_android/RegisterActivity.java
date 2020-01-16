package com.charlesdj.tiket_kereta_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;

    private Button LinkRegister;
    //inputdata
    private EditText Nlengkap, userreq, alamt, passreq;
    private Button register;

    //user interfaces loading
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        mRequestQueue = Volley.newRequestQueue(this);

        //user interfaces loading

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Nlengkap = (EditText) findViewById(R.id.EdtNamaLengkap);
        userreq = (EditText) findViewById(R.id.EdtUsernameRegister);
        alamt = (EditText) findViewById(R.id.EdtAlamat);
        passreq = (EditText) findViewById(R.id.EdtPasswordRegister);

        register = (Button) findViewById(R.id.btnregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNlengkap = Nlengkap.getText().toString();
                String struserregister = userreq.getText().toString();
                String stralamat = alamt.getText().toString();
                String strpassreq = passreq.getText().toString();

                if(strNlengkap.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else if (struserregister.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama Lengkap tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else if (stralamat.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Alamat tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else if (strpassreq.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else {
                    inputdata(strNlengkap,struserregister,stralamat,strpassreq);
                }

            }
        });
        getSupportActionBar().hide();
        //link
        LinkRegister = (Button)findViewById(R.id.btnlinklogin);
        LinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void inputdata(String username, String namalengkap, String alamat, String password) {
        //final String URL = "/volley/resource/12";
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("namalengkap", namalengkap);
        params.put("alamat", alamat);
        params.put("password", password);
        //userinterfaces
        pDialog.setMessage("Mohon Tunggu");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.inputusr, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //userinterfaces
                        hideDialog();

                        try {
                            //pesan error
                            boolean status = response.getBoolean("error");
                            String msg;
                            if (status == true){
                                msg = response.getString("pesan");
                            }else {
                                msg = response.getString("pesan");

                                Intent a = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(a);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            //batas error

                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //user interfaces
                hideDialog();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed
//ApplicationController.getInstance().addToRequestQueue(req);
        mRequestQueue.add(req);
    }
    //user interfaces loading
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();

    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    //batas interfaces loading
}
