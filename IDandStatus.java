import java.util.HashMap;

public class IDandStatus {
	private HashMap<String, String> IDstatus = new HashMap<String, String>();
	
	public IDandStatus() {
		
	}
	
	protected String get(String userID) {
		return IDstatus.get(userID);
	}
	
	public void putIDandStatus(String ID, String status) {
		IDstatus.put(ID, status);
	}
}
