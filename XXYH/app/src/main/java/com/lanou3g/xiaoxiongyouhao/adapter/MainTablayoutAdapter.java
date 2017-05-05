package com.lanou3g.xiaoxiongyouhao.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.xiaoxiongyouhao.R;

import java.util.List;

/**
 * Created by dllo on 17/4/18.
 */

public class MainTablayoutAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments ;
    private String[] text = new String[]{"油耗","排名","油费","费用"};
    private int[] img = new int[]{
            R.mipmap.tubiao ,
            R.mipmap.jiangbei ,
            R.mipmap.tubiao2 ,
            R.mipmap.bingzhuangtu ,
    };


    public MainTablayoutAdapter (FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }



    @Override
    public Fragment getItem (int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount () {
        return fragments.size();
    }


    public View getTabView (int index , Context context){

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View  tabView = layoutInflater.inflate(R.layout.item_tablayout,null);


        TextView textView = (TextView) tabView.findViewById(R.id.tao_text_tv);
        ImageView imageView = (ImageView) tabView.findViewById(R.id.tab_image_iv);

        textView.setText(text[index]);
        imageView.setImageResource(img[index]);

        return tabView ;
    }

}
