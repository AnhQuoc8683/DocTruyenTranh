package com.doctruyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.doctruyentranh.object.TruyenTranh;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.doctruyentranh.adapter.TruyenTranhadapter;
import com.doctruyentranh.api.ApiLayTruyen;
import com.doctruyentranh.interfaces.LayTruyenVe;
import com.doctruyentranh.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
    GridView gdvDSTuyen;
    TruyenTranhadapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    EditText edtTimkiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayTruyen(this).execute();
    }
    protected int getLayoutResId() {
        return R.layout.nutlienheadmin;
    }

    private void init() {
        truyenTranhArrayList = new ArrayList<>();
        adapter = new TruyenTranhadapter(this, 0, truyenTranhArrayList);
    }

    private void anhXa() {
        gdvDSTuyen = findViewById(R.id.gdvDSTruyen);
        edtTimkiem = findViewById(R.id.edtTimKiem);
    }

    private void setUp() {
        gdvDSTuyen.setAdapter(adapter);
    }

    private void setClick() {
        edtTimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtTimkiem.getText().toString();
                adapter.sortTruyen(s);
            }
        });


        gdvDSTuyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //i = 2;
                l=26;
                TruyenTranh truyenTranh = truyenTranhArrayList.get(i);
                Bundle b = new Bundle();
                b.putSerializable("truyen", truyenTranh);
                Intent intent = new Intent(MainActivity.this, ChapActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        //Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
        CustomToast.showCustomToast(this, "Loading");
    }

    @Override
    public void ketThuc(String data) {
        try {
            truyenTranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i=0;i<arr.length();i++){
                JSONObject o = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter = new TruyenTranhadapter(this, 0, truyenTranhArrayList);
            gdvDSTuyen.setAdapter(adapter);
        }catch (JSONException e){
            CustomToast.showCustomToastupdate(this, "Tải Lại Trang");
        }
        CustomToast.showCustomToastupdate(this, "Done");
    }

    @Override
    public void biloi() {
        CustomToast.showCustomToasterron(this, "Lỗi Kết Nối");
    }

    public void update (View view){
        new ApiLayTruyen(this).execute();
    }
}