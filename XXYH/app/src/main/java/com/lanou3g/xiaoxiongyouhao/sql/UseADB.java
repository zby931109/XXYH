package com.lanou3g.xiaoxiongyouhao.sql;

import android.content.Context;

import com.lanou3g.xiaoxiongyouhao.bean.CarsBean;
import com.lanou3g.xiaoxiongyouhao.bean.MoneyBean;
import com.lanou3g.xiaoxiongyouhao.bean.RecordsBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dllo on 17/4/14.
 */

public class UseADB {

    private CarsBean carsBean;
    private MoneyBean moneyBean;
    private RecordsBean recordsBean;
    private ADBCommand adbCommand;


    private Context context;

    public UseADB (Context context) {
        this.context = context;
    }


    //添加数据
    public void insertCars (CarsBean carsBean) {

        adbCommand = new ADBCommand(context);

        Observable.just(carsBean)
                .map(new Function<CarsBean, CarsBean>() {
                    @Override
                    public CarsBean apply (@NonNull CarsBean carsBean) throws Exception {
                        adbCommand.openDb();
                        adbCommand.insertCars(carsBean);
                        return new CarsBean();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }




//        queryCarsAll(new GetData() {
//            @Override
//            public void onGetData (List<CarsBean> list) {
//
//            }
//        });




    //查 车 所有
    public void queryCarsAll (final QueryCarsAll queryCarsAll) {
        adbCommand = new ADBCommand(context);
        Observable.just("")
                .map(new Function<Object, List<CarsBean>>() {
                    @Override
                    public List<CarsBean> apply (@NonNull Object o) throws Exception {
                        adbCommand.openDb();
                        List<CarsBean> list = adbCommand.queryCarsAll();
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CarsBean>>() {
                    @Override
                    public void accept (@NonNull List<CarsBean> carsBeen) throws Exception {
                        queryCarsAll.onGetData(carsBeen);
                    }
                });


    }
    public interface QueryCarsAll{
        void onGetData(List<CarsBean> list);
    }


    //查Car BY  ID
    public void queryCarsById( final int id , final QueryCarsById queryCarsById ){
        adbCommand = new ADBCommand(context);
        Observable.just("")
                .map(new Function<Object, CarsBean>() {
                    @Override
                    public CarsBean apply (@NonNull Object o) throws Exception {
                        adbCommand.openDb();
                        CarsBean carBean = adbCommand.queryCarsById(id);
                        return carBean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarsBean>() {
                    @Override
                    public void accept (@NonNull CarsBean carsBean) throws Exception {
                        queryCarsById.onGetData(carsBean);
                    }
                });
    }public interface QueryCarsById{
        void onGetData(CarsBean carsBean);
    }



    //删除Car指定id
    public void deleteCars (int id){
        adbCommand = new ADBCommand(context);

        Observable.just(id)
                .map(new Function<Integer, Object>() {
                    @Override
                    public Object apply (@NonNull Integer integer) throws Exception {
                        adbCommand.openDb();
                        adbCommand.deleteCars(integer);
                        return 0;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    //更改
    public void upDataCars(CarsBean carsBean){
        Observable.just(carsBean)
                .map(new Function<CarsBean, Object>() {
                    @Override
                    public Object apply (@NonNull CarsBean carsBean) throws Exception {
                        adbCommand = new ADBCommand(context);
                        adbCommand.openDb();
                        adbCommand.upDataCars(carsBean);
                        return 0;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    //更改Selected
    public void upDataCarsSelected(int id){
        Observable.just(id)
                .map(new Function<Integer, Object>() {
                    @Override
                    public Object apply (@NonNull Integer integer) throws Exception {
                        adbCommand = new ADBCommand(context);
                        adbCommand.openDb();
                        adbCommand.upDataCarsSelected(integer);
                        return 0;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    //增加 二表 一条数据
    public void insertRecords (RecordsBean recordsBean){
        Observable.just(recordsBean)
                .map(new Function<RecordsBean, Object>() {
                    @Override
                    public Object apply (@NonNull RecordsBean recordsBean) throws Exception {
                        adbCommand = new ADBCommand(context);
                        adbCommand.openDb();
                        adbCommand.insertRecords(recordsBean);
                        return 0;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    //删除选定 一条 数据
    public  void deleteRecords(int id){
        Observable.just(id)
                .map(new Function<Integer, Object>() {
                    @Override
                    public Object apply (@NonNull Integer integer) throws Exception {
                        adbCommand = new ADBCommand(context);
                        adbCommand.openDb();
                        adbCommand.deleteRecords(integer);
                        return 0;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    //第二张表查所有
    public void queryRecordsAll(final QueryRecordsAll queryRecordsAll){
        adbCommand = new ADBCommand(context);

        Observable.just("")
                .map(new Function<String, List<RecordsBean>>() {
                    @Override
                    public List<RecordsBean> apply (@NonNull String s) throws Exception {
                        adbCommand.openDb();
                        List<RecordsBean> list = adbCommand.queryRecordsAll();
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RecordsBean>>() {
                    @Override
                    public void accept (@NonNull List<RecordsBean> recordsBeen) throws Exception {
                        queryRecordsAll.onGetData(recordsBeen);
                    }
                });
    }
    public interface QueryRecordsAll{
        void onGetData(List<RecordsBean> list);
    }

    //二表 查询 选中
    public void queryRecordsById(final QueryRecordsById queryRecordsById , int id){
        adbCommand = new ADBCommand(context);

        Observable.just(id)
                .map(new Function<Integer, List<RecordsBean>>() {
                    @Override
                    public List<RecordsBean> apply (@NonNull Integer integer) throws Exception {
                        adbCommand.openDb();
                        List<RecordsBean> list = adbCommand.queryRecordsById(integer);
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RecordsBean>>() {
                    @Override
                    public void accept (@NonNull List<RecordsBean> recordsBeen) throws Exception {
                        queryRecordsById.onGetData(recordsBeen);
                    }
                });
    }
    public interface QueryRecordsById{
        void onGetData(List<RecordsBean> list);
    }

    //二表查选中 一年
    public void queryRecordsOneYear(final QueryRecordsOneYear queryRecordsOneYear , int id){
        adbCommand = new ADBCommand(context);

        Observable.just(id)
                .map(new Function<Integer, List<RecordsBean>>() {
                    @Override
                    public List<RecordsBean> apply (@NonNull Integer integer) throws Exception {
                        adbCommand.openDb();
                        List<RecordsBean> list = adbCommand.queryRecordsOneYear(integer);
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RecordsBean>>() {
                    @Override
                    public void accept (@NonNull List<RecordsBean> recordsBeen) throws Exception {
                        queryRecordsOneYear.onGetData(recordsBeen);
                    }
                });
    }
    public interface QueryRecordsOneYear{
        void onGetData(List<RecordsBean> list);
    }

    //二表查选中 半年
    public void queryRecordsHalfYear(final QueryRecordsHalfYear queryRecordsHalfYear , int id){
        adbCommand = new ADBCommand(context);

        Observable.just(id)
                .map(new Function<Integer, List<RecordsBean>>() {
                    @Override
                    public List<RecordsBean> apply (@NonNull Integer integer) throws Exception {
                        adbCommand.openDb();
                        List<RecordsBean> list = adbCommand.queryRecordsHalfYear(integer);
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RecordsBean>>() {
                    @Override
                    public void accept (@NonNull List<RecordsBean> recordsBeen) throws Exception {
                        queryRecordsHalfYear.onGetData(recordsBeen);
                    }
                });
    }
    public interface QueryRecordsHalfYear{
        void onGetData(List<RecordsBean> list);
    }


    //二表查选中 三个月
    public void queryRecordsThreeMonth(final QueryRecordsThreeMonth queryRecordsThreeMonth , int id){
        adbCommand = new ADBCommand(context);

        Observable.just(id)
                .map(new Function<Integer, List<RecordsBean>>() {
                    @Override
                    public List<RecordsBean> apply (@NonNull Integer integer) throws Exception {
                        adbCommand.openDb();
                        List<RecordsBean> list = adbCommand.queryRecordsThreeMonth(integer);
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RecordsBean>>() {
                    @Override
                    public void accept (@NonNull List<RecordsBean> recordsBeen) throws Exception {
                        queryRecordsThreeMonth.onGetData(recordsBeen);
                    }
                });
    }
    public interface QueryRecordsThreeMonth{
        void onGetData(List<RecordsBean> list);
    }


    public void queryRecordsHalfYear(final QueryRecordsEveryYear queryRecordsEveryYear){
        adbCommand = new ADBCommand(context);

        Observable.just("")
                .map(new Function<String, List<MoneyBean>>() {
                    @Override
                    public List<MoneyBean> apply (@NonNull String s) throws Exception {
                        adbCommand.openDb();
                        List<MoneyBean> list = adbCommand.queryRecordsEveryYear();
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MoneyBean>>() {
                    @Override
                    public void accept (@NonNull List<MoneyBean> moneyBeen) throws Exception {
                        queryRecordsEveryYear.onGetData(moneyBeen);
                    }
                });
    }
    public interface QueryRecordsEveryYear{
        void onGetData(List<MoneyBean> list);
    }




}
