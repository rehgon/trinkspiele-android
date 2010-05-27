package com.google.code.meier;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.code.trinkspiele.R;

public class Meieroid extends Activity {

	ImageView image, wuerfelEins, wuerfelZwei;
	boolean wuerfelSichtbar;
	Meier meier;

	public void onCreate(Bundle savedInstanceState) {

		
		luegenDialog();
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meier);

		wuerfelSichtbar = true;

		image = (ImageView) findViewById(R.id.meierImage);
		wuerfelEins = (ImageView) findViewById(R.id.wuerfelEinsImage);
		wuerfelZwei = (ImageView) findViewById(R.id.wuerfelZweiImage);
		meier = new Meier();
		image.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (wuerfelSichtbar) {
					image.setImageResource(R.drawable.wuerfelbecher_gross);
					wuerfelEins.setVisibility(4);
					wuerfelZwei.setVisibility(4);
					wuerfelSichtbar = false;
				} else {
					image.setImageResource(R.drawable.wuerfelbecher_klein);
					wuerfelEins.setVisibility(0);
					wuerfelZwei.setVisibility(0);
					wuerfelSichtbar = true;
				}
			}
		});
	}
	
	public CharSequence[] makeCharSequenceNumbers() {
		
		ArrayList<CharSequence> numbers = new ArrayList<CharSequence>();
		CharSequence[] charNumbers;
		
		for (int i = 1; i <= 50; i++) {
			numbers.add(i+"");
		}
		
		charNumbers = new CharSequence[numbers.size()];
		for (int i = 0; i < numbers.size(); i++) {
			charNumbers[i] = numbers.get(i);
			numbers.remove(i);
		}
		return charNumbers;
	}

	public void luegenDialog() {

		/*
		for (int i = 31; i <= 65; i++) {
			if ((i % 10) < (i / 10) && (i % 10 != 0)) {
				numbers.add(i + "");
				index++;
			}
		}
		*/
		
		final CharSequence[] charNumbers = makeCharSequenceNumbers();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Wähle eine Zahl");
		builder.setSingleChoiceItems(charNumbers, 0, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String s = (String) charNumbers[which];
				meier.setGelogeneZahl(Integer.parseInt(s));
			}
		});
		builder.show();
	}
}
