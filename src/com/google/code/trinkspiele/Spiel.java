package com.google.code.trinkspiele;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

public abstract class Spiel {
	
	Context contex;

	//Ruft einen Dialog mit der Spielerklärung auf
	public void createHelperDialog(Context context, String message) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(context.getString(R.string.hilfe));
		builder.setMessage(message);
		builder.setPositiveButton(context.getString(R.string.ok), null);
		builder.show();
	}
	
	public void wirklichBeendenDialog(final Context context) {
		
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(context.getString(R.string.wirklich_beenden_titel));
		dialog.setMessage(context.getString(R.string.wirklich_zum_hauptmenue_message));
		dialog.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				((Activity) context).finish();
				context.startActivity(new Intent(context, Trinkspiele.class));
				Log.v("Spiel", "Neue Trinkspiel activity");
			}
		});
		dialog.setNegativeButton(context.getString(R.string.abbrechen), null);
		dialog.show();
	}
}
