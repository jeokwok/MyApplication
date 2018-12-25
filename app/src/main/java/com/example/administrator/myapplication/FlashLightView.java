package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Rect;
import android.hardware.Camera;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.security.Policy;

/**
 * Created by Administrator on 2018/8/2.
 */

public class FlashLightView extends View {
    Rect rect;
    private Policy.Parameters parameters = null;
    private Camera camera  = null;
    private Bitmap bitmap;

    public FlashLightView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a101);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
        canvas.drawBitmap(bitmap, null,getRect(), paint);
        super.onDraw(canvas);
    }
   /* private int getScreenWidth()
    {
        WindowManager windowManager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return  displayMetrics.widthPixels;
    }

    /*设置自定义控件的宽高*/
    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getScreenWidth();
        int height= getScreenWidth()*2;
        super.onMeasure(width, height);
    }
    /*点击事件 启动闪光灯*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
           /* camera = Camera.open();
            camera.startPreview();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);*/
        isFlashlightOn(true);
        flashlightUtils();
        return super.onTouchEvent(event);
    }
    /*开启前先检查下闪光灯是否开启*/
    public boolean isFlashlightOn(boolean flag) {
        try {
            Camera.Parameters parameters = camera.getParameters();
            String flashMode = parameters.getFlashMode();
            if (flashMode.equals(android.hardware.Camera.Parameters.FLASH_MODE_TORCH)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    public void flashlightUtils() {

        if (isFlashlightOn(false)) {
            Closeshoudian();
            camera = null;
        } else {
            Openshoudian();
        }
    }
    /*开启方法*/
    public void Openshoudian() {
        //异常处理一定要加，否则Camera打开失败的话程序会崩溃
        try {
            Log.d("smile","camera打开");
            camera = Camera.open();
        } catch (Exception e) {
            Log.d("smile","Camera打开有问题");
            Context context1  =null;
            Toast.makeText(context1, "Camera被占用，请先关闭", Toast.LENGTH_SHORT).show();
        }

        if(camera != null)
        {
            //打开闪光灯
            camera.startPreview();
            Camera.Parameters parameter = camera.getParameters();
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameter);
            Log.d("smile","闪光灯打开");


        }
    }
    /*关闭方法*/
    public void Closeshoudian()
    {
        if (camera != null)
        {
            //关闭闪光灯
            Log.d("smile", "closeCamera()");
            camera.getParameters().setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(camera.getParameters());
            camera.stopPreview();
            camera.release();
            camera = null;


        }
    }

    public Rect getRect() {

        rect.left = getPaddingLeft();
        rect.right = getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom =getPaddingBottom();
        return rect;
    }
}
