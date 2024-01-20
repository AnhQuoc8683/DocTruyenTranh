package com.doctruyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cuneytayyildiz.gestureimageview.GestureImageView;
import com.doctruyentranh.adapter.TruyenTranhadapter;
import com.doctruyentranh.api.ApiLayAnhNoiDungVe;
import com.doctruyentranh.interfaces.LayAnhNoiDungTruyenVe;
import com.doctruyentranh.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DocTruyenActivity extends AppCompatActivity implements LayAnhNoiDungTruyenVe {

    GestureImageView imgAnh;
    ArrayList<String> arrUrlAnh;
    int soTrang, soTrangDangDoc;
    TextView txvSoTrang;
    String idChap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayAnhNoiDungVe(this, idChap).execute();

    }

    private void init() {

        Bundle b = getIntent().getBundleExtra("data");
        idChap = b.getString("idChap");
    }

    private void anhXa() {
        imgAnh = findViewById(R.id.imgAnh);
        txvSoTrang = findViewById(R.id.txvSoTrang);

    }

    private void setUp() {
        // docTheoTrang(0);
    }

    private void setClick() {

    }

    public void left(View view) {
        docTheoTrang(-1);
    }

    public void right(View view) {
        docTheoTrang(1);

    }

    private void docTheoTrang(int i) {
        soTrangDangDoc = soTrangDangDoc + i;
        if (soTrangDangDoc == 0) {
            soTrangDangDoc = 1;

        }
        if (soTrangDangDoc > soTrang) {
            soTrangDangDoc = soTrang;
        }
        txvSoTrang.setText(soTrangDangDoc + " / " + soTrang);
        Glide.with(this).load(arrUrlAnh.get(soTrangDangDoc - 1)).skipMemoryCache(true).into(imgAnh);
    }

    @Override
    public void batDau() {
        CustomToast.showCustomToast(this, "Loading");
    }

    @Override
    public void ketThuc(String data) {
        arrUrlAnh = new ArrayList<>();
        if ("[]\r\n".equals(data)) {
            // Hiển thị thông báo khi chuỗi data rỗng
            CustomToast.showCustomToastupdate(this, "Chapter Sắp Được Phát Hành");
            finish();
            return;
        }
        try {
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++) {
                arrUrlAnh.add(arr.getString(i));
            }
            soTrangDangDoc = 1;
            soTrang = arrUrlAnh.size();
            docTheoTrang(0);
        } catch (JSONException e) {

        }
        CustomToast.showCustomToastupdate(this, "Done");
    }

    @Override
    public void biloi() {
        CustomToast.showCustomToasterron(this, "Lỗi Kết Nối");
    }
}