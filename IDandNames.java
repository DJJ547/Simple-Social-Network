import java.util.HashMap;

public class IDandNames {
	private HashMap<String, String> IDname = new HashMap<String, String>();

	public IDandNames(){
		
	}
	
	protected HashMap<String, String> getLoginInfo() {
		return IDname;
	}
	
	public void putIDandName(String ID, String name) {
		IDname.put(ID, name);
	}
}
