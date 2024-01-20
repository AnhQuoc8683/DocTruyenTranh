package com.doctruyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doctruyentranh.adapter.ChapTruyenAdapter;
import com.doctruyentranh.api.ApiChapTruyen;
import com.doctruyentranh.interfaces.LayChapVe;
import com.doctruyentranh.object.ChapTruyen;
import com.doctruyentranh.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity implements LayChapVe {
    TextView txvTenTruyens;
    ImageView imgAnhtruyens;
    TruyenTranh truyenTranh;
    ListView lsvDanhSachChap;
    ArrayList<ChapTruyen> arrChap;
    ChapTruyenAdapter chapTruyenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiChapTruyen(this, this.truyenTranh.getId()).execute();
    }

    private void init() {
        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh = (TruyenTranh) b.getSerializable("truyen");


        arrChap = new ArrayList<>();
        /*for (int i=1;i<20;i++){
            int a = 11;
            a = a+i;
            arrChap.add(new ChapTruyen("Chapter "+i,a+"-1-2024"));
        }
        chapTruyenAdapter = new ChapTruyenAdapter(this,0,arrChap);*/
    }

    ;

    private void anhXa() {
        imgAnhtruyens = findViewById(R.id.imhAnhTruyens);
        txvTenTruyens = findViewById(R.id.txvTenTruyens);
        lsvDanhSachChap = findViewById(R.id.lsvDanhSachChap);
    }

    ;

    private void setUp() {
        txvTenTruyens.setText(truyenTranh.getTenTruyen());
        Glide.with(this).load(truyenTranh.getLinkAnh()).into(imgAnhtruyens);

        //lsvDanhSachChap.setAdapter(chapTruyenAdapter);
    }

    ;

    private void setClick() {
        lsvDanhSachChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Bundle b = new Bundle();
                b.putString("idChap", arrChap.get(i).getId());
                Intent intent = new Intent(ChapActivity.this, DocTruyenActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        CustomToast.showCustomToast(this, "loading");
    }

    @Override
    public void ketThuc(String data) {
        if ("[]\r\n".equals(data)) {
            // Hiển thị thông báo khi chuỗi data rỗng
            CustomToast.showCustomToastupdate(this, "Truyện Sắp Được Ra Mắt");
            return;
        }

        try {
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                ChapTruyen chapTruyen = new ChapTruyen(array.getJSONObject(i));
                arrChap.add(chapTruyen);
            }

            chapTruyenAdapter = new ChapTruyenAdapter(this, 0, arrChap);
            lsvDanhSachChap.setAdapter(chapTruyenAdapter);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        CustomToast.showCustomToastupdate(this, "Done");
    }


    @Override
    public void biloi() {
        CustomToast.showCustomToasterron(this, "Lỗi Kết Nối");
    }

    ;
}