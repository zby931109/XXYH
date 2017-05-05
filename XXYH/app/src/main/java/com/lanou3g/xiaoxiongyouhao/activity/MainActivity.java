package com.lanou3g.xiaoxiongyouhao.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou3g.xiaoxiongyouhao.R;
import com.lanou3g.xiaoxiongyouhao.sql.ADBCommand;
import com.lanou3g.xiaoxiongyouhao.sql.UseADB;
import com.lanou3g.xiaoxiongyouhao.adapter.DialogRVAdapter;
import com.lanou3g.xiaoxiongyouhao.adapter.MainPopupWindowRecyclerViewAdapter;
import com.lanou3g.xiaoxiongyouhao.adapter.MainTablayoutAdapter;
import com.lanou3g.xiaoxiongyouhao.bean.CarsBean;
import com.lanou3g.xiaoxiongyouhao.bean.RecordsBean;
import com.lanou3g.xiaoxiongyouhao.fragment.FourFragment;
import com.lanou3g.xiaoxiongyouhao.fragment.OneFragment;
import com.lanou3g.xiaoxiongyouhao.fragment.ThreeFragment;
import com.lanou3g.xiaoxiongyouhao.fragment.TwoFragment;
import com.lanou3g.xiaoxiongyouhao.tools.DialogRVListener;
import com.lanou3g.xiaoxiongyouhao.tools.PopupWindowRVListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupWindowRVListener ,DialogRVListener{

    private ADBCommand adbCommand ;
    private RecordsBean bean ;
    private UseADB useADB ;

    private List<Fragment> fragments ;
    private TabLayout tabLayout ;
    private ViewPager viewPager ;
    private MainTablayoutAdapter mainTablayoutAdapter ;

    private TextView carManagementTv ;
    private ImageView addIv , listIv , eyeIv ,shareIv ;
    private boolean isShowing = false ;
    private PopupWindow popupWindow ;
    private RecyclerView popupWindowRV,dialogRV;
    private MainPopupWindowRecyclerViewAdapter popupWindowAdapter ;
    private int dialogId = 0 ;
    private List<CarsBean> listBean ;

    private AlertDialog dialog ;




    private Spinner spinner ;
    private CarsBean carsBean ;
    private DialogRVAdapter dialogRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.main_tl_tl);
        viewPager = (ViewPager) findViewById(R.id.main_vp_vp);

        addIv = (ImageView) findViewById(R.id.main_add_iv);
        listIv = (ImageView) findViewById(R.id.main_list_iv);
        eyeIv = (ImageView) findViewById(R.id.main_eye_iv);
        shareIv = (ImageView) findViewById(R.id.main_share_iv);

        carManagementTv = (TextView) findViewById(R.id.main_car_management_tv);

        adbCommand = new ADBCommand(this);
        useADB = new UseADB(this);

        fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());

        mainTablayoutAdapter = new MainTablayoutAdapter(getSupportFragmentManager() , fragments);
        viewPager.setAdapter(mainTablayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < mainTablayoutAdapter.getCount(); i++) {
            View tabView = mainTablayoutAdapter.getTabView(i , this);

            TabLayout.Tab tab = tabLayout.getTabAt(i);

            tab.setCustomView(tabView);
        }

        //右上角车辆管理
        carManagementTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (isShowing == false){
                    Toast.makeText(MainActivity.this, "00001", Toast.LENGTH_SHORT).show();
                    showPopupWindow();

                    isShowing = true;
                }
                else {
                    popupWindow.dismiss();
                    isShowing = false ;
                }
            }
        });


//        carsBean = new CarsBean();
//        carsBean.setName("第二辆车");
//        carsBean.setSelected(0);
//        carsBean.setUuid(67890);
//        carsBean.setModel(25901);
//        useADB.insertCars(carsBean);














//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item);



        useADB.queryRecordsAll(new UseADB.QueryRecordsAll() {
            @Override
            public void onGetData (List<RecordsBean> list) {
            }
        });


        //左下角添加
        addIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(MainActivity.this ,AddRecordsActivity.class);
                startActivity(intent);
            }
        });



    }



    private void showPopupWindow (){

        View view = LayoutInflater.from(this).inflate(R.layout.item_popupwindow,null);
        popupWindowRV = (RecyclerView) view.findViewById(R.id.item_popupwindow_rv);
        popupWindowAdapter = new MainPopupWindowRecyclerViewAdapter(MainActivity.this);


        popupWindow = new PopupWindow(  ViewGroup.LayoutParams.WRAP_CONTENT   ,   ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);


        popupWindow.showAsDropDown(carManagementTv,0,0,Gravity.RIGHT);


       //
        popupWindowAdapter.setRecyclerListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        popupWindowRV.setLayoutManager(linearLayoutManager);


        useADB.queryCarsAll(new UseADB.QueryCarsAll() {
            @Override
            public void onGetData (List<CarsBean> list) {
                Log.d("MainActivity", "list.size():" + list.size());
                popupWindowAdapter.setAddCarBean(list);
                popupWindowAdapter.notifyDataSetChanged();
                popupWindowRV.setAdapter(popupWindowAdapter);

            }
        });



//        popupWindowUrl = ConfigManager.CONFIG.getString("listurl" , null);
//        Log.d("MainActivity", "BBBB: "+popupWindowUrl);
//        getData(popupWindowUrl);

    }


    //Dialog
    private void showDialog(){

        View view = LayoutInflater.from(this).inflate(R.layout.item_dialog,null);
        ImageView addIv = (ImageView) view.findViewById(R.id.item_dialog_add_iv);
        dialogRV = (RecyclerView) view.findViewById(R.id.item_dialog_rv);
        TextView closeTv = (TextView) view.findViewById(R.id.item_dialog_close_tv);

        dialogRVAdapter = new DialogRVAdapter(MainActivity.this);
        dialogRVAdapter.setRecyclerListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        dialogRV.setLayoutManager(linearLayoutManager);

        useADB.queryCarsAll(new UseADB.QueryCarsAll() {
            @Override
            public void onGetData (List<CarsBean> list) {

                listBean = list ;

                dialogRVAdapter.setAddCarBean(list);
                dialogRV.setAdapter(dialogRVAdapter);
            }
        });



        //右上角添加
        addIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(MainActivity.this,AddCarActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        //最下面关闭
        closeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                dialog.dismiss();
            }
        });


        //创建一个构造器对象builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //通过builder的setView方法,将自定义的视图对象设置进去
        //通过builder的..show方法将 视图显示出来
        dialog = builder.setView(view).show();

    }


    //弹出dialog
    @Override
    public void onItemClick (int position) {

        Toast.makeText(this, "110", Toast.LENGTH_SHORT).show();
        popupWindow.dismiss();
        showDialog();
        isShowing = false ;
    }

    //改变主页面的数据内容
    @Override
    public void onImageViewClick (int position) {
        Toast.makeText(this, "120", Toast.LENGTH_SHORT).show();
        popupWindow.dismiss();
        isShowing = false ;
    }

    //dialog里RV的点击事件
    @Override
    public void onItemClickListener (int position) {
        Intent intent = new Intent(MainActivity.this , QueryCarActivity.class);
        intent.putExtra(QueryCarActivity.MODEL , listBean.get(position).getModel());
        intent.putExtra(QueryCarActivity.ID , listBean.get(position).getId());
        startActivity(intent);
        dialog.dismiss();

    }
    //dialog里RV的删除
    @Override
    public void onItemDeleteListener (int position) {

        int dialogId = listBean.get(position).getId();

        useADB.deleteCars(dialogId);
        Toast.makeText(this, "数据已删除", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}

