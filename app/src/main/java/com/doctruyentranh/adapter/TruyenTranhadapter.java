package com.doctruyentranh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.doctruyentranh.R;
import com.doctruyentranh.object.TruyenTranh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TruyenTranhadapter extends ArrayAdapter<TruyenTranh> {
    private Context ct;
    private ArrayList<TruyenTranh> arr;

    public TruyenTranhadapter(@NonNull Context context, int resource, @NonNull List<TruyenTranh> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    public void sortTruyen(String s) {
        s = s.toUpperCase();
        List<TruyenTranh> sortedList = new ArrayList<>(arr);

        Collections.sort(sortedList, new Comparator<TruyenTranh>() {
            @Override
            public int compare(TruyenTranh truyen1, TruyenTranh truyen2) {
                String ten1 = truyen1.getTenTruyen().toUpperCase();
                String ten2 = truyen2.getTenTruyen().toUpperCase();

                if (ten1.indexOf(s) >= 0 && ten2.indexOf(s) < 0) {
                    return -1;
                } else if (ten1.indexOf(s) < 0 && ten2.indexOf(s) >= 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // Cập nhật danh sách arr với danh sách đã sắp xếp
        arr.clear();
        arr.addAll(sortedList);

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyen, null);
        }
        if (arr.size() > 0) {
            TruyenTranh truyenTranh = this.arr.get(position);
            TextView tenTenTruyen = convertView.findViewById(R.id.txvTenTruyen);
            TextView tenTenChap = convertView.findViewById(R.id.txvTenChap);
            ImageView imgAnhBia = convertView.findViewById(R.id.imgAnhBia);

            tenTenTruyen.setText(truyenTranh.getTenTruyen());
            tenTenChap.setText(truyenTranh.getTenChap());
            Glide.with(this.ct).load(truyenTranh.getLinkAnh()).into(imgAnhBia);
        }
        return convertView;
    }
}
