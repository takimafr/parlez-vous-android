package com.excilys.formation.parlezvous;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ParlezVousActivity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parlezvous2);
		SharedPreferences prefs = getSharedPreferences("users_credentials", MODE_PRIVATE);
		String username = prefs.getString("username", "");
		String password = prefs.getString("password", "");
		// Without preferences but with extras
		// Intent intent = getIntent();
		// String username = intent.getStringExtra("username");
		// String password = intent.getStringExtra("password");
		Toast.makeText(this, username + "/" + password, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logout:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Voulez-vous vraiment vous déconnecter ?").setCancelable(false) //
					.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							SharedPreferences prefs = getSharedPreferences("users_credentials", MODE_PRIVATE);
							SharedPreferences.Editor editor = prefs.edit();
							editor.remove("username");
							editor.remove("password");
							editor.commit();
							finish();
						}
					}).setNegativeButton("Non", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			AlertDialog alert = builder.create();
			alert.show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
