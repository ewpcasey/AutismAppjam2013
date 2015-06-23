package com.activities;

import com.example.registertest2.R;
import com.example.registertest2.R.layout;
import com.example.registertest2.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ChildloginSuccess extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.childloginsuccess);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.childlogin_success, menu);
		return true;
	}

}
