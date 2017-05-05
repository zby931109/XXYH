package com.lanou3g.xiaoxiongyouhao.adapter;

import android.print.PageRange;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.xiaoxiongyouhao.bean.CarsBean;

import java.util.List;

/**
 * Created by dllo on 17/4/18.
 */

public class CarSpinnerAdapter extends BaseAdapter{
    private List<CarsBean> cars ;
    private LayoutInflater layout ;

    public CarSpinnerAdapter (Layout layout, List<CarsBean> cars) {
        this.cars = cars;
    }

    @Override
    public int getCount () {
        return cars.size();
    }

    @Override
    public Object getItem (int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId (int position) {
        return cars.get(position).getId();
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

        }
        TextView textView = (TextView) convertView;
        CarsBean car = (CarsBean) getItem(position);
        textView.setText(car.getName());

        return convertView;
    }
}
