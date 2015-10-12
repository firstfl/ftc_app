package com.firstfl.sensors;

import android.hardware.SensorEvent;

/**
 * Created by chris on 10/12/15.
 */
public class Accelerometer {
    private float x;
    private float y;
    private float z;
    private long timestamp;
    private float accuracy;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public long getTimestamp(){
        return timestamp;
    }

    public float getAccuracy(){
        return accuracy;
    }

    protected void updateData(SensorEvent event){
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        timestamp = event.timestamp;
        accuracy = event.accuracy;
    }
}
