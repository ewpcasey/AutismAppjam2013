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

import dto.UserDTO;

/**
 * Class: ServerInterface - Provides static methods to abstract the server
 * calls. This makes it easy for calling classes to use these functions without
 * worrying about the details of the server communication.
 * 
 */
public class ServerInterface {

        // Declared Constants
        public static final String SERVER_URL = "http://192.168.90.1:80/Hello.php";

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
         */
        public static String registerUser(UserDTO userDTO){
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>(7);
        	params.add(new BasicNameValuePair("command", "register"));
        	params.add(new BasicNameValuePair("name", userDTO.getname()));
        	params.add(new BasicNameValuePair("username", userDTO.getUserName()));
        	params.add(new BasicNameValuePair("pwd", userDTO.getUserPwd()));
        	params.add(new BasicNameValuePair("isKid", userDTO.getIsKid()));
        	params.add(new BasicNameValuePair("kidEmail", userDTO.getIsKid() == "yes" ? userDTO.getUserEmail() : ""));
        	params.add(new BasicNameValuePair("parentEmail", userDTO.getParentEmail()));
        	
        	return httpPost(params);
        	
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
        	params.add(new BasicNameValuePair("command", "getHelloWorld"));
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
