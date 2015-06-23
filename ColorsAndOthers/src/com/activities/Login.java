package com.activities;

import http.interfac.ServerInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.registertest2.R;

public class Login extends Activity {
	private EditText etUsername, etPassword;
	private Button loginParent;
	private CheckBox isKid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initializeRegister();

		etUsername = (EditText) findViewById(R.id.loginUsername);
		etPassword = (EditText) findViewById(R.id.loginPassword);
		loginParent = (Button) findViewById(R.id.btnLogin);

		loginParent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserDetails.username = etUsername.getText().toString();
				UserDetails.password = etPassword.getText().toString();
				sendMessage(v);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	Intent intent = null;

	public void sendMessage(View view) {
		
		System.out
				.println("[User] :  Send button request received. Connecting to server...");
		intent = new Intent(this, RegistrationSuccess.class);
		new sync().execute((Object) null);
	}

	private class sync extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			String message = ServerInterface.logIn(UserDetails.username,
					UserDetails.password, "no");
			return message;
		}

		protected void onPostExecute(Object obres) {
			String result = (String) obres;
			System.out.println("[User] response from server :: " + result);
			intent.putExtra(MainActivity.EXTRA_MESSAGE, result);
			startActivity(intent);
		}

	}

	private void initializeRegister() {
		TextView registerScreen = (TextView) findViewById(R.id.btnLinkToRegisterScreen);
		registerScreen.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(i);
				finish();
			}
		});
	}

}
