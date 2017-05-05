package com.lanou3g.xiaoxiongyouhao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lanou3g.xiaoxiongyouhao.R;
import com.lanou3g.xiaoxiongyouhao.adapter.SpinnerAddCarBrandAdapter;
import com.lanou3g.xiaoxiongyouhao.bean.CarBrandBean;
import com.lanou3g.xiaoxiongyouhao.bean.CarFullBean;
import com.lanou3g.xiaoxiongyouhao.bean.CarsBean;
import com.lanou3g.xiaoxiongyouhao.sql.UseADB;
import com.lanou3g.xiaoxiongyouhao.tools.NetResultInterface;
import com.lanou3g.xiaoxiongyouhao.tools.NetWorkInstance;

/**
 * Created by dllo on 17/4/28.
 */

public class QueryCarActivity extends AppCompatActivity{

    private EditText editText;
    private Spinner brandSp, seriesSp, modelSp;
    private ImageView saveIv;
    private SpinnerAddCarBrandAdapter brandAdapter;
    private SpinnerAddCarBrandAdapter seriesAdapter;
    private SpinnerAddCarBrandAdapter typeAdapter;

    private String url = "http://www.xiaoxiongyouhao.com/models/pinpai.json";
    private String id1;
    private String id2;

    private TextView textView1, textView2;
    private int modelUrl;
    private CarFullBean carFullBean ;
    private int pinPai;
    private int cheXi;

    private UseADB useADB;
    private String urll;
    private CarsBean carsBean;
    private int id;

    public static final String MODEL = "model" ;
    public static final String ID = "id" ;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        editText = (EditText) findViewById(R.id.add_car_et);
        brandSp = (Spinner) findViewById(R.id.add_car_car_brand_sp);
        seriesSp = (Spinner) findViewById(R.id.add_car_car_series_sp);
        modelSp = (Spinner) findViewById(R.id.add_car_car_model_sp);
        saveIv = (ImageView) findViewById(R.id.activity_add_car_save_iv);
        textView1 = (TextView) findViewById(R.id.activity_add_tv1);
        textView2 = (TextView) findViewById(R.id.activity_add_tv2);


        brandAdapter = new SpinnerAddCarBrandAdapter(QueryCarActivity.this);
        brandSp.setAdapter(brandAdapter);
        seriesAdapter = new SpinnerAddCarBrandAdapter(QueryCarActivity.this);
        seriesSp.setAdapter(seriesAdapter);
        typeAdapter = new SpinnerAddCarBrandAdapter(QueryCarActivity.this);
        modelSp.setAdapter(typeAdapter);


        Intent intent = getIntent();
        modelUrl = intent.getIntExtra(MODEL ,0);
        id = intent.getIntExtra(ID , 0);

        Log.d("QueryCarActivity", "123123123123id:" + id);

        spinnerGetCarData();

        useADB = new UseADB(QueryCarActivity.this);
        //用ID差车
        useADB.queryCarsById(id, new UseADB.QueryCarsById() {
            @Override
            public void onGetData (CarsBean carsBean) {
                editText.setText(carsBean.getName());
            }
        });
        saveIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                carsBean = new CarsBean();
                carsBean.setName(editText.getText().toString());
                carsBean.setModel(Integer.parseInt(urll));
                carsBean.setSelected(0);
                carsBean.setId(id);
                useADB.upDataCars(carsBean);
                Toast.makeText(QueryCarActivity.this, "更改完毕", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    public void spinnerGetCarData(){
        String carUrl = "http://www.xiaoxiongyouhao.com/models/spec.php?cheXing=" + modelUrl;

        getDataCarFullBean2(carUrl);

    }

    private void getDataCarFullBean2 (String url) {

        NetWorkInstance.getInstance().netRequest(url, new NetResultInterface() {
            //当网络请求成功了,自动调用此方法--

            @Override
            public void success (String str) {

                //gson解析
                Gson gson = new Gson();
//                carFullBean = new CarFullBean();
                //解析后的数据存于homeEntity中
                carFullBean = gson.fromJson(str, CarFullBean.class);
                if (!"".equals(carFullBean.getEngine())) {
                    textView1.setText(carFullBean.getEngine());
                }
                if (!"".equals(carFullBean.getGearbox())) {
                    textView2.setText(carFullBean.getGearbox());
                }

                pinPai = carFullBean.getPinpai();
                cheXi = carFullBean.getChexi();
                Log.d("AddCarActivity", "pinPai:" + pinPai);
                Log.d("AddCarActivity", "cheXi:" + cheXi);

                getDataOne(QueryCarActivity.this.url);

                String url2 = "http://www.xiaoxiongyouhao.com/models/" + pinPai + "/che_xi.json";
                getDataTwo(url2);
                String url3 = "http://www.xiaoxiongyouhao.com/models/" + pinPai + "/che_xing_"+ cheXi +".json";
                Log.d("AddCarActivity", "url3:  " + url3);
                getDataThree(url3);

            }
            //网络请求失败了,自动调用次方法

            @Override
            public void faile (String str) {
                Log.d("faile" + "", str);
            }
        });

    }

    private void getDataOne (String url) {

        NetWorkInstance.getInstance().netRequest(url, new NetResultInterface() {
            //当网络请求成功了,自动调用此方法
            @Override
            public void success (String str) {
//                Log.d("success", str);

                //gson解析
                Gson gson = new Gson();
                //解析后的数据存于homeEntity中
                CarBrandBean bean = gson.fromJson(str, CarBrandBean.class);
//                homeEntity.getData().getItems().get(1).getIntroduction();

                Log.d("AddCarActivity", "bean:" + bean);

                brandAdapter.setAddCarBean(bean);

                if (modelUrl != 0 && pinPai!=0){
                    Log.d("AddCarActivity", "pinPai:" + pinPai);
                    int i = bean.getIdxes().indexOf(pinPai);
                    brandSp.setSelection(i);
                }
            }

            //网络请求失败了,自动调用次方法
            @Override
            public void faile (String str) {
                Log.d("faile" + "", str);
            }
        });

    }

    private void getDataTwo (String url) {

        NetWorkInstance.getInstance().netRequest(url, new NetResultInterface() {
            //当网络请求成功了,自动调用此方法
            @Override
            public void success (String str) {

                //gson解析
                Gson gson = new Gson();
                //解析后的数据存于homeEntity中
                CarBrandBean bean = gson.fromJson(str, CarBrandBean.class);

                seriesAdapter.setAddCarBean(bean);

                if (modelUrl != 0){
                    int i = bean.getIdxes().indexOf(cheXi);
                    seriesSp.setSelection(i);
                }

            }
            //网络请求失败了,自动调用次方法

            @Override
            public void faile (String str) {
                Log.d("faile" + "", str);
            }
        });

    }


    private void getDataThree (String url) {

        NetWorkInstance.getInstance().netRequest(url, new NetResultInterface() {
            //当网络请求成功了,自动调用此方法
            @Override
            public void success (String str) {
//                Log.d("success", str);

                //gson解析
                Gson gson = new Gson();
                //解析后的数据存于homeEntity中
                Log.d("AddCarActivity", "str:  " + str);
                CarBrandBean bean = gson.fromJson(str, CarBrandBean.class);

                typeAdapter.setAddCarBean(bean);


                setSelectedListeners();
//                Log.d("AddCarActivity", "brandSp.getCount():" + brandSp.getCount());
            }
            //网络请求失败了,自动调用次方法

            @Override
            public void faile (String str) {
                Log.d("faile" + "", str);
            }
        });

    }

    private void setSelectedListeners () {
        brandSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
                Log.d("AddCarActivity", "id0:   " + id);
                id1 = String.valueOf(id);
                String url2 = "http://www.xiaoxiongyouhao.com/models/" + id + "/che_xi.json";
                getDataTwo(url2);
            }

            @Override
            public void onNothingSelected (AdapterView<?> parent) {

            }
        });

        seriesSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
                Log.d("AddCarActivity", "id1:   " + id);
                id2 = String.valueOf(id);
//                Log.d("AddCarActivity", "12315:  " + id2);
                String url3 = "http://www.xiaoxiongyouhao.com/models/" + id1 + "/che_xing_" + id2 + ".json";
//                Log.d("AddCarActivity", "12315:  " + url3);
                getDataThree(url3);
            }

            @Override
            public void onNothingSelected (AdapterView<?> parent) {

            }

        });

        modelSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {

                Log.d("AddCarActivity", "id2:" + id);
                urll = String.valueOf(id);
                String url = "http://www.xiaoxiongyouhao.com/models/spec.php?cheXing=" + urll + "";
                Log.d("AddCarActivity", "000000001:  " + url);
                getDataCarFullBean(url);

            }

            @Override
            public void onNothingSelected (AdapterView<?> parent) {

            }
        });
    }

    private void getDataCarFullBean (String url) {

        NetWorkInstance.getInstance().netRequest(url, new NetResultInterface() {
            //当网络请求成功了,自动调用此方法
            @Override
            public void success (String str) {

                //gson解析
                Gson gson = new Gson();
                //解析后的数据存于homeEntity中
                CarFullBean bean = gson.fromJson(str, CarFullBean.class);
                if (bean != null && bean.getEngine() !=null && !bean.getEngine().equals("")) {
                    textView1.setText(bean.getEngine());
                }
                if (bean != null && bean.getGearbox() != null && !bean.getGearbox().equals("")) {
                    textView2.setText(bean.getGearbox());
                }
            }
            //网络请求失败了,自动调用次方法

            @Override
            public void faile (String str) {
                Log.d("faile" + "", str);
            }
        });

    }

}
