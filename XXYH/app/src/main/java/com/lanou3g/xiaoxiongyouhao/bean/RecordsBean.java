package com.lanou3g.xiaoxiongyouhao.bean;

/**
 * Created by dllo on 17/4/12.
 */

public class RecordsBean {

    private String date;
    private int odometer ;
    private String price;
    private String yuan ;
    private int type ;
    private int gassup ;
    private String remark ;
    private int carId;
    private int forget ;
    private int lightOn ;
    private int stationld ;

    private int id ;

    public int getId () {
        return id;
    }

    public RecordsBean setId (int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString () {
        return "RecordsBean = { " + date + "," + odometer + "," + price + "," + yuan + "}" + '\n';
    }

    public int getStationld () {
        return stationld;
    }

    public RecordsBean setStationld (int stationld) {
        this.stationld = stationld;
        return this;
    }

    public int getStational () {
        return stational;
    }

    public RecordsBean setStational (int stational) {
        this.stational = stational;
        return this;
    }

    public int getCarId () {
        return carId;
    }

    public RecordsBean setCarId (int carId) {
        this.carId = carId;
        return this;
    }

    public String getDate () {
        return date;
    }

    public RecordsBean setDate (String data) {
        this.date = data;
        return this;
    }

    public int getForget () {
        return forget;
    }

    public RecordsBean setForget (int forget) {
        this.forget = forget;
        return this;
    }

    public int getGassup () {
        return gassup;
    }

    public RecordsBean setGassup (int gassup) {
        this.gassup = gassup;
        return this;
    }

    public int getLightOn () {
        return lightOn;
    }

    public RecordsBean setLightOn (int lightOn) {
        this.lightOn = lightOn;
        return this;
    }

    public int getOdometer () {
        return odometer;
    }

    public RecordsBean setOdometer (int odometer) {
        this.odometer = odometer;
        return this;
    }

    public String getPrice () {
        return price;
    }

    public RecordsBean setPrice (String price) {
        this.price = price;
        return this;
    }

    public String getRemark () {
        return remark;
    }

    public RecordsBean setRemark (String remark) {
        this.remark = remark;
        return this;
    }

    public int getType () {
        return type;
    }

    public RecordsBean setType (int type) {
        this.type = type;
        return this;
    }

    public String getYuan () {
        return yuan;
    }

    public RecordsBean setYuan (String yuan) {
        this.yuan = yuan;
        return this;
    }

    private int stational ;


}
