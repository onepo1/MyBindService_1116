package com.example.edu.mybindservice_1116;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private BackgroundMusicWithBindService mServiceBinder;

    Button btn_stop, btn_play;


    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mServiceBinder =((BackgroundMusicWithBindService.MyBinder) service).getService();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBinder = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        ((Button) findViewById(R.id.btn_stop)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_play)).setOnClickListener(this);
        Intent intent = new Intent(this, BackgroundMusicWithBindService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.btn_play):
                mServiceBinder.play();

//                btn_play.setEnabled(false);
//                btn_stop.setEnabled(true);
                break;
            case (R.id.btn_stop):
                mServiceBinder.selfStop();
                break;
        }

    }

}
