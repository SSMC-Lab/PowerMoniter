package com.wshadow.powermonitoring;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Binder;
import android.os.IBinder;

import com.wshadow.powermonitoring.data.PowerData;
import com.wshadow.powermonitoring.data.PowerDataHelper;
import com.wshadow.powermonitoring.timer.TimerHelper;
import com.wshadow.powermonitoring.timer.TimerProcessor;
import com.wshadow.powermonitoring.util.LogUtil;
import com.wshadow.powermonitoring.util.TimeUtil;

public class RecordService extends Service {

    private PowerDataHelper powerDataHelper;
    private TimerHelper timerHelper;

    private RecordBinder mBinder = new RecordBinder();
    private RecordTask task = new RecordTask();

    class RecordTask implements TimerProcessor {

        @Override
        public void process() {
            LogUtil.d("Execute record task");
            IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent intent = registerReceiver(null, filter);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            String date = TimeUtil.getNowTimeString();
            PowerData powerData = new PowerData(level, scale, date, status, plug);
            powerDataHelper.write(powerData);
            timerHelper.startTimer();//重复执行
        }
    }

    class RecordBinder extends Binder {
        public void startRecording() {
            LogUtil.d("Start recording");
            timerHelper.startTimer();
        }

        public void stopRecording() {
            LogUtil.d("Stop recording");
            timerHelper.stopTimer();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("onCreate");
        timerHelper = new TimerHelper(Conditions.TIME, task);
        powerDataHelper = new PowerDataHelper();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
