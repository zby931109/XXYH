package com.lanou3g.xiaoxiongyouhao.table;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/4/24.
 */

class LinearChartView extends View {

    private float[] values = new float[5];
    private String[] names = new String[5];
    private List<ChartValue> valueList;
    private int mWidth;
    private int mHeight;

    public LinearChartView (Context context) {
        this(context, null);
    }

    public LinearChartView (Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearChartView (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private Paint mPaint , linePaint;
    // 这两个数据是随便写的, 实际上应该根据不同的屏幕确定不同的值
    private static final int PADDING_WIDTH = 50;
    private static final int PADDING_HEIGHT = 50;
    private static final int COUNT_X_MARK = 5;
    private static final int COUNT_Y_MARK = 15;

    private List<Point> mPoints;

    private void init () {
        values[0] = 12.5f;
        values[1] = 10.8f;
        values[2] = 9.2f;
        values[3] = 22.0f;
        values[4] = 18.5f;

        names[0] = "一月";
        names[1] = "二月";
        names[2] = "三月";
        names[3] = "四月";
        names[4] = "五月";

        mPoints = new ArrayList<>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.GRAY);
        linePaint.setStrokeWidth(2);
        linePaint.setAntiAlias(true);

    }

    public void setValueList (List<ChartValue> valueList) {
        this.valueList = valueList;
        invalidate();
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);

        drawAxisX(canvas);
        drawAxisY(canvas);
        drawMarkX(canvas);
        drawMarkY(canvas);
        drawLineX(canvas);
        drawLineY(canvas);
//        drawPoint(canvas);
//        drawLine(canvas);

    }

    private void drawLine (Canvas canvas) {
        Point lastPoint = null;
        for (int i = 0; i < mPoints.size(); i++) {
            Point currentP = mPoints.get(i);
            if (i != 0) {
                canvas.drawLine(lastPoint.x, lastPoint.y, currentP.x, currentP.y, mPaint);
            }
            lastPoint = currentP;
        }
    }

    private void drawPoint (Canvas canvas) {
        float length = (mWidth - PADDING_WIDTH * 2) / (COUNT_X_MARK + 1);
        float heightY = mHeight - PADDING_HEIGHT * 2;
        mPoints.clear();
        for (int i = 0; i < COUNT_X_MARK; i++) {
            float cx = PADDING_WIDTH + length * (i + 1);
            float cy = (30 - values[i]) * heightY / 30 + PADDING_HEIGHT;
            float radius = 5;
            canvas.drawCircle(cx, cy, radius, mPaint);
            mPoints.add(new Point(cx, cy));

        }
    }


    //画X轴点
    private void drawMarkX (Canvas canvas) {
        float length = (mWidth - PADDING_WIDTH * 2) / (20 + 1);

        for (int i = 0; i < 20; i++) {
            float startX = PADDING_WIDTH + length * (i + 1);
            float startY = mHeight - PADDING_HEIGHT - 10;
            float stopY = mHeight - PADDING_HEIGHT;
            canvas.drawLine(startX, startY, startX, stopY, mPaint);
            float x = startX - 15;
            float y = stopY + 5;
            mPaint.setTextSize(30);
            mPaint.setColor(0xffffffff);
//            canvas.drawText(names[i], x, y, mPaint);
        }
    }

    //画Y轴点
    private void drawMarkY (Canvas canvas) {

        float length = (mHeight - PADDING_HEIGHT * 2) / (15+1);

        for (int i = 0; i < 15; i++) {
            float startX =PADDING_WIDTH;
            float startY = mHeight - PADDING_HEIGHT - length * (i+1);
            float stopX =PADDING_WIDTH + 10 ;
            canvas.drawLine(startX, startY, stopX, startY, mPaint);
        }

    }

    //画X点线
    private void drawLineX (Canvas canvas){
        float length = (mWidth - PADDING_WIDTH * 2) / (20 + 1);

        for (int i = 0; i < 20; i++) {
            float startX = PADDING_WIDTH + length * (i + 1);
            float startY = mHeight - PADDING_HEIGHT ;
            float stopY = mHeight -(mHeight-PADDING_HEIGHT*2)-PADDING_HEIGHT;
            canvas.drawLine(startX, startY, startX, stopY, linePaint);
        }
    }

    //画Y点线
    private void drawLineY(Canvas canvas){
        float length = (mHeight - PADDING_HEIGHT * 2) / (15+1);

        for (int i = 0; i < 15; i++) {
            float startX =PADDING_WIDTH;
            float startY = mHeight - PADDING_HEIGHT - length * (i+1);
            float stopX =PADDING_WIDTH + (mWidth-PADDING_WIDTH*2);
            canvas.drawLine(startX, startY, stopX, startY, linePaint);
        }
    }

    private void drawAxisX (Canvas canvas) {
        float startX = PADDING_WIDTH;
        float startY = mHeight - PADDING_HEIGHT;
        float stopX = mWidth - PADDING_WIDTH;
        float stopY = startY;
        mPaint.setColor(0xffffffff);
        canvas.drawLine(startX, startY, stopX, stopY, mPaint);
    }

    private void drawAxisY (Canvas canvas) {
        float startY = PADDING_HEIGHT;
        float stopY = mHeight - PADDING_HEIGHT;
        float startX = PADDING_WIDTH;
        float stopX = startX;
        mPaint.setColor(0xffffffff);
        canvas.drawLine(startX, startY, stopX, stopY, mPaint);
    }

    private static class Point {
        public final float x;
        public final float y;

        public Point (float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class ChartValue{
        private String name;
        private float value;

        public String getName () {
            return name;
        }

        public void setName (String name) {
            this.name = name;
        }

        public float getValue () {
            return value;
        }

        public void setValue (float value) {
            this.value = value;
        }
    }
}
