import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

public class ProfilePage  implements ActionListener{
	private ImageIcon picture;
	private String name;
	private String status = "Online";
	private ArrayList<ProfilePage> friendProfiles;
	private JFrame frame;
	private JLabel ImageLabel;
	private JLabel titleLabel = new JLabel("Your Profile");
	private JLabel nameLabel;
	private JLabel statusLabel = new JLabel("Status: " + status);
	private JLabel friendAmountLabel = new JLabel("# of friends: 0");
	private JLabel searchLabel = new JLabel("Name Search: ");
	
	private JScrollPane friendPanel = new JScrollPane();
	
	private JButton leaveButton = new JButton("Leave Network");
	private JButton modifyButton = new JButton("Modify Profile");
	private JButton searchButton = new JButton("Search");
	
	private JTextField nameSearchField = new JTextField();
	
	private String userID;
	
	HashMap<String, String> loginInfo = new HashMap<String, String>();
	HashMap<String, ImageIcon> accountInfo = new HashMap<String, ImageIcon>();
	HashMap<String, String> nameInfo = new HashMap<String, String>();
	
	UndirectedGraph graph = new UndirectedGraph();

	/** Constructor for an instance of a profile. */
	public ProfilePage(String ID, HashMap<String, String> loginInfoOriginal, HashMap<String, ImageIcon> 
	accountInfoOriginal, HashMap<String, String> nameInfoOriginal, UndirectedGraph ug) {
		
		userID = ID;
		loginInfo = loginInfoOriginal;
		accountInfo = accountInfoOriginal;
		nameInfo = nameInfoOriginal;
		
		graph = ug;
		
		name = nameInfo.get(userID);
		friendProfiles = new ArrayList<ProfilePage>();
		
		frame = new JFrame();
		
		picture = accountInfo.get(userID);
		ImageLabel = new JLabel(picture);
		
		nameLabel = new JLabel("Name: " + name);
		
		titleLabel.setBounds(30, 30, 150, 35);
		titleLabel.setFont(new Font(null, Font.BOLD, 20));
		ImageLabel.setBounds(30, 80, 110, 110);
		nameLabel.setBounds(40, 200, 120, 25);
		statusLabel.setBounds(40, 230, 120, 25);
		friendAmountLabel.setBounds(40, 260, 120, 25);
		searchLabel.setBounds(200, 400, 150, 30);
		searchLabel.setFont(new Font(null, Font.BOLD, 20));
		
		nameSearchField.setBounds(350, 400, 200, 30);
		
		leaveButton.setBounds(30, 300, 130, 35);
		leaveButton.setFocusable(false);
		leaveButton.addActionListener(this);
		modifyButton.setBounds(30, 350, 130, 35);
		modifyButton.setFocusable(false);
		modifyButton.addActionListener(this);
		searchButton.setBounds(570, 400, 130, 30);
		searchButton.setFocusable(false);
		searchButton.addActionListener(this);
		
		friendPanel.setBounds(200, 30, 550, 350);
		friendPanel.setBackground(Color.LIGHT_GRAY);
		friendPanel.setLayout(null);
		
		
		
		/**
		ScrollPaneLayout mySPLayout = new ScrollPaneLayout() {
		    public void layoutContainer(Container p) {
		        super.layoutContainer(p);
		        // do some extra work here ...
		    }
		};
		*/
		//friendPanel.setLayout(mySPLayout);
		
		Iterable users = graph.getAllVertices();
		int y = 5;
		for(Object s : users){
		    String uID = (String)s;
		    //System.out.println(userID);
		    if(uID.equals(userID)) {
		    	//do nothing if userID is myself
		    }else {
		    	ImageIcon img = accountInfo.get(uID);
		    	String name = nameInfo.get(uID);
		    	JPanel userPanel = new JPanel();
		    	userPanel.setBounds(5, y, 540, 120);
		    	userPanel.setLayout(null);
		    	userPanel.setBackground(Color.gray);
		    	friendPanel.add(userPanel);
		    	
		    	JLabel imgLabel = new JLabel(img);
		    	imgLabel.setBounds(5, 5, 100, 100);
		    	JLabel nLabel= new JLabel("Name: " + name);
		    	nLabel.setBounds(120, 15, 150, 25);
		    	JLabel sLabel = new JLabel("Status: Offline");
		    	sLabel.setBounds(120, 35, 150, 25);
		    	JButton addButton = new JButton("+ Add Friend");
		    	addButton.setFocusable(false);
		    	/**
		    	addButton.addActionListener(new ActionListener() {
		    	    public void actionPerformed(ActionEvent e) {
		    	        if(e.getSource() == addButton) {
		    	        	graph.addEdge(userID, uID);
		    	        	
		    	        }
		    	    }
		    	});
		    	*/
		    	addButton.setBounds(400, 70, 120, 40);
		    	
		    	//userPanel.setBounds(195, 30, 500, 300);
		    	//userPanel.setBackground(Color.red);
		    	//friendPanel.add(userPanel);
		    	userPanel.add(imgLabel);
		    	userPanel.add(nLabel);
		    	userPanel.add(sLabel);
		    	userPanel.add(addButton);
		    	//friendPanel.setViewportView();
		    	y = y + 125;
		    }
		}
		
		
		
		
		
		
		frame.add(titleLabel);
		frame.add(ImageLabel);
		frame.add(nameLabel);
		frame.add(statusLabel);
		frame.add(friendAmountLabel);
		frame.add(searchLabel);
		frame.add(leaveButton);
		frame.add(modifyButton);
		frame.add(searchButton);
		frame.add(nameSearchField);
		frame.add(friendPanel);
		
		frame.setTitle(name + "'s Profile Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		frame.setLayout(null);
		frame.setVisible(true);
	} // end default constructor

	/** Returns the picture associated with the profile.
		@return  The profile's picture. */
	public ImageIcon getProfilePicture() {
		return picture;
	} // end getProfilePicture

	/** Sets the picture associated with the profile to the given picture.
		@param newPicture  The new picture associated with the profile. */
	public void setProfilePicture(ImageIcon newPicture) {
		picture = newPicture;
	} // end setProfilePicture

	/** Sets the name associated with the profile to the given name.
       @param firstName  The first name for the profile.
       @param lastName   The last name for the profile. */
	public void setName(String name) {
	   	this.name = name;
	} // end setName

	/** Returns the name associated with the profile.
       @return  The profile's name. */
	public String getName() {
		return name;
	} // end getName
	
	/** Sets the current status of the profile to the given string.
		@param stat  The new status for the profile. */
	public void setStatus(String stat) {
		status = stat;
	} // end setStatus

	/** Returns the status associated with the profile.
		@return  The profile's status.*/
	public String getStatus() {
		return status;
	} // end getStatus

	/** Returns a list of all the person's friendProfiles on the social network.
		@return  The list of friendProfiles. */
	public ArrayList<ProfilePage> getFriends() {
		return friendProfiles;
	} // end getFriends

	/** Adds a friend to the profile's list of friendProfiles.
		@param p  The profile to be added to the list. */
	public void addFriend(ProfilePage p) {
		friendProfiles.add(p);
	} // end addFriend

	/** Removes a friend from the profile's list of friendProfiles.
		@param p  The profile to be removed from the list.
       @return  True if the profile was removed. */
	public boolean removeFriend(ProfilePage p) {
		if(friendProfiles.contains(p)) {
			friendProfiles.remove(p);
			return true;
		}else {
			return false;
		}
	} // end removeFriend

	public String toString() {
		return "Name: " + name + "\nStatus: " 
	+ status + "\nTheir Friends: " ;
	} // end toString

	/** Displays the profile's information and friendProfiles. */
	public void display() {
		
	} // end display

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == leaveButton) {
			frame.dispose();
			LoginPage lg = new LoginPage(loginInfo, accountInfo, nameInfo, graph);
		}
		if(e.getSource() == modifyButton) {
			
		}
		if(e.getSource() == searchButton) {
			//if(nameSearchField.getText()) {
				
			//}
		}
		
	}
} // end ProfilePage