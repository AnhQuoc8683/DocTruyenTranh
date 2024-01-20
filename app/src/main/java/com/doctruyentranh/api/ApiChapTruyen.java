package com.doctruyentranh.api;

import android.os.AsyncTask;

import com.doctruyentranh.interfaces.LayChapVe;
import com.doctruyentranh.interfaces.LayTruyenVe;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiChapTruyen extends AsyncTask<Void, Void, Void> {
    String data;
    LayChapVe layChapVe;
    String idTruyen;

    public ApiChapTruyen(LayChapVe layChapVe,String idTruyen) {
        this.layChapVe = layChapVe;
        this.layChapVe.batDau();
        this.idTruyen = idTruyen;
    }



    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://dulieuappdoctruyen.000webhostapp.com/layChap.php?id="+idTruyen).build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            data = responseBody.string();
        }catch (IOException e){
            data = null;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data == null){
            this.layChapVe.biloi();
        }else {
            this.layChapVe.ketThuc(data);
        }
    }
}
