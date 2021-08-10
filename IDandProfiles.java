import java.util.HashMap;

public class IDandProfiles {
	private HashMap<String, ProfilePage> IDprofile = new HashMap<String, ProfilePage>();
	
	public IDandProfiles() {
		
	}
	
	protected ProfilePage get(String userID) {
		return IDprofile.get(userID);
	}
	
	public void putIDandProfile(String ID, ProfilePage newProfile) {
		IDprofile.put(ID, newProfile);
	}
}
