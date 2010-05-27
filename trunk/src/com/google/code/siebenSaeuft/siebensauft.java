package com.google.code.siebenSaeuft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.code.trinkspiele.R;

public class siebensauft extends Activity {
	Button wuerfeln;
	SiebenSauftLogik neuesSpiel; 
	ImageView wuerfel1, wuerfel2;
	TextView textfenster;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siebensaeuft);
        neuesSpiel = new SiebenSauftLogik();
        textfenster=(TextView)findViewById(R.id.Textausgabe);
        wuerfel1=(ImageView)findViewById(R.id.ImageView01);
        wuerfel2=(ImageView)findViewById(R.id.ImageView02);
        wuerfeln=(Button)findViewById(R.id.Button01);
        wuerfeln.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ausgabe = neuesSpiel.chat();
				neuesSpiel.paint(neuesSpiel, wuerfel1, 0);
				neuesSpiel.paint(neuesSpiel, wuerfel2, 1);
				if (ausgabe == "Spiel zu Ende\nDanke fürs spielen") {
					textfenster.setText(ausgabe);
					wuerfeln.setText("neustart");
					neuesSpiel = new SiebenSauftLogik();
				}
				else{
					wuerfeln.setText("Würfeln");
					textfenster.setText(ausgabe);
				}
			}
		});
    }
}