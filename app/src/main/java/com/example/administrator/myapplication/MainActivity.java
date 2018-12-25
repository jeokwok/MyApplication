package com.example.administrator.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.Drawable;


import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.IBinder;
import android.provider.CallLog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.widget.ToggleButton;
import android.widget.VideoView;

import static android.R.attr.phoneNumber;


public class MainActivity extends AppCompatActivity  {

    private Button mMoreButton;
    private Button mBackButton;
    private ImageButton mSosButton;
    private CameraManager manager;
    private ToggleButton mToggleButton;
    /*用于标示闪光灯目前的状态 true为ON false为OFF*/
    private boolean isopen = false;

    private char isOpenNum =  1;
    private static Camera camera = null;

    /*按钮创建音效*/
   // MediaPlayer backgroundmusic = MediaPlayer.create(MainActivity.this,R.raw.gun);
    //private SoundPool soundPool;//声明一个SoundPool
    //private int soundID;//创建某个声音对应的音频ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*实现标题栏的隐藏*/
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*设置动态的layout背景图片*/
        // LinearLayout temp = (LinearLayout)findViewById(R.layout.flashlight);
        // Drawable d = Drawable.createFromPath();
        // temp.setBackgroundDrawable(d);

        setContentView(R.layout.flashlight);
        //FlashLightView flashLightView =new FlashLightView(this);
        //setContentView(flashLightView);
        final View parentView = findViewById(R.id.parentView);

        initData();
        onBackClickEvent();
        onsosClickEvent();
        onMoreClickEvent();
        //onClickEvent();
        //closeOnClick();

        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mToggleButton.setChecked(isChecked);

                if(isChecked == true )
                {
                    parentView.setBackgroundResource(R.drawable.p00);

                    try {
                        manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        manager.setTorchMode("0", true);// "0"是主闪光灯
                        //manager.setTorchMode("0",false);
                        isopen = true;
                    } catch (Exception e) {
                    }
                }
                else{
                    try {
                        manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        manager.setTorchMode("0", false);// "0"是主闪光灯
                        isopen = false;
                    } catch (Exception e) {
                    }
                    isOpenNum++;
                    parentView.setBackgroundResource(R.drawable.p01);
                }
            }
        });


        /*根据点击的开关次数实现背景图片的切换*/
        /*if(isOpenNum % 2 == 0)
        {
            parentView.setBackgroundResource(R.drawable.p02);
        }else if(isOpenNum %2 == 1)
        {
            parentView.setBackgroundResource(R.drawable.p03);
        }else {
            parentView.setBackgroundResource(R.drawable.p04);
            isOpenNum = 1;
        }*/
    }



        /*TextView view  = new TextView(this);
        view.setText("NI HAO  MEI NV ");
        view.setTextColor(Color.BLACK);
        view.setBackgroundColor(Color.GREEN);

        TextView view1 =  new TextView(this);
        view1.setText("WO ");
        view1.setTextColor(Color.RED);
        view1.setBackgroundColor(Color.BLUE);

        Button  button = new  Button(this);
        button.setText("open the hotbox tool");
        button.setHeight(30);
        button.setWidth(50);



        MyView myView = new MyView(this);

        LinearLayout layout = new LinearLayout(this);
        //layout.setOrientation();
        layout.addView(view);
        layout.addView(view1);
        layout.addView(myView);

        setContentView(layout);
        //setContentView(R.layout.activity_main);*/


    private void onMoreClickEvent() {
        mMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final String [] menu = {"注册","登陆","更多","切换背景","返回"};
                builder.setItems(menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (menu[which])
                        {
                            case "注册":
                                displayEorro();
                                break;
                            case "登陆":
                                displayEorro();
                                break;
                            case "更多":
                                displayEorro();
                                break;
                            case"切换背景":
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
    }

    /*更多菜单显示提示*/
    private void displayEorro() {
        Context context = getApplicationContext();
        CharSequence text = "此功能暂未开放！";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /*实现sos 下拉列表弹出框*/
    private void onsosClickEvent() {
        mSosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*数据对应的拨号码数组*/
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.a101_meitu_4);
                builder.setTitle("紧急呼叫");
               final String[] calls = {"匪警110","急救120","火警119","自定义紧急联系人"};
                builder.setItems(calls, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*跳转到手机拨号*/
                        /*Intent intent1=new Intent();
                        intent1.setAction(Intent.ACTION_DIAL);
                        intent1.setData(Uri.parse(temp[0]));*/
                       // intent1.setAction(Intent.ACTION_PACKAGE_FIRST_LAUNCH);
                        /*实现把用户点击的数据号码输入到电话拨号上*/
                        /*if(calls[which] == calls[3] )
                        {
                            Context context = getApplicationContext();
                            CharSequence text = "此功能暂未开放！";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }else {*/
                           switch (calls[which])
                            {
                                case "匪警110"://calls[which] = temp[0];
                                    //跳转到拨号界面，同时传递电话号码
                                    //Intent Intent1 =  new Intent(Intent.ACTION_DIAL,Uri.parse("110" ));
                                    //startActivity(Intent1);
                                    String temp = "110";
                                    Intent intent1=new Intent(Intent.ACTION_DIAL , Uri.parse("tel:"+temp));
                                    //intent1.setAction(Intent.ACTION_DIAL , Uri.parse("tel://"+temp ));
                                    /*跳转到拨号界面OK 无法传输号码到拨号盘*/
                                    //intent1.setData(Uri.parse(temp[0]));
                                    startActivity(intent1);
                                    break;
                                case "急救120"://calls[which] = temp[1];
                                    String temp1 = "120";
                                    Intent intent2=new Intent(Intent.ACTION_DIAL , Uri.parse("tel:"+temp1));
                                    //intent2.setAction(Intent.ACTION_DIAL);
                                    startActivity(intent2);
                                    break;
                                case "火警119"://calls[which] = temp[2];
                                    String temp2 = "119";
                                    Intent intent3=new Intent(Intent.ACTION_DIAL , Uri.parse("tel:"+temp2));
                                    //intent3.setAction(Intent.ACTION_DIAL);
                                    startActivity(intent3);
                                    break;
                                case "自定义紧急联系人":
                                   /* Context context = getApplicationContext();
                                    CharSequence text = "此功能暂未开放！";
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();*/
                                    /*弹出一个新建自定义紧急联系人activity */
                                    Intent contactIntent = new Intent(MainActivity.this,AddContactActivity.class);
                                    startActivity(contactIntent);


                                    //AddContactActivity addContactActivity = new AddContactActivity();
                                    //addContactActivity.getContactPhoneNum();
                                    //String temp3 = addContactActivity.getContactPhoneNum();
                                    //Intent intent4 = new Intent(Intent.ACTION_DIAL , Uri.parse("tel:"+temp3));
                                    /*输入一个联系人号码 、名字 保存和退出2个按钮*/
                                    /*当用户点击保存按钮后 数据保存在原紧急联系人的底行 “自定义紧急联系人”下移*/

                                    /*保存的数据储存在数据了 保存在本机的文件里*/
                                    //String [] sosContact = {};

                                   // contactPhoneNum[0];

                                default:
                                    break;
                            }
                           // intent1.setData(Uri.parse(calls[which]));
                        //}
                       // startActivity(intent1);
                        //Toast.makeText(this,"您即将拨打"+calls[which],Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });
    }

    /*实现返回桌面点击事件*/
    private void onBackClickEvent() {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*按钮打开音效*/

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确认退出应用？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent home = new Intent(Intent.ACTION_MAIN);
                        home.addCategory(Intent.CATEGORY_HOME);
                        startActivity(home);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //null
                    }
                });
                builder.show();

            }
        });
    }

    /*实现闪关灯的开关 目前没好的解决方案*/
   /*private void onClickEvent() {
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                    if(isopen == false)
                   try {
                       manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                       manager.setTorchMode("0", true);// "0"是主闪光灯
                       isopen = true;
                   } catch (Exception e) {
                   }

           }
       });
   }
    private void closeOnClick()
    {
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isopen  == true )
                try {
                    manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                    manager.setTorchMode("0", false);// "0"是主闪光灯
                    isopen = false;
                } catch (Exception e) {
                }
            }
        });
    }*/
     /**/







                                      // backgroundmusic.start();
                                      //flashStatus = false;
                                      //mCameraManage = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                                      //flashlight();
    /*防恶刷功能
    开启后2S后起到关闭按钮Enable 启动按钮Disabled
    * 再2S后启动按钮Enable 关闭按钮Disabled
    * */

                                      /*打开手机自带拨号器2018.1.1*/
                                      //Intent intent=new Intent();
                                      // intent.setAction(Intent.ACTION_PACKAGE_FIRST_LAUNCH);
                                      //startActivity(intent);
    /*启动服务*/
                                      // Intent intent = new Intent(MainActivity.this,MyService.class);
                                      //startService(intent);
                                      //finish();

   /* private void display()
    {
        if(islight == true)
        {
            Context context = getApplicationContext();
            CharSequence text = "flashlight is ON";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }else{

        }
    }*/

    private void initData() {

        mMoreButton = (Button) findViewById(R.id.btn_more);
        mBackButton = (Button) findViewById(R.id.btn_more1);
        mSosButton = (ImageButton) findViewById(R.id.btn_sos);
        mToggleButton = (ToggleButton)findViewById(R.id.btn_toggle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*获取AddContactActivity中保存的联系人号码  通过listView实现*/
        //AddContactActivity addContactActivity = new AddContactActivity();
        //String temp3 = addContactActivity.getContactPhoneNum();
       // Toast.makeText(MainActivity.this,temp3,Toast.LENGTH_SHORT).show();
    }
    /*private void sound(){
        soundPool = new SoundPool.Builder().build();
        soundID = soundPool.load(this, R.raw.gun, 1);
    }
    private void playSound() {
        soundPool.play(
                soundID,
                0.1f,   //左耳道音量【0~1】
                0.5f,   //右耳道音量【0~1】
                0,     //播放优先级【0表示最低优先级】
                1,     //循环模式【0表示循环一次，-1表示一直循环，其他表示数字+1表示当前数字对应的循环次数】
                1     //播放速度【1是正常，范围从0~2】
        );
    }*/


}

            // @Override
    /*public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        button.setOnClickListener();
        if(isChecked)
        {
            manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            manager.setTorchMode("0", true);// "0"是主闪光灯
            isopen =  false;
        } catch (Exception e) {
        }
        else{
        manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        manager.setTorchMode("0",false);// "0"是主闪光灯
        isopen =  true;
        } catch (Exception e) {
        }


    }*/
    /*启动闪光灯*/
   /* private void open() {
        /*camera.startPreview();
        Camera.Parameters parameter = camera.getParameters();
        parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameter);

        try{
            Camera m_Camera = Camera.open();
            Camera.Parameters mParameters;
            mParameters = m_Camera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            m_Camera.setParameters(mParameters);
        } catch(Exception ex){

        }
    }*/
   /* public void Open() {
        //异常处理一定要加，否则Camera打开失败的话程序会崩溃
        try {

            camera = Camera.open();
        } catch (Exception e) {

            Toast.makeText(MainActivity.this, "Camera被占用，请先关闭", Toast.LENGTH_SHORT).show();
        }

        if(camera != null)
       {
            //打开闪光灯
            camera.startPreview();
            Camera.Parameters parameter = camera.getParameters();
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameter);
       }
    }
    public void Close()
    {
        if (camera != null)
        {
            //关闭闪光灯
            camera.getParameters().setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(camera.getParameters());
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }
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
    public void flashlight() {

        if (isFlashlightOn(false)) {
            Close();
            camera = null;
        } else {
            Open();
        }
    }
    public void open(){
        try {
            camera = null;
            camera = Camera.open();
            camera.startPreview();
            Camera.Parameters parameter = camera.getParameters();
            parameter.setFlashMode(parameter.FLASH_MODE_TORCH);
            camera.setParameters(parameter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void open7(){
        //Intent intent1 = new Intent(this, LightService.class);
        //stopService(intent1);

        /*启动service 来启动闪关灯*/
            //intent = new Intent(MainActivity.this, LightService.class);
            //startService(intent);

        /*提示启动服务*/
       /* Context context = getApplicationContext();
        CharSequence text = "qi dong fu wu";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/

               /* if (!isopen) {
                    MainActivity.this.startService(intent);
                    //light_bt.setBackgroundResource(R.drawable.shou_on);
                    isopen = true;
                }else {
                    MainActivity.this.stopService(intent);
                    //light_bt.setBackgroundResource(R.drawable.shou_off);
                    isopen = false;
                }*/


            //finish();



        /*  CameraManager 实现闪关灯方式
    }
        try {
            {
                CameraManager manager = (CameraManager)MyApplication.getContext()
                        .getSystemService(Context.CAMERA_SERVICE);
                String [] ids = manager.getCameraIdList();
                for(String id :ids )
                {
                    CameraCharacteristics c = manager.getCameraCharacteristics(id);
                    Boolean fiashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }*/