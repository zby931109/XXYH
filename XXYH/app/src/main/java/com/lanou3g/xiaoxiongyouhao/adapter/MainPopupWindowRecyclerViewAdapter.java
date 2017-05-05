package com.lanou3g.xiaoxiongyouhao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou3g.xiaoxiongyouhao.R;
import com.lanou3g.xiaoxiongyouhao.bean.CarsBean;
import com.lanou3g.xiaoxiongyouhao.tools.PopupWindowRVListener;

import java.util.List;

/**
 * Created by dllo on 17/4/18.
 */

public class MainPopupWindowRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context ;
    private List<CarsBean> carsBeen ;
    private PopupWindowRVListener listener ;
    private static final int TYPE_TITLE = 0;

    public MainPopupWindowRecyclerViewAdapter (Context context) {
        this.context = context;
    }

    public void setAddCarBean(List<CarsBean> bean) {
        this.carsBeen = bean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_TITLE:
                View title = LayoutInflater.from(context).inflate(R.layout.item_popupwindow_revyvlerview_title, parent, false);
                viewHolder = new TitleHolder(title);
                break;
            default:
                View other = LayoutInflater.from(context).inflate(R.layout.item_popupwindow_revyvlerview,parent,false);
                viewHolder = new MainHolder(other);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, final int position) {

        switch (position) {
            case TYPE_TITLE:
                TitleHolder titleHolder = (TitleHolder) holder;
                titleHolder.textView.setText("车辆管理");
                titleHolder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(position);
                    }
                });
                break;
            default:
                MainHolder mainHolder = (MainHolder) holder;

//                Log.d("MainPopupWindowRecycler", carsBeen.get(position - 1).getName().toString());
                mainHolder.textView.setText(carsBeen.get(position-1).getName().toString());


                mainHolder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        listener.onImageViewClick(position);
                    }
                });
                break;
        }

    }

    @Override
    public int getItemViewType (int position) {
        switch (position) {
            case 0:
                return TYPE_TITLE;
            default:

                break;

        }

        return position+1;
    }

    @Override
    public int getItemCount () {

        if (0 == carsBeen.size()){
            return 1 ;
        }

        return carsBeen.size() > 0 ? carsBeen.size()+1 : 1;
    }


    public class TitleHolder extends  RecyclerView.ViewHolder{
        TextView textView ;
        public TitleHolder (View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_popupwindow_rv_title_tv);
        }
    }

    public class MainHolder extends  RecyclerView.ViewHolder{
        TextView textView ;
        public MainHolder (View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_popupwindow_rv_tv);
        }
    }





    public void setRecyclerListener(PopupWindowRVListener listener){
        this.listener = listener ;
    }

}
