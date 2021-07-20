import java.util.HashMap;

public class IDandName {
	HashMap<String, String> nameInfo = new HashMap<String, String>();

	public IDandName(){
		nameInfo.put("123", "David Dai");
	}
	
	protected HashMap getLoginInfo() {
		return nameInfo;
	}
}
