package com.google.code.woody;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.google.code.trinkspiele.Shaker;

public class WoodyShaker extends Shaker {
	
	public WoodyShaker() {
		super();
	}
	
	woodroid woodroid;
	
	private final SensorEventListener mSensorListener = new SensorEventListener() 
	{
		public void onSensorChanged(SensorEvent se) {
			
			lastTimeStamp = System.currentTimeMillis();
			
			float x = se.values[0];
			float y = se.values[1];
			float z = se.values[2];
			mAccelLast = mAccelCurrent;
			mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
			float delta = mAccelCurrent - mAccelLast;
			mAccel = (mAccel * 0.9f + delta); // perform low-cut filter

			if(mAccel > 3 && (System.currentTimeMillis() - lastTimeStamp) > 2000 && !woodroid.getWoody().getNeuerWoody()) {
				woodroid.handleEvent();
			}

		}
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
}
