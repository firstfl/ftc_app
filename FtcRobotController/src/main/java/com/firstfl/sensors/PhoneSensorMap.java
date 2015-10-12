package com.firstfl.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;

/**
 * Created by chris on 10/12/15.
 */
public class PhoneSensorMap {
    private SensorManager sensorManager;
    ArrayList<SensorEventListener> listeners = new ArrayList<SensorEventListener>();

    public PhoneSensorMap(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    private void registerListener(SensorEventListener listener, Sensor sensor, int rate){
        listeners.add(listener);
        sensorManager.registerListener(listener, sensor, rate);
    }

    private void registerListener(SensorEventListener listener, int sensor, int rate){
        registerListener(listener, sensorManager.getDefaultSensor(sensor), rate);
    }

    public Accelerometer getAccelerometer(){
        final Accelerometer accelerometer = new Accelerometer();
        registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                accelerometer.updateData(event);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, Sensor.TYPE_ACCELEROMETER, SensorManager.SENSOR_DELAY_GAME);
        return accelerometer;
    }


}
