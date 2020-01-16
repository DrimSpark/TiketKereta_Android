package adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.charlesdj.tiket_kereta_android.R;
import com.charlesdj.tiket_kereta_android.ubahdandetail;

import java.util.List;

import model.TiketPemesananModel;

public class AdapterTiketPemesanan extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<TiketPemesananModel> item;

    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    public AdapterTiketPemesanan(Activity activity, List<TiketPemesananModel> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
        this.activity = activity;
        this.item = item;
        this.mRequestQueue = mRequestQueue;
        this.pDialog = pDialog;
    }
    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.conten_tiketpemesanan, null);


        TextView kodebooking = (TextView) convertView.findViewById(R.id.txtkode);
        TextView dari = (TextView) convertView.findViewById(R.id.txtdari);
        TextView tujuan = (TextView) convertView.findViewById(R.id.txttujuan);
        TextView tanggal = (TextView) convertView.findViewById(R.id.txttanggalberangkat);
        TextView harga = (TextView) convertView.findViewById(R.id.txthargatiket);
        Button btnDetail = (Button) convertView.findViewById(R.id.btnDetail);
        Button btnPesan = (Button) convertView.findViewById(R.id.btnpesan);

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, ubahdandetail.class);
                i.putExtra("_id", item.get(position).get_id());
                i.putExtra("kodebooking", item.get(position).getKodebooking());
                i.putExtra("kotaasal", item.get(position).getKotaasal());
                i.putExtra("kotatujuan", item.get(position).getKotatujuan());
                i.putExtra("tanggalberangkat", item.get(position).getTanggalberangkat());
                i.putExtra("namastasiun", item.get(position).getNamastasiun());
                i.putExtra("jadwalkeberangkatan", item.get(position).getJadwalkeberangkatan());
                i.putExtra("jadwaltiba", item.get(position).getJadwaltiba());
                i.putExtra("kelaspenumpang", item.get(position).getKelaspenumpang());
                i.putExtra("keretaapi", item.get(position).getKeretaapi());
                i.putExtra("hargatiket", item.get(position).getHargatiket());
                i.putExtra("detail", "detail");
                activity.startActivity(i);
                activity.finish();
            }
        });

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, ubahdandetail.class);
                i.putExtra("_id", item.get(position).get_id());
                i.putExtra("kodebooking", item.get(position).getKodebooking());
                i.putExtra("kotaasal", item.get(position).getKotaasal());
                i.putExtra("kotatujuan", item.get(position).getKotatujuan());
                i.putExtra("tanggalberangkat", item.get(position).getTanggalberangkat());
                i.putExtra("namastasiun", item.get(position).getNamastasiun());
                i.putExtra("jadwalkeberangkatan", item.get(position).getJadwalkeberangkatan());
                i.putExtra("jadwaltiba", item.get(position).getJadwaltiba());
                i.putExtra("kelaspenumpang", item.get(position).getKelaspenumpang());
                i.putExtra("keretaapi", item.get(position).getKeretaapi());
                i.putExtra("hargatiket", item.get(position).getHargatiket());
                i.putExtra("detail", "pesan");
                activity.startActivity(i);
                activity.finish();
            }
        });

        kodebooking.setText(item.get(position).getKodebooking());
        dari.setText(item.get(position).getKotaasal());
        tujuan.setText(item.get(position).getKotatujuan());
        tanggal.setText(item.get(position).getTanggalberangkat());
        harga.setText(item.get(position).getHargatiket());

        return convertView;
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
