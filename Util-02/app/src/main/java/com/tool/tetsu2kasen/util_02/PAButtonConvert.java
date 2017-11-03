package com.tool.tetsu2kasen.util_02;



import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;
;import java.util.EventListener;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by tetsu2kasen on 2017/10/27.
 */



public class PAButtonConvert extends Service {


    private Timer timer = null;
    private int count = 0;





    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(){

        HandlerThread thread = new HandlerThread("ServiceStartArguments");
        thread.start();

        Log.i("TestService", "onCreate");

    }


    int keyCode = 0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("TestService", "onStartCommand");


        pow(1);


        //srvlp();

        timer = new Timer();
        timer.schedule( new TimerTask(){
            @Override
            public void run(){


                //public boolean onKeyDown(int keyCode, KeyEvent event){

                if(keyCode == KeyEvent.KEYCODE_BACK) {

                    Log.d("TestService", "BK_KEYTAPPED at count = " + count);


                        //return true;
                    //}
                    //return false;
                }

                    Log.d("TestService", "BK_KEYTAPPED at count = " + count);

                count++;
            }
        }, 0, 1000);

        pow(1);

        return super.onStartCommand(intent, flags, startId);


        //return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i("TestService", "onDestroy");
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.i("TestService", "onBind");
        return null;
    }

    //@Override


    int  pow(int aa){
        Toast.makeText(this, "(￣ー￣)サービス稼働始めたで（byPABService",Toast.LENGTH_SHORT).show();
        return 0;
    }

    int  MSG(){
        Toast.makeText(this, "(￣ー￣)BK_KEY押されたで（byPABService",Toast.LENGTH_SHORT).show();
        return 0;
    }



}

