package com.doctruyentranh.api;

import android.os.AsyncTask;

import com.doctruyentranh.interfaces.LayAnhNoiDungTruyenVe;
import com.doctruyentranh.interfaces.LayTruyenVe;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiLayAnhNoiDungVe extends AsyncTask<Void, Void, Void> {
    String data;
    String idChap;
    LayAnhNoiDungTruyenVe layAnhNoiDungTruyenVe;

    public ApiLayAnhNoiDungVe(LayAnhNoiDungTruyenVe layAnhNoiDungTruyenVe, String idChap) {
        this.layAnhNoiDungTruyenVe = layAnhNoiDungTruyenVe;
        this.idChap = idChap;
        this.layAnhNoiDungTruyenVe.batDau();
    }


    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://dulieuappdoctruyen.000webhostapp.com/layAnhNoiDung.php?idChap=" + idChap).build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            data = responseBody.string();
        } catch (IOException e) {
            data = null;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data == null) {
            this.layAnhNoiDungTruyenVe.biloi();
        } else {
            this.layAnhNoiDungTruyenVe.ketThuc(data);
        }
    }
}
