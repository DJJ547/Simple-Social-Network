import java.util.HashMap;

public class IDandPasswords {
	private HashMap<String, String> IDpassword = new HashMap<String, String>();

	public IDandPasswords(){
		
	}
	
	protected HashMap<String, String> getLoginInfo() {
		return IDpassword;
	}
	
	protected boolean containsKey(String key) {
		return IDpassword.containsKey(key);
	}
	
	protected String get(String userID) {
		return IDpassword.get(userID);
	}
	
	public void putIDandPassword(String ID, String password) {
		IDpassword.put(ID, password);
	}
}
