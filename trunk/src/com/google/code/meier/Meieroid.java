package com.google.code.meier;

import com.google.code.trinkspiele.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Meieroid extends Activity {

	ImageView image, wuerfelEins, wuerfelZwei;
	boolean wuerfelSichtbar;

	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meier);
		
		wuerfelSichtbar = true;
		
		image = (ImageView) findViewById(R.id.meierImage);
		wuerfelEins = (ImageView) findViewById(R.id.wuerfelEinsImage);
		wuerfelZwei = (ImageView) findViewById(R.id.wuerfelZweiImage);
		image.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (wuerfelSichtbar) {
					image.setImageResource(R.drawable.wuerfelbecher_gross);
					wuerfelEins.setVisibility(4);
					wuerfelZwei.setVisibility(4);
					wuerfelSichtbar = false;
				}
				else {
					image.setImageResource(R.drawable.wuerfelbecher_klein);
					wuerfelEins.setVisibility(0);
					wuerfelZwei.setVisibility(0);
					wuerfelSichtbar = true;
				}
			}
		});
	}
}
