package com.wshadow.powermonitoring.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.wshadow.powermonitoring.Conditions;
import com.wshadow.powermonitoring.MyApplication;
import com.wshadow.powermonitoring.util.LogUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * 数据记录操作
 * Created by WelkinShadow on 2017/11/24.
 */

public class PowerDataHelper {

    public void write(PowerData data) {
        File dir = new File(Environment.getExternalStorageDirectory().getPath()+"/PowerMonitor/");
        if(!dir.exists())
        { //如果该文件夹不存在，则进行创建
            dir.mkdirs();//创建文件夹
        }
        File file = new File(Environment.getExternalStorageDirectory().getPath()+"/PowerMonitor/",Conditions.RECORD_NAME);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        StringBuilder text = new StringBuilder();
        text.append("电量:");
        text.append(" ");
        text.append(data.getLevel());
        text.append("%  ");
        text.append(data.getDate());
        text.append("\n");
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = new FileOutputStream(file,true);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(text.toString());
            LogUtil.d("write : " + text.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                    LogUtil.d("close");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeAll(List<PowerData> data) {

    }

    public PowerData read() {
        return null;
    }

    public List<PowerData> readAll() {
        return null;
    }

    public Intent getFileIntent() {
        File file = new File(MyApplication.getContext().getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS) , Conditions.RECORD_NAME);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "text/plain");
        return intent;
    }
}