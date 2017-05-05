package com.lanou3g.xiaoxiongyouhao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.xiaoxiongyouhao.R;
import com.lanou3g.xiaoxiongyouhao.bean.CarBrandBean;
import com.lanou3g.xiaoxiongyouhao.bean.CarsBean;

import java.util.List;

/**
 * Created by dllo on 17/4/24.
 */

public class SpinnerAddCarBrandAdapter extends BaseAdapter {

    private CarBrandBean carBrandBean ;
    private Context context ;


    public void setAddCarBean(CarBrandBean carBrandBean) {
        this.carBrandBean = carBrandBean;
        notifyDataSetChanged();
    }

    public SpinnerAddCarBrandAdapter (Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        View view = null;
        MainHolder holder = null;
        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_add_car , parent ,false);
            holder = new MainHolder(view);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (MainHolder) view.getTag();
        }
        Log.d("SpinnerAddCarBrandAdapt", "AAAAAA:   "+ carBrandBean.getNames().get(position));
        holder.textView.setText(carBrandBean.getNames().get(position));
        return view;
    }

    @Override
    public long getItemId (int position) {
        long idxes = carBrandBean.getIdxes().get(position);
        return idxes;
    }

    @Override
    public int getCount () {
        return carBrandBean == null || carBrandBean.getNames() == null ? 0 : carBrandBean.getNames().size();
    }

    @Override
    public String getItem (int position) {
        return carBrandBean.getNames().get(position);
    }

    private class MainHolder {

        private View view ;
        private TextView textView;

        public MainHolder (View view) {
            this.view = view;
            textView = (TextView) view.findViewById(R.id.item_add_car_tv);
        }
    }
}
