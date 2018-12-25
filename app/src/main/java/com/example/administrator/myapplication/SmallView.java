package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/7/31.
 */

public class SmallView extends View {

        private Paint mPaint;
        private Context mContext;
        private static final String mString = "Welcome to smallwindow";

        public SmallView(Context context) {
            super(context);

        }
        public SmallView(Context context,AttributeSet attr)
        {
            super(context,attr);

        }
        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);

            mPaint = new Paint();

            //设置画笔颜色
            mPaint.setColor(Color.RED);
            //设置填充
            mPaint.setStyle(Paint.Style.FILL);

            //画一个矩形,前俩个是矩形左上角坐标，后面俩个是右下角坐标
            canvas.drawRect(new Rect(10, 10, 100, 100), mPaint);

            mPaint.setColor(Color.BLUE);
            //绘制文字
            canvas.drawText("Welcome to the hotbox tools", 10, 110, mPaint);
        }
    }



