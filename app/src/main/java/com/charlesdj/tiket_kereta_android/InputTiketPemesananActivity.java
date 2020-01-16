package com.charlesdj.tiket_kereta_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InputTiketPemesananActivity extends AppCompatActivity {

    private EditText IdDari, IdKe, IdKls;
    private TextView IdTgl;
    private Button cekpemesanan;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tampilkantanggal;
    private Button btDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tiket_pemesanan);
        getSupportActionBar().hide();

        IdDari = (EditText) findViewById(R.id.edtdari);
        IdKe = (EditText) findViewById(R.id.edtke);
        IdTgl = (TextView) findViewById(R.id.tampiltanggal);
        IdKls = (EditText) findViewById(R.id.listkelalspenumpang);

        cekpemesanan = (Button) findViewById(R.id.btncek);
        cekpemesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(InputTiketPemesananActivity.this, ListPemesananTiket.class);
                i.putExtra("dari", IdDari.getText().toString());
                i.putExtra("ke", IdKe.getText().toString());
                i.putExtra("tanggal", IdTgl.getText().toString());
                i.putExtra("kelas", IdKls.getText().toString());
                startActivity(i);
                finish();
            }
        });
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tampilkantanggal = (TextView) findViewById(R.id.tampiltanggal);
        btDatePicker = (Button) findViewById(R.id.btn_tanggal);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }
    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tampilkantanggal.setText(""+dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(InputTiketPemesananActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
