package com.google.code.trinkspiele;

import android.app.AlertDialog;
import android.content.Context;

public abstract class Spiel {

	//Ruft einen Dialog mit der Spielerklärung auf
	public void createHelperDialog(Context context, String message) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Hilfe");
		builder.setMessage(message);
		builder.setPositiveButton("Ok", null);
		builder.show();
	}
}
