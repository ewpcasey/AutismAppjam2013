package Facade;

public interface IClientFacade {
	
	/***** Common APIs *******/
	
	//Thsi API will create the user/kid's gaming session on the mobile device once he is logged-in successfully.
	abstract public String createSession();
	
	//This API will show the "Welcome XXXX" message after user is successfully logged-in.
	abstract public String displayUserDetails();
	
	//This API wil end the user session after the game successfully finishes and user opts to log-out
	abstract public String endSession();
	
	/***** Child client specific APIs ****/
	
	//This API will allow the child to start the game @ his terminal.
	abstract public String startGameChild();
		
	//Using this API, child will answer the question
	abstract public String answer();
	
	//In case parent is not available for play, child can proceed his play alone in an "Independent mode" and this API
	//will fetch the questions from our pre-defined set at random.
	abstract public String nextQuestionAuto();
	
	//This API will display the question on the user screen
	abstract public String displayQuestion();
	
	//We need to brainstorm in order to implement next 2 APIs. These APIs will pause/ restore the current game session.  
	abstract public String pauseGame();
	
	abstract public String restoreGame();
	/***** Child server specific APIs ****/
	
	//This API will be called by Server to send the user score after game is finished. 
	abstract public String displayScoreChild();
	
	/***** Parent client specific APIs ****/
	
	//This API will allow parent to select the game from the options like colors, images, etc
	abstract public String selectGame();
	
	//This API will allow the parent to start the game @ his terminal.
	abstract public String startGameParent();
	
	//This API will allow the parent to pick the next question for the kid in case kid is playing with parent.
	abstract public String nextQuestion();
	
	//Thsi API will allow the parent to create a new album of ".JPEG" files which they can use as another game in future.
	abstract public String createAlbum();
	
	//This API will be used to upload photos from parent terminal. 
	abstract public String uploadPhoto();
	
	//This API will be used to record a voice along with the pic @ parent's terminal. Need to brainstorm more on this.
	abstract public String uploadVoice();
	
	//Optionaly, we may allow parent to preview their newly created albums.
	abstract public String displayPreview();
	
	/***** Parent server specific APIs ****/
	
	//This API will be called by the server to display the kid's score @ parent terminal.
	abstract public String displayScoreParent();
	
}
