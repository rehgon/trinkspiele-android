package com.google.code.trinkspiele;

import com.google.code.woody.woodroid;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Shaker {

	protected SensorManager mSensorManager ;
	protected float mAccel; // acceleration apart from gravity
	protected float mAccelCurrent; // current acceleration including gravity
	protected float mAccelLast; // last acceleration including gravity
	
	protected long lastTimeStamp;
	
	woodroid woodroid;
	
	public SensorManager getMSensorManager() {
		return mSensorManager;
	}
	
	public SensorEventListener getMSensorEventListener() {
		return mSensorListener;
	}
	
	public long getLastTimeStamp() {
		return lastTimeStamp;
	}
	
	public void initialize(Activity activity) {
		
		mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorListener, mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		mAccel =0.00f;
		mAccelCurrent = SensorManager.GRAVITY_EARTH;
		mAccelLast = SensorManager.GRAVITY_EARTH;
	}
	
	protected final SensorEventListener mSensorListener = new SensorEventListener() {
		
		public void onSensorChanged(SensorEvent se) {
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
}


