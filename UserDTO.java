package dto;

/*** This DTO is the class object used for communicating user information for registration, log-in, log-out, etc. ***/
public class UserDTO {

	private String name;
	private String userName;
	private String userPwd;
	private String userEmail;
	private String parentEmail;
	private String isActiveSession;
	private String isKid;
	
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	public String getIsActiveSession() {
		return isActiveSession;
	}
	public void setIsActiveSession(String isActiveSession) {
		this.isActiveSession = isActiveSession;
	}
	public String getIsKid() {
		return isKid;
	}
	public void setIsKid(String isKid) {
		this.isKid = isKid;
	}
	
}
