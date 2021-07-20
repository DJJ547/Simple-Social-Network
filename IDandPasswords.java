import java.util.HashMap;

public class IDandPasswords {
	HashMap<String, String> loginInfo = new HashMap<String, String>();

	public IDandPasswords(){
		loginInfo.put("123", "123");
	}
	
	protected HashMap getLoginInfo() {
		return loginInfo;
	}
}
