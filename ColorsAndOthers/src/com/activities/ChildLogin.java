package com.activities;

import http.interfac.ServerInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.registertest2.R;

public class ChildLogin extends Activity {

	EditText etusername;
	Button cLogin;
	static String EXTRA_MESSAGE = "1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.child);
		parentLogin();
		cLogin = (Button) findViewById(R.id.childLoginButton);
		etusername = (EditText) findViewById(R.id.childUsername);

		cLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserDetails.username = etusername.getText().toString();

				sendMessage(v);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.child_login, menu);
		return true;
	}

	Intent intent = null;

	public void sendMessage(View view) {
		
		System.out.println("[User] : Send button request received. Connecting to server...");
		intent = new Intent(this, RegistrationSuccess.class);
		new sync().execute((Object) null);
	}

	public class sync extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			String message = ServerInterface.logIn(UserDetails.username, "", "yes");
			return message;
		}

		protected void onPostExecute(Object obres) {
			String result = (String) obres;
			System.out.println("[User] response from server :: " + result);
			intent.putExtra(MainActivity.EXTRA_MESSAGE, result);
			startActivity(intent);
		}


	}

	private void parentLogin() {
		TextView parentLog = (TextView) findViewById(R.id.ParentButton);
		parentLog.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), Login.class);
				startActivity(i);
				finish();
			}
		});
	}

}
