package com.lanou3g.xiaoxiongyouhao.bean;

/**
 * Created by dllo on 17/4/14.
 */

public class MoneyBean {

    private String time ;
    private float money ;

    public float getMoney () {
        return money;
    }

    public MoneyBean setMoney (float money) {
        this.money = money;
        return this;
    }

    public String getTime () {
        return time;
    }

    public MoneyBean setTime (String time) {
        this.time = time;
        return this;
    }
}
