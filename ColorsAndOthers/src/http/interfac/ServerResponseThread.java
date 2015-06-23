package http.interfac;


import com.activities.ChildloginSuccess;
import com.activities.ServerClasses;
import com.activities.ServerClasses.SYNC_CHILD_LOGGED_IN_SUCCESS;

import AppConstants.AppConstants;
import android.content.Context;
import android.content.Intent;

public class ServerResponseThread implements Runnable {

	@Override
	public void run() {
		
		AppConstants K = new AppConstants();
		while (true) {
			String response = ServerInterface.getResponseViaPolling();
			if(response == ""){
				
				//If no response received from System, wait for 400 ms and continue
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				continue;
			}
			
			// Process the response for the desired UI
			// Response format --> "Event, Message"
			// Display the Message in the screen based on Event
			
			String[] responseArr = response.split(",");
			String eventName = responseArr[0];
			String  responseMessage = responseArr[1];
			Intent intent = null;
			
			//Create a new intent (new screen) for parent to prompt for game select as Kid has joined the game. 
			if(eventName.equals(K.CHILD_LOGGED_IN_SUCCESS)){
			}
			
			//Create a new intent (new screen) for Kid to display the "Hey XXXX, get ready for playing YYYY".
			else if(eventName.equalsIgnoreCase(K.GAME_SELECT_BY_PARENT)){
				
			}
			
			//Create intent (new screen) for kid to display the question
			else if(eventName.equalsIgnoreCase(K.PROMPT_QUESTION_AT_CHILD)){
				
			}
			
			//Create a new intent (new screen) for parent to show if Kid has answered the question correctly.
			else if(eventName.equalsIgnoreCase(K.CHILD_ANSWER_CORRECT)){
				
			}
			
			//Create a new intent (new screen) for parent to show if Kid has answered the question in-correctly.
			else if(eventName.equalsIgnoreCase(K.CHILD_ANSWER_INCORRECT)){
				
			}
			
			//Create a new intent (new screen) for Kid to show that game has ended.
			else if(eventName.equalsIgnoreCase(K.END_OF_GAME_BY_PARENT)){
				
			}
			
			//Create a new intent (new screen) for parent to display the kid's score.
			else if(eventName.equalsIgnoreCase(K.SEND_SCORE)){
				
			}
			
			// Do a gentle polling.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
