package AppConstants;

public class AppConstants {
	
	/*** RESPONSE CONSTANTS FROM SERVER***/
	
	//Child log-in success, prompt parent to select the game.
	public String CHILD_LOGGED_IN_SUCCESS = "CHILD_LOG_IN";
	
	//Game select by parent, prompt the child.
	public String GAME_SELECT_BY_PARENT = "GAME_SELECT_BY_PARENT";
	
	//Question selected by parent, prompt the child.
	public String PROMPT_QUESTION_AT_CHILD = "PROMPT_QUESTION_AT_CHILD";
	
	//Child answered correctly, prompt the parent.
	public String CHILD_ANSWER_CORRECT = "CHILD_ANSWER_CORRECT";
	
	//Child answered incorrectly, prompt the parent.
	public String CHILD_ANSWER_INCORRECT = "CHILD_ANSWER_INCORRECT";
	
	//End of game at parent's terminal, prompt the child.
	public String END_OF_GAME_BY_PARENT = "END_OF_GAME_BY_PARENT";
	
	// After end of game, send Kid's score to parent.
	public String SEND_SCORE = "SEND_SCORE";
}
