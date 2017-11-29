package com.wshadow.powermonitoring;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wshadow.powermonitoring.data.PowerDataHelper;
import com.wshadow.powermonitoring.util.LogUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start;
    private Button end;
    private Button read;
    private boolean isStartService = false;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.d("Service connected");
            recordBinder = (RecordService.RecordBinder) service;
            recordBinder.startRecording();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private RecordService.RecordBinder recordBinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("onCreate");
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.bt_start_recording);
        end = findViewById(R.id.bt_stop_recording);
        read = findViewById(R.id.bt_read);
        start.setOnClickListener(this);
        end.setOnClickListener(this);
        read.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start_recording:
                start();
                break;
            case R.id.bt_stop_recording:
                stop();
                break;
            case R.id.bt_read:
                PowerDataHelper powerDataHelper = new PowerDataHelper();
                Intent intent = powerDataHelper.getFileIntent();
                startActivity(intent);
                break;
        }
    }

    private void start() {
        LogUtil.d("click start");
        if (!isStartService) {
            showMessage("开始");
            Intent bindIntent = new Intent(this, RecordService.class);
            bindService(bindIntent, connection, BIND_AUTO_CREATE);
            isStartService = true;
        } else {
            recordBinder.startRecording();
        }
        start.setTextColor(getResources().getColor(R.color.colorAccent));
        end.setTextColor(getResources().getColor(R.color.black));
    }

    private void stop() {
        LogUtil.d("click stop");
        if (recordBinder != null && isStartService) {
            recordBinder.stopRecording();
            end.setTextColor(getResources().getColor(R.color.colorAccent));
            start.setTextColor(getResources().getColor(R.color.black));
            showMessage("停止");
        } else {
            showMessage("还没开始");
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
