import javax.swing.*;
import java.awt.*;

/**
	An implementation of a profile manager on a simple social network.

	@author JiaJun Dai
*/
public class ProfileManager {
	private UndirectedGraph<ProfilePage> allProfiles;

	/** Constructor for an instance of a profile manager. */
	public ProfileManager() {
		allProfiles = new UndirectedGraph<>();
	} // end default constructor

	/** Adds a profile to the social network.
		@param p  The profile to be added to the network. */
	public void addProfile(ProfilePage p) {
		allProfiles.addVertex(p);
	} // end addProfile

	/** Creates a friendship between two users on the social network.
		@param a  The first profile in the friendship.
		@param b  The second profile in the friendship. */
	public void createFriendship(ProfilePage a, ProfilePage b) {
		allProfiles.addEdge(a, b);
	} // end createFriendship

	/** Displays each profile's information and friends. */
	public void display(ProfilePage startPoint) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
	}
} // end ProfileManager
