package com.example.administrator.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/7/25.
 * 练习  7.27 绘制一个2个背景块 #1 #2   当点击#1是显示1的 颜色  点击#2时显示2的颜色
 */

public class MyView extends View {
    private  Bitmap bitmap;
    public MyView(Context context) {


        super(context);
      bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.a101);
    }
/*获取屏幕的宽度*/
  /* private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }*/
    /*重写touch方法 */

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.clipRect(50,50,300,500);
        //canvas.drawText(scae,50,50,paint);
        super.onDraw(canvas);
    }
}
