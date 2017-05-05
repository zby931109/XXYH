package com.lanou3g.xiaoxiongyouhao.activity;

import android.os.Bundle;
import android.print.PageRange;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lanou3g.xiaoxiongyouhao.R;

/**
 * Created by dllo on 17/5/2.
 */

public class AddRecordsActivity extends AppCompatActivity{
    private EditText dateEt , timeEt ,  liChengEt ,danJiaEt , youLiangEt ,beiZhuEt ;
    private Spinner numberSp , addressSp ;
    private CheckBox fullCB, onLightCB, hasRecordCB ;
    private ImageView saveIv ;


    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_records);
    }
}
