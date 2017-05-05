package com.lanou3g.xiaoxiongyouhao.bean;

/**
 * Created by dllo on 17/4/12.
 */

public class CarsBean {

    private String name ;
    private int selected ;
    private int model ;
    private int uuid ;

    @Override
    public String toString () {
        return "CArsBean = { " + name + "," + selected + "," + model + "," + uuid + "}" + '\n';
    }

    private int id ;

    public int getId() {
        return id;
    }

    public CarsBean setId(int id) {
        this.id = id;
        return this;
    }

    public int getUuid() {
        return uuid;
    }

    public CarsBean setUuid(int uuid) {
        this.uuid = uuid;
        return this;
    }

    public int getModel() {
        return model;
    }

    public CarsBean setModel(int model) {
        this.model = model;
        return this;
    }

    public String getName() {
        return name;
    }

    public CarsBean setName(String name) {
        this.name = name;
        return this;
    }

    public int getSelected() {
        return selected;
    }

    public CarsBean setSelected(int selected) {
        this.selected = selected;
        return this;
    }
}
