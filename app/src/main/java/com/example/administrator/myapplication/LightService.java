package com.example.administrator.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.IBinder;
import android.util.MonthDisplayHelper;

/**
 * Created by Administrator on 2018/8/8.
 */


public class LightService extends Service {
    private Camera camera = null ;
    private Parameters pm = null;

    @Override
    public IBinder onBind(Intent intent) {
        pm = camera.getParameters();
        pm.setFlashMode(Parameters.FLASH_MODE_TORCH);
        camera.setParameters(pm);
        return this.onBind(intent);
    }

    @Override
    public void onCreate() {
        camera = Camera.open();
        camera.startPreview();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        pm = camera.getParameters();
        pm.setFlashMode(Parameters.FLASH_MODE_TORCH);
        camera.setParameters(pm);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        pm = camera.getParameters();
        pm.setFlashMode(Parameters.FLASH_MODE_OFF);
        camera.setParameters(pm);
        camera.release();
        camera = null;
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        pm = camera.getParameters();
        pm.setFlashMode(Parameters.FLASH_MODE_OFF);
        camera.setParameters(pm);
        camera.release();
        camera = null;
        super.onDestroy();
    }
}