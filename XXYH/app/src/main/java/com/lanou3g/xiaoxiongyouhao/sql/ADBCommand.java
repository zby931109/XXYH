package com.lanou3g.xiaoxiongyouhao.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lanou3g.xiaoxiongyouhao.bean.CarsBean;
import com.lanou3g.xiaoxiongyouhao.bean.MoneyBean;
import com.lanou3g.xiaoxiongyouhao.bean.RecordsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/4/12.
 */

public class ADBCommand {

    private SQLiteDatabase database ;
    private final String TABLE_CARS = "cars" ;
    private final String TABLE_RECORDS = "records" ;
    private Context context;

    public ADBCommand (Context context) {
        this.context = context;
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//    }




    public void openDb(){
        //建立数据库
        database = context.openOrCreateDatabase("CarData" , Context.MODE_PRIVATE , null);
        database.execSQL("create table if not exists "+ TABLE_CARS +" (id integer primary key autoincrement,name text,selected integer,model integer,uuid integer)");
        database.execSQL("create table if not exists "+ TABLE_RECORDS +" (id integer primary key autoincrement,date integer,price integer,odometer integer,yuan text,type integer,gassup integer,remark text,carId integer,forget integer,lightOn integer,stationld integer)");

//        database.rawQuery("",null);
//        database.execSQL("");
//        database.delete();
//        database.update();
    }


    //增加
    public void insertCars (CarsBean carsBean){
        String name = carsBean.getName();
        int selected = carsBean.getSelected();
        int model = carsBean.getModel();
        int uuid = carsBean.getUuid();
        Log.d("ADBCommand", "database:" + database);
        database.execSQL("insert into "+ TABLE_CARS +" (name, selected, model, uuid) values ('" + name + "' , '"+ selected +"' , '" + model + "' , '" + uuid + "')");
    }

    //查询所有
    public List<CarsBean> queryCarsAll(){

        List<CarsBean> list = new ArrayList<>(5);

        Cursor cursor =database.rawQuery("select *from "+ TABLE_CARS +" ",null);
        if (cursor != null && cursor.moveToFirst()){

            int name = cursor.getColumnIndex("name");
            int model = cursor.getColumnIndex("model");
            int selected = cursor.getColumnIndex("selected");
            int uuid = cursor.getColumnIndex("uuid");
            int id = cursor.getColumnIndex("id");

            do{
                CarsBean carsBean1 = new CarsBean();

                carsBean1.setName(cursor.getString(name));
                carsBean1.setModel(cursor.getInt(model));
                carsBean1.setSelected(cursor.getInt(selected));
                carsBean1.setUuid(cursor.getInt(uuid));
                carsBean1.setId(cursor.getInt(id));
                list.add(carsBean1);
            }while (cursor.moveToNext());
        }

        cursor.close();
        return list ;
    }

    //查询 BY ID
    public CarsBean queryCarsById(int id){
        Cursor cursor =database.rawQuery("select *from "+ TABLE_CARS +" where id = "+ id +" ",null);
        CarsBean carsBean = new CarsBean();
        if (cursor != null && cursor.moveToFirst()){
            carsBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            carsBean.setModel(cursor.getInt(cursor.getColumnIndex("model")));
            carsBean.setSelected(cursor.getInt(cursor.getColumnIndex("selected")));
            carsBean.setUuid(cursor.getInt(cursor.getColumnIndex("uuid")));
        }
        return carsBean ;
    }

    //删除
    public void deleteCars (int id){
        database.execSQL("delete from "+TABLE_CARS+" where id = '"+id+"' ");
    }


    //更改
    public void upDataCars(CarsBean carsBean){
        database.execSQL("update "+TABLE_CARS+" set name = '"+carsBean.getName()+"' , model = "+carsBean.getModel()+" where id = "+carsBean.getId()+" ");
//        database.execSQL("update "+TABLE_CARS+" set model = '"+carsBean.getModel()+"' where _id = "+carsBean.getId());
//        database.execSQL("update "+TABLE_CARS+" set selected = '"+carsBean.getSelected()+"' where _id = "+carsBean.getId());
//        database.execSQL("update "+TABLE_CARS+" set uuid = '"+carsBean.getUuid()+"' where _id = "+carsBean.getId());
    }

    //更改Selected
    public void upDataCarsSelected(int id){
//        database.execSQL("update "+TABLE_CARS+" set name = '"+carsBean.getName()+"' where _id = "+carsBean.getId());
//        database.execSQL("update "+TABLE_CARS+" set selected = '"+selected+"' where _id = "+id+"");
//        database.execSQL("update "+TABLE_CARS+" set model = '"+carsBean.getModel()+"' where _id = "+carsBean.getId());
//        database.execSQL("update "+TABLE_CARS+" set uuid = '"+carsBean.getUuid()+"' where _id = "+carsBean.getId());

        database.execSQL("update "+TABLE_CARS+" set selected = 0 where selected = 1 ");
        database.execSQL("update "+TABLE_CARS+" set selected = 1 where id = "+id+"");
    }



    //增加 二表 一条数据
    public void insertRecords (RecordsBean recordsBean){
        String date = recordsBean.getDate();
        int odometer = recordsBean.getOdometer();
        String price = recordsBean.getPrice();
        String yuan = recordsBean.getYuan();
        int type = recordsBean.getType();
        int gassup = recordsBean.getGassup();
        String remark = recordsBean.getRemark();
        int carId = recordsBean.getCarId();
        int forget = recordsBean.getForget();
        int lightOn = recordsBean.getLightOn();
        int stationld = recordsBean.getStationld();
        database.execSQL("insert into "+ TABLE_RECORDS
                +" (date, odometer, price, yuan, type, gassup, remark, carId, forget, lightOn, stationld) values ('"
                + date + "' , '"+ odometer +"' , '" + price + "' , '"
                + yuan + "' , '" + type + "' , '" + gassup + "' , '"
                + remark + "' , '" + carId + "' , '" + forget + "' , '"
                + lightOn + "' , '" + stationld + "')");
    }

    //删除选定 一条 数据
    public  void deleteRecords(int id){
        database.execSQL("delete from "+ TABLE_RECORDS +" where id = '"+ id +"' ");
    }




    //第二张表查所有
    public List<RecordsBean> queryRecordsAll(){

        List<RecordsBean> list = new ArrayList<>();

        Cursor cursor =database.rawQuery("select *from "+ TABLE_RECORDS +" ",null);
        if (cursor != null && cursor.moveToFirst()){

            int date = cursor.getColumnIndex("date");
            int odometer = cursor.getColumnIndex("odometer");
            int price = cursor.getColumnIndex("price");
            int yuan = cursor.getColumnIndex("yuan");
            int type = cursor.getColumnIndex("type");
            int gassup = cursor.getColumnIndex("gassup");
            int remark =  cursor.getColumnIndex("remark");
            int carId =  cursor.getColumnIndex("carId");
            int forget = cursor.getColumnIndex("forget");
            int lightOn = cursor.getColumnIndex("lightOn");
            int stationld =  cursor.getColumnIndex("stationld");

            do{
                RecordsBean recordsBean = new RecordsBean();

                recordsBean.setDate(cursor.getString(date));
                recordsBean.setOdometer(cursor.getInt(odometer));
                recordsBean.setPrice(cursor.getString(price));
                recordsBean.setYuan(cursor.getString(yuan));
                recordsBean.setType(cursor.getInt(type));
                recordsBean.setGassup(cursor.getInt(gassup));
                recordsBean.setRemark(cursor.getString(remark));
                recordsBean.setCarId(cursor.getInt(carId));
                recordsBean.setForget(cursor.getInt(forget));
                recordsBean.setLightOn(cursor.getInt(lightOn));
                recordsBean.setStationld(cursor.getInt(stationld));

                list.add(recordsBean);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list ;

    }

    //二表 查询 选中
    public List<RecordsBean> queryRecordsById (int id){
        Cursor cursor = database.rawQuery("select A.* from "
                +TABLE_RECORDS+" as A , "
                +TABLE_CARS+" as B where A.carId = B.'"+id+"' and B.selected = 1 ",null);

        List<RecordsBean> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()){

            int date = cursor.getColumnIndex("date");
            int odometer = cursor.getColumnIndex("odometer");
            int price = cursor.getColumnIndex("price");
            int yuan = cursor.getColumnIndex("yuan");
            int type = cursor.getColumnIndex("type");
            int gassup = cursor.getColumnIndex("gassup");
            int remark =  cursor.getColumnIndex("remark");
            int carId =  cursor.getColumnIndex("carId");
            int forget = cursor.getColumnIndex("forget");
            int lightOn = cursor.getColumnIndex("lightOn");
            int stationld =  cursor.getColumnIndex("stationld");

            do{
                RecordsBean recordsBean = new RecordsBean();

                recordsBean.setDate(cursor.getString(date));
                recordsBean.setOdometer(cursor.getInt(odometer));
                recordsBean.setPrice(cursor.getString(price));
                recordsBean.setYuan(cursor.getString(yuan));
                recordsBean.setType(cursor.getInt(type));
                recordsBean.setGassup(cursor.getInt(gassup));
                recordsBean.setRemark(cursor.getString(remark));
                recordsBean.setCarId(cursor.getInt(carId));
                recordsBean.setForget(cursor.getInt(forget));
                recordsBean.setLightOn(cursor.getInt(lightOn));
                recordsBean.setStationld(cursor.getInt(stationld));

                list.add(recordsBean);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list ;
    }




    //二表查选中 一年
    public List<RecordsBean> queryRecordsOneYear (int id){
        Cursor cursor = database.rawQuery("select A.date, A.price , A.yuan , A.carId , datetime(A.date / 1000, 'unixepoch') from "
                +TABLE_RECORDS+" as A , "+TABLE_CARS+" as B WHERE A.date > ( strftime ('%s', datetime('now', '-12 month')) * 1000 ) and A.carId = B.'"
                +id+"' and B.selected = 1 ",null);


        List<RecordsBean> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()){

            int date = cursor.getColumnIndex("date");
            int price = cursor.getColumnIndex("price");
            int yuan = cursor.getColumnIndex("yuan");
            int carId =  cursor.getColumnIndex("carId");

            do{
                RecordsBean recordsBean = new RecordsBean();

                recordsBean.setDate(cursor.getString(date));
                recordsBean.setPrice(cursor.getString(price));
                recordsBean.setYuan(cursor.getString(yuan));
                recordsBean.setCarId(cursor.getInt(carId));

                list.add(recordsBean);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list ;

    }




    //二表查选中 半年
    public List<RecordsBean> queryRecordsHalfYear (int id){
        Cursor cursor = database.rawQuery("select A.date, A.price , A.yuan , A.carId , datetime(A.date / 1000, 'unixepoch') from "
                +TABLE_RECORDS+" as A , "+TABLE_CARS+" as B WHERE A.date > ( strftime ('%s', datetime('now', '-6 month')) * 1000 ) and A.carId = B.'"
                +id+"' and B.selected = 1 ",null);


        List<RecordsBean> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()){

            int date = cursor.getColumnIndex("date");
            int price = cursor.getColumnIndex("price");
            int yuan = cursor.getColumnIndex("yuan");
            int carId =  cursor.getColumnIndex("carId");

            do{
                RecordsBean recordsBean = new RecordsBean();

                recordsBean.setDate(cursor.getString(date));
                recordsBean.setPrice(cursor.getString(price));
                recordsBean.setYuan(cursor.getString(yuan));
                recordsBean.setCarId(cursor.getInt(carId));

                list.add(recordsBean);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list ;

    }




    //二表查选中 3个月
    public List<RecordsBean> queryRecordsThreeMonth (int id){
        Cursor cursor = database.rawQuery("select A.date, A.price , A.yuan , A.carId , datetime(A.date / 1000, 'unixepoch') from "
                +TABLE_RECORDS+" as A , "+TABLE_CARS+" as B WHERE A.date > ( strftime ('%s', datetime('now', '-3 month')) * 1000 ) and A.carId = B.'"
                +id+"' and B.selected = 1 ",null);


        List<RecordsBean> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()){

            int date = cursor.getColumnIndex("date");
            int price = cursor.getColumnIndex("price");
            int yuan = cursor.getColumnIndex("yuan");
            int carId =  cursor.getColumnIndex("carId");

            do{
                RecordsBean recordsBean = new RecordsBean();

                recordsBean.setDate(cursor.getString(date));
                recordsBean.setPrice(cursor.getString(price));
                recordsBean.setYuan(cursor.getString(yuan));
                recordsBean.setCarId(cursor.getInt(carId));

                list.add(recordsBean);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list ;

    }


    //查询当前选中车辆的每年的加油费用
    public List<MoneyBean> queryRecordsEveryYear (){
        Cursor cursor = database.rawQuery("select strftime('%Y' , datetime(A.date / 1000, 'unixepoch'), 'localtime') date_t, sum(A.yuan) money from "
                +TABLE_RECORDS+" as A, "+TABLE_CARS+" where A.cardId = B.id and B.selected = 1 GROUP BY date_t ORDER BY date_t" , null);

        List<MoneyBean> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()){
            int time = cursor.getColumnIndex("date_t");
            int money = cursor.getColumnIndex("money");
            do {
                MoneyBean moneyBean = new MoneyBean();

                moneyBean.setTime(cursor.getString(time));
                moneyBean.setMoney(cursor.getFloat(money));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }


    //查询当前选中车辆的每月的加油费用
    public List<MoneyBean> queryRecordsEveryMonth (){
        Cursor cursor = database.rawQuery("select strftime('%Y-%m',datetime(A.date / 1000, 'unixepoch'),'localtime') date_t, sum(A.yuan) money from "
                +TABLE_RECORDS+" as A, "+TABLE_CARS+" as B where A.carId = B.id and B.selected = 1 GROUP BY date_t ORDER BY date_t" , null);
        List<MoneyBean> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()){
            int time = cursor.getColumnIndex("date_t");
            int money = cursor.getColumnIndex("money");
            do {
                MoneyBean moneyBean = new MoneyBean();

                moneyBean.setTime(cursor.getString(time));
                moneyBean.setMoney(cursor.getFloat(money));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }



}