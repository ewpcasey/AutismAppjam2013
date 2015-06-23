package com.activities;

import http.interfac.ServerInterface;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registertest2.R;

import dto.UserDTO;

public class MainActivity extends Activity {
	EditText etUsername, etEmail, etPassword, etFullname, etParentEmail;
	CheckBox isKid;
	UserDTO user = new UserDTO();
	Button registerButton;
	public static String EXTRA_MESSAGE = "3";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 backToLogin();
		etUsername = (EditText) findViewById(R.id.registerUsername);
		etEmail = (EditText) findViewById(R.id.registerEmail);
		etPassword = (EditText) findViewById(R.id.registerPassword);
		etFullname = (EditText) findViewById(R.id.registerName);
		etParentEmail = (EditText) findViewById(R.id.parentEmail);
		etParentEmail.setVisibility(View.GONE);
		isKid = (CheckBox) findViewById(R.id.isKid);
		
		isKid.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
					if(isChecked)
						etParentEmail.setVisibility(View.VISIBLE);
					else
						etParentEmail.setVisibility(View.GONE);
			}
			
		});
		registerButton = (Button) findViewById(R.id.btnRegister);
		registerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				UserDetails.fullname = etFullname.getText().toString();
				UserDetails.email = etEmail.getText().toString();
				UserDetails.password = etPassword.getText().toString();
				UserDetails.username = etUsername.getText().toString();
				UserDetails.parentemail = etParentEmail.getText().toString();
				if (UserDetails.username.equals("") || UserDetails.password.equals("")
						|| UserDetails.fullname.equals("") || UserDetails.email.equals("")) {
					Toast.makeText(getApplicationContext(), "Field Vaccant",
							Toast.LENGTH_LONG).show();
					return;
				}

				user.setUserName(UserDetails.username);
				user.setUserPwd(UserDetails.password);
				user.setUserEmail(UserDetails.email);
				user.setname(UserDetails.fullname);
				user.setParentEmail(UserDetails.parentemail);
				user.setIsKid(isKid.isChecked() ? "yes" : "no");
				sendMessage(v);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	Intent intent = null;

	public void sendMessage(View view) {
		
		System.out
				.println("[User] : Send button request received. Connecting to server...");
		intent = new Intent(this, RegistrationSuccess.class);
		new sync().execute((Object) null);
	}

	private class sync extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			String message = ServerInterface.registerUser(user);
			return message;
		}

		protected void onPostExecute(Object obres) {
			String result = (String) obres;
			System.out.println("[User] response from server :: " + result);
			intent.putExtra(EXTRA_MESSAGE, result);
			startActivity(intent);
		}

	}

	private void backToLogin() {
		TextView backToLog = (TextView) findViewById(R.id.btnLinkToLoginScreen);
		backToLog.setOnClickListener(new View.OnClickListener() {

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
