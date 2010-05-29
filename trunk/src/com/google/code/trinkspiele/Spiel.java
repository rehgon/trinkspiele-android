package com.google.code.trinkspiele;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public abstract class Spiel {

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
		dialog.setMessage(context.getString(R.string.wirklich_beenden_message));
		dialog.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				context.startActivity(new Intent(context, Trinkspiele.class));
			}
		});
		dialog.setNegativeButton(context.getString(R.string.abbrechen), null);
		dialog.show();
	}
}
