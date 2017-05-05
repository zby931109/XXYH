package com.lanou3g.xiaoxiongyouhao.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dllo on 17/4/21.
 */

public class FirstView extends View{

    private Paint paint1 ;
    private static final int HEIGHT = 10 ;
    private static final int WIDTH = 10 ;


    public FirstView (Context context) {
        this(context, null);
    }

    public FirstView (Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FirstView (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init () {
        paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.GRAY);
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);


    }
}
