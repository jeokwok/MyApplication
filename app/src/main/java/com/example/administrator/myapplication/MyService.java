package com.example.administrator.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    //private WindowManager windowManager ;
    //private WindowManager.LayoutParams layoutParams ;


    /*线程中创建或移除悬浮窗工具*/
    //private Handler handler = new Handler();


    /*定时器*/
    //private Timer timer;

    public MyService() {
    }

    @Override
    public void onCreate() {
        System.out.println("On Create");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

   // @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*提示弹窗 提示用户成功启动service2018.7.30*/
        Context context = getApplicationContext();
        CharSequence text = "qi dong fu wu";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


       // MyView myView = new MyView(this);
       // windowManager.addView(myView,layoutParams);


        /*显示一个smallwindow*/
            //SmallView smallWindow = new SmallView(this);
           // windowManager.addView(smallWindow, layoutParams);//添加窗口


        /*if(timer == null)
        {
            timer  = new Timer();
            timer.scheduleAtFixedRate(TimerTask,0,500);
        }*/
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("On Destroy");
        super.onDestroy();
    }
    /*@Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer = null;
    }*/
    /*刷新页面类*/
}
