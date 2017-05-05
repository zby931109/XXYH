package com.lanou3g.xiaoxiongyouhao.tools;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 17/3/14.
 */

public class NetWorkInstance {

    private OkHttpClient okHttpClient;

    private static NetWorkInstance netWorkInstance;

    //1.控制好 "new 构造方法"
    private NetWorkInstance () {
        super();
        okHttpClient = new OkHttpClient();
    }

    //2.提供一个让别人访问的方法
    public static NetWorkInstance getInstance () {

        if (null == netWorkInstance) {
            //锁
            synchronized (NetWorkInstance.class) {

                //判断:如果没有对象,则创建
                //      如果有对象,则直接返回已有对象
                if (null == netWorkInstance) {
                    netWorkInstance = new NetWorkInstance();
                }
            }
        }

        return netWorkInstance;

    }

    private NetResultInterface netResultInterface;



    //自定义
    //网络请求的功能方法
    //第二个参数,填一个接口对象
    public void netRequest (final String urlStr, final NetResultInterface netResultInterface) {
        this.netResultInterface = netResultInterface;
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage (Message msg) {

                switch (msg.what) {
                    case 101:
                        netResultInterface.faile("fail");
                        break;
                    case 102:
                        if (netResultInterface == null) {
                            Log.d("NetWorkInstance", "is null");
                        }else
                            netResultInterface.success((String) msg.obj);
                        break;

                }

                return false;
            }
        });
        //开启分支线程,进行网络请求

        Request request = new Request.Builder()
                .url(urlStr)
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            //请求失败了的方法
            @Override
            public void onFailure (Call call, IOException e) {
                handler.sendEmptyMessage(101);
            }

            //请求有应答
            @Override
            public void onResponse (Call call, Response response) throws IOException {

                Message msg = Message.obtain();

                if (response.code() == 200) {
                    msg.what = 102;
                    //传递应答信息
                    //转成String的形式
                    msg.obj = response.body().string();
                    handler.sendMessage(msg);

                }else{
                    handler.sendEmptyMessage(101);
                }


            }
        });


    }


}
