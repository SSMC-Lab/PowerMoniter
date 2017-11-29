package com.wshadow.powermonitoring.data;

import android.os.BatteryManager;

import java.io.Serializable;

/**
 * 电池状态实体类
 * Created by WelkinShadow on 2017/11/24.
 */

public class PowerData implements Serializable{
    private String level;//当前电量
    private String scale;//电池刻度
    private String battery;//当前电量的百分比
    private String date;//日期
    private String status;//充电状态
    private String plug;//充电方式

    public PowerData(String level, String scale, String battery, String date, String status, String plug) {
        this.level = level;
        this.scale = scale;
        this.battery = battery;
        this.date = date;
        this.status = status;
        this.plug = plug;
    }

    public PowerData(int level, int scale, String date, int status, int plug) {
        this.level = String.valueOf(level);
        this.scale = String.valueOf(scale);
        this.battery = String.valueOf((int) (level / (float) scale * 100));
        this.date = date;
        switch (status) {
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                this.status = "UNKNOWN";
                break;
            case BatteryManager.BATTERY_STATUS_CHARGING:
                this.status = "CHARGING";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                this.status = "DISCHARGING";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                this.status = "NOT CHARGING";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                this.status = "FULL";
                break;
            default:
                this.status = "NULL";
                break;
        }
        switch (plug) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                this.status = "AC";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                this.status = "USB";
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                this.status = "WIRELESS";
                break;
            default:
                this.status = "NULL";
                break;
        }
    }

    public String getLevel() {
        return level;
    }

    public String getScale() {
        return scale;
    }

    public String getBattery() {
        return battery;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getPlug() {
        return plug;
    }
}
