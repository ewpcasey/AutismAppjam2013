package com.activities;

import http.interfac.ServerInterface;
import http.interfac.ServerResponseThread;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class ServerClasses extends Activity {

	public  Intent intent = null;
	public static class SYNC_CHILD_LOGGED_IN_SUCCESS extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class SYNC_GAME_SELECT_BY_PARENT extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			ServerInterface.sendChildLoginSuccessToParent();
			return null;
		}
		protected void onPostExecute(Object obres) {
			String result = (String) obres;
			System.out.println("[User] response from server :: " + result);
			startActivity(intent);
		}
	}

	public class SYNC_PROMPT_QUESTION_AT_CHILD extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			return null;
		}

	}

	public class SYNC_CHILD_ANSWER_CORRECT extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			ServerInterface.sendCorrectAnswerResponse();
			return null;
		}

	}

	public class SYNC_CHILD_ANSWER_INCORRECT extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class SYNC_END_OF_GAME_BY_PARENT extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			return null;
		}

		public class SYNC_SEND_SCORE extends AsyncTask {

			@Override
			protected Object doInBackground(Object... params) {
				// TODO Auto-generated method stub
				return null;
			}

		}

	}

}
