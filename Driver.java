import java.util.ArrayList;

public class Driver {
	public static void main(String[] args) {
		IDandPasswords IDandPassword = new IDandPasswords();
		IDandImages IDandImage = new IDandImages();
		IDandNames IDandName = new IDandNames();
		IDandProfiles IDandProfile = new IDandProfiles();
		IDandStatus IDandStatus = new IDandStatus();
		ArrayList<ProfilePage> profileList = new ArrayList<ProfilePage>();
		
		ProfileManager manager = new ProfileManager(IDandImage, IDandName, 
				IDandPassword, IDandStatus, profileList);
		manager.displayLoginPage(manager);
	} // end main
} // end Drive