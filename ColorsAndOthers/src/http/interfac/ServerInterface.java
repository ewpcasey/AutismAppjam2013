package http.interfac;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import AppConstants.AppConstants;

import com.activities.MainActivity;

import dto.UserDTO;


/**
 * Class: ServerInterface - Provides static methods to abstract the server
 * calls. This makes it easy for calling classes to use these functions without
 * worrying about the details of the server communication.
 * 
 */

public class ServerInterface {
	
		static ServerResponseThread threadObj = null;
		static Thread thread = null;
		static AppConstants K = new AppConstants();
 	
        // Declared Constants
        public static final String SERVER_URL = "http://sandboxed.in/appjam/php/Server.php";

        /***
         * --Way to call this API
         * --create a User DTO object:
         * UserDTO userDto = new UserDTO();
         * userDto.setName("ABC");
         * .......
         * --set other user details in the DTO;
         * .......
         * String response = ServerInterface.register(UserDto);
         * 
         ***/
        
        public static String registerUser(UserDTO userDTO){
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>(7);
        	params.add(new BasicNameValuePair("command", "register"));
        	params.add(new BasicNameValuePair("name", userDTO.getname()));
        	params.add(new BasicNameValuePair("username", userDTO.getUserName()));
        	params.add(new BasicNameValuePair("pwd", userDTO.getUserPwd()));
        	params.add(new BasicNameValuePair("isKid", userDTO.getIsKid()));
        	params.add(new BasicNameValuePair("kidEmail", userDTO.getIsKid() == "yes" ? userDTO.getUserEmail() : ""));
        	params.add(new BasicNameValuePair("parentEmail", userDTO.getIsKid() == "yes" ? userDTO.getParentEmail() : userDTO.getUserEmail()));
        	
        	return httpPost(params);
        	
        }
        
        public static String logIn(String userName, String pwd, String isKid){
        	
        	 //Start the thread to receive response from user
        	List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        	params.add(new BasicNameValuePair("command", "login"));
        	params.add(new BasicNameValuePair("username", userName));
        	params.add(new BasicNameValuePair("pwd", pwd));
        	
        	threadObj = new ServerResponseThread();
        	thread = new Thread(threadObj);
        	thread.start();
  
        	
        	String response = httpPost(params);
        	if(response.contains("welcome") && isKid.equalsIgnoreCase("yes")){
        		sendChildLoginSuccessToParent();
        	}
        	
        	return response;
        	
        }

        public static String logOut(String userName){
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        	params.add(new BasicNameValuePair("command", "logout"));
        	params.add(new BasicNameValuePair("username", userName));
        	
        	//Stop the thread once user logs-out
        	thread.stop();
        	return httpPost(params);
        }

        public static String getResponseViaPolling(){
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        	params.add(new BasicNameValuePair("command", "getResponseViaPolling"));
        	params.add(new BasicNameValuePair("username", com.activities.UserDetails.username));
        	
        	return httpPost(params);
        	
        }
        
        public static void sendChildLoginSuccessToParent(){
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        	params.add(new BasicNameValuePair("command", "childLoginSuccess"));
        	params.add(new BasicNameValuePair("username", com.activities.UserDetails.username));
        	params.add(new BasicNameValuePair("event", K.CHILD_LOGGED_IN_SUCCESS));
        	
        	httpPost(params);
        }
        
        public static void sendGameInfoToMyChild(String gameName){
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>(4);
        	params.add(new BasicNameValuePair("command", "sendGameNameToMyChild"));
        	params.add(new BasicNameValuePair("username", com.activities.UserDetails.username));
        	params.add(new BasicNameValuePair("gamename", gameName));
        	params.add(new BasicNameValuePair("event", K.GAME_SELECT_BY_PARENT));
        	
        	httpPost(params);
        }
        
        public static void sendQuestionToTheKid(int imageID){
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>(4);
        	params.add(new BasicNameValuePair("command", "sendQuestion"));
        	params.add(new BasicNameValuePair("username", com.activities.UserDetails.username));
        	params.add(new BasicNameValuePair("imageid", Integer.toString(imageID)));
        	params.add(new BasicNameValuePair("event", K.PROMPT_QUESTION_AT_CHILD));
        	
        	httpPost(params);
        	
        }
        
        public static void sendCorrectAnswerResponse(){
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        	params.add(new BasicNameValuePair("command", "sendCorrectAnsResponse"));
        	params.add(new BasicNameValuePair("username", com.activities.UserDetails.username));
        	params.add(new BasicNameValuePair("event", K.CHILD_ANSWER_CORRECT));
        	
        	httpPost(params);
        }

        public static void sendInCorrectAnswerResponse(){
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        	params.add(new BasicNameValuePair("command", "sendInCorrectAnsResponse"));
        	params.add(new BasicNameValuePair("username", com.activities.UserDetails.username));
        	params.add(new BasicNameValuePair("event", K.CHILD_ANSWER_INCORRECT));
        	
        	httpPost(params);
        }
        
        public static void sendEndOfGameToChild(){
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        	params.add(new BasicNameValuePair("command", "endOfGame"));
        	params.add(new BasicNameValuePair("username", com.activities.UserDetails.username));
        	params.add(new BasicNameValuePair("event", K.END_OF_GAME_BY_PARENT));
        	httpPost(params);
        	
        }
        
        //Create the API to send the child's score to parent
        public static void sendChildScore(int score){
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>(4);
        	params.add(new BasicNameValuePair("command", "sendScore"));
        	params.add(new BasicNameValuePair("username", com.activities.UserDetails.username));
        	params.add(new BasicNameValuePair("score", Integer.toString(score)));
        	params.add(new BasicNameValuePair("event", K.SEND_SCORE));
        	httpPost(params);
        	
        }


        /**
         * Helper function used to communicate with the server by sending/receiving
         * POST commands.
         * @param data String representing the command and (possibly) arguments.
         * @return String response from the server.
         */
        public static String httpPost(List<NameValuePair> params){
        	HttpClient httpclient = new DefaultHttpClient();
        	HttpPost httppost = new HttpPost(SERVER_URL);

        	// Request parameters and other properties.
        	try {
				httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        	//Execute and get the response.
        	HttpResponse response = null;
			try {
				response = httpclient.execute(httppost);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			HttpEntity entity = response.getEntity();
        	
        	String ret = "";
        	if (entity != null) {
        	    InputStream instream = null;
				try {
					
					instream = entity.getContent();
					BufferedReader buf = new BufferedReader(new InputStreamReader(instream,"UTF-8"));
					String temp;
					
					while((temp = buf.readLine()) != null){
						ret += temp + "\n";
					}
					
					System.out.println("[User] :: response from server :: " + ret);
					
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
        	        try {
						instream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
        	    }
        	}
        	return ret;
        }
}
