package com.tool.tetsu2kasen.util_02;

//import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;


import android.view.WindowManager;


//import static android.app.Service.START_STICKY;


//PAButtonConvert
public class MainActivity extends AppCompatActivity {


    int start;
    boolean muted;
    int ceck;
    int now;

    int mTime;

    int taped = 0;

    Timer mTimer;



    Handler mHandler;






    boolean exed=false;

    boolean runnin = false;

    AudioManager am =(AudioManager)getSystemService(Context.AUDIO_SERVICE);
    int ringVolume = am.getStreamVolume(AudioManager.STREAM_RING);
    int systemVolume = am.getStreamVolume(AudioManager.STREAM_SYSTEM);
    int alarmVolume = am.getStreamVolume(AudioManager.STREAM_ALARM);
    int musicVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
    int noftVolume = am.getStreamVolume(AudioManager.STREAM_NOTIFICATION);

    int mv =musicVolume;

    private static final String TAG = MainActivity.class.getSimpleName();

    TextView text2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        int recentOperator = R.id.btnStart;
        super.onCreate(savedInstanceState);




        textView = (TextView) findViewById(R.id.textView);
        text2 = (TextView) findViewById(R.id.textView2);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
        /*findViewById(R.id.btnStart).setOnClickListener(ServiceStrListener);*/
        findViewById(R.id.btnStop).setOnClickListener(ServiceStpListener);
        mHandler = new Handler();

    }

    public class MyTaskThread extends Thread
    {
        private CountDownLatch latch;

        public MyTaskThread(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run()
        {
            //処理
            startService(new Intent(MainActivity.this, PAButtonConvert.class));

            //MyTaskThreadの処理が完了したことを知らせる
            latch.countDown();
        }
    }

    public void srt(View v)
    {
        mTime=0;


        mTimer = new Timer(false);
        mTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                now= mTime;
                if(start>=0)
                {
                    ceck=now-start;
                    if(ceck>=1&&ceck<=2)
                    {
                        if(taped==1)
                        {
                            if(muted==false)
                            {
                                mv=mv+10;
                            }
                            else
                            {
                                muted =false;
                            }
                            am.setStreamVolume(AudioManager.STREAM_MUSIC, mv, 0);
                            Log.i("Once Pressed",String.valueOf(mTime));

                        }
                        else if(taped>=2)
                        {

                            Log.i("Twice or more Pressed",String.valueOf(mTime));
                            if(muted==false) {
                                mv=mv-10;
                            }else{
                                muted =false;
                            }
                            am.setStreamVolume(AudioManager.STREAM_MUSIC, mv, 0);
                        }
                        else if(taped>=3)
                        {
                            Log.i("Twice or more Pressed",String.valueOf(mTime));
                            am.setStreamVolume(AudioManager.STREAM_MUSIC,0,0);
                            muted=true;
                        }
                        start=0;
                        ceck=0;
                        taped=0;
                    }
                }

                mTime++;
                Log.i("timeの数字",String.valueOf(mTime));

            }
        },0,1000);

        Toast.makeText(this, "(￣ー￣)スタートするで", Toast.LENGTH_SHORT).show();

        /*
        try {
            //task1とtask2の両方の処理が完了するまで待機
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // サービスの開始


    }

    int stp(int aa)
    {
        Toast.makeText(this, "(￣ー￣)ストップするで", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < 50; i++) {
            Toast.makeText(this, "(￣ー￣)ストップ" + i, Toast.LENGTH_SHORT).show();
        }
        // サービスの終了
        //stopService(new Intent(MainActivity.this, MainActivity.class));
        return 0;
    }
    //stopService(new Intent(MainActivity.this, MainActivity.class));
                //startService(new Intent(MainActivity.this, MainActivity.class));

    void msg(int bnk)
    {
        if(bnk==1)
        {
            Toast.makeText(this, "(￣ー￣)1!", Toast.LENGTH_SHORT).show();
        }
        else if(bnk==2)
        {
            Toast.makeText(this, "(￣ー￣)2!", Toast.LENGTH_SHORT).show();
        }
        else if(bnk==3)
        {
            Toast.makeText(this, "(￣ー￣)3!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "(￣ー￣)Errpr!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e)
    {
        // キーコードを表示する
        //textView.setText("KeyCode:" + e.getKeyCode());

        // 検索ボタンが押されたとき
        if (e.getKeyCode() == KeyEvent.KEYCODE_POWER)
        {
            // ボタンが押し込まれたとき
            if (e.getAction() == KeyEvent.ACTION_DOWN)
            {

                if(start==0){
                    start =mTime;
                }



                //new WaitCheck().execute();



                // 背景色を赤色にする
                //textView.setBackgroundColor(9);
                Toast.makeText(this, "(￣ー￣)einen schönen tag!", Toast.LENGTH_SHORT).show();
                taped++;
                //Log.d("TestService", "einen schönen tag!");
                //Log.d("TestService", "tapped = "+taped);

            }
        }
        // ボタンが離されたとき
        else if (e.getAction() == KeyEvent.ACTION_UP)
        {
            // 背景色を青色にする
            Toast.makeText(this, "(￣ー￣)Lassen Sie den Anker verankern!", Toast.LENGTH_SHORT).show();
            Log.d("TestService", "Lassen Sie den Anker verankern!");

            //textView.setBackgroundColor(1);
        }


        return super.dispatchKeyEvent(e);
    }

    @Override
    public void onUserLeaveHint()
    {
        //ホームボタンが押された時や、他のアプリが起動した時に呼ばれる
        //戻るボタンが押された場合には呼ばれない
        Toast.makeText(getApplicationContext(), "Good bye!" , Toast.LENGTH_SHORT).show();
    }



    View.OnClickListener ServiceStpListener = new View.OnClickListener()
    {
        public void onClick(View view)
        {
            stopService(new Intent(MainActivity.this, PAButtonConvert.class));
            stp(1);
        }

    };


}


