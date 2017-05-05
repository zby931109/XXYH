package com.lanou3g.xiaoxiongyouhao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.xiaoxiongyouhao.R;
import com.lanou3g.xiaoxiongyouhao.bean.CarsBean;
import com.lanou3g.xiaoxiongyouhao.tools.DialogRVListener;

import java.util.List;

/**
 * Created by dllo on 17/4/20.
 */

public class DialogRVAdapter extends RecyclerView.Adapter<DialogRVAdapter.MainHolder>{

    private Context context ;
    private List<CarsBean> carsBeen ;
    private DialogRVListener listener ;


    public DialogRVAdapter (Context context) {
        this.context = context;
    }

    public void setAddCarBean(List<CarsBean> bean) {
        this.carsBeen = bean;
        notifyDataSetChanged();
    }

    @Override
    public MainHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        MainHolder holder = null ;
        View view = LayoutInflater.from(context).inflate(R.layout.item_dialog_rv_rv,parent ,false);

        holder = new MainHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder (MainHolder holder, final int position) {
        holder.textView.setText(carsBeen.get(position).getName().toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                listener.onItemClickListener(position);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                listener.onItemDeleteListener(position);
            }
        });

    }

    @Override
    public int getItemCount () {
        return carsBeen.size() > 0 ? carsBeen.size() : 0;
    }

    public class MainHolder extends RecyclerView.ViewHolder{

        TextView textView ;
        ImageView imageView ;

        public MainHolder (View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_dialog_rv_tv);
            imageView = (ImageView) itemView.findViewById(R.id.item_dialog_rv_iv);
        }
    }

    public void setRecyclerListener(DialogRVListener listener){
        this.listener = listener ;
    }


}
