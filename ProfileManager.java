import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.ArrayList;

/**
	An implementation of a profile manager on a simple social network.

	@author JiaJun Dai
*/
public class ProfileManager {
	private UndirectedGraph<ProfilePage> allProfiles;
	private IDandImages IDandImage = new IDandImages();
	private IDandNames IDandName = new IDandNames();
	private IDandPasswords IDandPassword = new IDandPasswords();
	private IDandProfiles IDandProfile = new IDandProfiles();
	private IDandStatus IDandStatus = new IDandStatus();
	private ArrayList<ProfilePage> profileList = new ArrayList<ProfilePage>();
	
	private JPanel eachUserPanel;
	private JLabel imgLabel;
	private JLabel nLabel;
	private JLabel sLabel;
	private Border blackline = BorderFactory.createLineBorder(Color.black);
	
	/** Constructor for an instance of a profile manager. */
	public ProfileManager(IDandImages IDandImage, 
			IDandNames IDandName, IDandPasswords IDandPassword, 
			IDandStatus IDandStatus, ArrayList<ProfilePage> profileList) {
		allProfiles = new UndirectedGraph<>();
		this.IDandImage = IDandImage;
		this.IDandName = IDandName;
		this.IDandPassword = IDandPassword;
		this.IDandStatus = IDandStatus;
		this.profileList = profileList;
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

	/** Removes a friendship between two users on the social network.
	@param a  The first profile in the friendship.
	@param b  The second profile in the friendship. */
	public void removeFriendship(ProfilePage a, ProfilePage b) {
		allProfiles.removeEdge(a, b);
	} // end createFriendship
	
	public boolean checkFriendship(ProfilePage a, ProfilePage b) {
		return allProfiles.isAdjacent(a, b);
	}
	
	public void modifyPageChange(String ID, String name, String password, String status, ImageIcon image) {
		IDandName.putIDandName(ID, name);
		IDandPassword.putIDandPassword(ID, password);
		IDandStatus.putIDandStatus(ID, status);
		IDandImage.putIDandImage(ID, image);
	}
	
	/** Displays each profile's information and friends for testing */
	public void displayNetwork() {
		int counter = 1;
		for(ProfilePage p1: getAllUser()) {
			System.out.println("User" + counter + ": " + p1.getName() + ", " 
		+ "status: " + p1.getStatus() + ", " + "friends incude: ");
			for(ProfilePage p2: allProfiles.getNeighbors(p1)) {
				System.out.println(p2.getName() + " ");
			}
			System.out.println();
			counter++;
		}
	}
	
	public void displayLoginPage(ProfileManager manager) {
		JFrame frame = new JFrame();
		JButton loginButton = new JButton("Login");
		JButton resetButton = new JButton("Reset");
		JButton signupButton = new JButton("SignUp");
		JTextField userIDField = new JTextField();
		JPasswordField userPasswordField = new JPasswordField();
		JLabel userIDLabel = new JLabel("userID:");
		JLabel userPasswordLabel = new JLabel("password:");
		JLabel messageLabel = new JLabel();
		
		userIDLabel.setBounds(50, 100, 75, 25);
		userPasswordLabel.setBounds(50, 150, 75, 25);
		messageLabel.setBounds(125, 170, 250, 35);
		messageLabel.setFont(new Font(null, Font.ITALIC, 20));
		
		userIDField.setBounds(125, 100, 200, 25);
		userPasswordField.setBounds(125, 150, 200, 25);
		
		loginButton.setBounds(125, 200, 100, 25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(e -> {
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			if(IDandPassword.containsKey(userID)) {
				if(IDandPassword.get(userID).equals(password)) {
					messageLabel.setForeground(Color.green);
					messageLabel.setText("Login successful");
					frame.dispose();
					IDandProfile.get(userID).displayProfilePage(manager);
					manager.displayNetwork();
					
				}else {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("wrong password!");
				}
			}else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("username not found!");
			}
		});
		
		resetButton.setBounds(225, 200, 100, 25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(e -> {
			userIDField.setText("");
			userPasswordField.setText("");
			messageLabel.setText("");
		});
		
		signupButton.setBounds(225, 250, 100, 25);
		signupButton.setFocusable(false);
		signupButton.addActionListener(e -> {
			frame.dispose();
			manager.displaySignUpPage(manager);
		});
		
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(resetButton);
		frame.add(signupButton);
		
		frame.setTitle("Login Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public void displaySignUpPage(ProfileManager manager) {
		JFrame frame = new JFrame();
		JButton signupButton = new JButton("SignUp");
		JButton cancelButton = new JButton("Cancel");
		JButton resetButton = new JButton("Reset");
		JTextField userIDField = new JTextField();
		JPasswordField userPasswordField = new JPasswordField();
		JTextField firstNameField = new JTextField();
		JTextField lastNameField = new JTextField();
		
		JLabel userIDLabel = new JLabel("userID:");
		JLabel userPasswordLabel = new JLabel("password:");
		JLabel firstNameLabel = new JLabel("First Name:");
		JLabel lastNameLabel = new JLabel("Last Name:");
		JLabel chooseImageLabel = new JLabel("Choose Image:");
		JLabel chooseStatusLabel = new JLabel("Choose Status:");
		JLabel messageLabel = new JLabel("");
		
		ImageIcon image1 = new ImageIcon(getClass().getResource("images/icon1.png"));
		ImageIcon image2 = new ImageIcon(getClass().getResource("images/icon2.png"));
		ImageIcon image3 = new ImageIcon(getClass().getResource("images/icon3.png"));
		ImageIcon image4 = new ImageIcon(getClass().getResource("images/icon4.png"));
		String str1 = new String("none");
		String str2 = new String("single");
		String str3 = new String("married");
		
		ImageIcon[] images = {image1, image2, image3, image4};
		String[] status = {str1, str2, str3};
		
		JComboBox imageCombo = new JComboBox(images);
		JComboBox statusCombo = new JComboBox(status);
		
		userIDLabel.setBounds(70, 50, 90, 25);
		userPasswordLabel.setBounds(50, 100, 90, 25);
		firstNameLabel.setBounds(45, 150, 90, 25);
		lastNameLabel.setBounds(45, 200, 90, 25);
		chooseImageLabel.setBounds(25, 300, 90, 25);
		chooseStatusLabel.setBounds(25, 250, 90, 25);
		messageLabel.setBounds(125, 20, 250, 35);
		messageLabel.setFont(new Font(null, Font.ITALIC, 20));
		
		userIDField.setBounds(125, 50, 200, 25);
		userPasswordField.setBounds(125, 100, 200, 25);
		firstNameField.setBounds(125, 150, 200, 25);
		lastNameField.setBounds(125, 200, 200, 25);
		
		
		
		imageCombo.setBounds(125, 300, 120, 120);
		imageCombo.setFocusable(false);
		statusCombo.setBounds(125, 250, 120, 25);
		statusCombo.setFocusable(false);
		
		signupButton.setBounds(250, 300, 100, 25);
		signupButton.setFocusable(false);
		signupButton.addActionListener(e -> {
			if(userIDField.getText().equals("") || String.valueOf(userPasswordField.getPassword()).equals("") 
					|| firstNameField.getText().equals("") || lastNameField.getText().equals("")) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Information incomplete!");
			}else if(IDandPassword.containsKey(userIDField.getText())) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("userID already exist!");
			}else {
				frame.dispose();
				IDandImage.putIDandImage(userIDField.getText(), (ImageIcon)imageCombo.getSelectedItem());
				IDandName.putIDandName(userIDField.getText(), firstNameField.getText() 
						+ " " + lastNameField.getText());
				IDandPassword.putIDandPassword(userIDField.getText(), 
						String.valueOf(userPasswordField.getPassword()));
				ProfilePage newProfile = new ProfilePage(userIDField.getText(), 
						String.valueOf(userPasswordField.getPassword()), firstNameField.getText() 
						+ " " + lastNameField.getText(), (ImageIcon)imageCombo.getSelectedItem(), 
						(String)statusCombo.getSelectedItem());
				IDandProfile.putIDandProfile(userIDField.getText(), newProfile);
				IDandStatus.putIDandStatus(userIDField.getText(), (String)statusCombo.getSelectedItem());
				profileList.add(newProfile);
				manager.addProfile(newProfile);
				manager.displayLoginPage(manager);
			}
		});
		
		cancelButton.setBounds(250, 350, 100, 25);
		cancelButton.setFocusable(false);
		cancelButton.addActionListener(e -> {
			frame.dispose();
			manager.displayLoginPage(manager);
		});
		
		resetButton.setBounds(250, 400, 100, 25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(e -> {
			userIDField.setText("");
			userPasswordField.setText("");
			firstNameField.setText("");
			lastNameField.setText("");
			messageLabel.setText("");
		});
		
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		
		frame.add(messageLabel);
		frame.add(firstNameLabel);
		frame.add(lastNameLabel);
		frame.add(chooseImageLabel);
		frame.add(chooseStatusLabel);
		
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(firstNameField);
		frame.add(lastNameField);
		frame.add(signupButton);
		frame.add(cancelButton);
		frame.add(resetButton);
		
		frame.add(imageCombo);
		frame.add(statusCombo);
		
		frame.setTitle("SignUp Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public JPanel createEachUserPanel(String ID, ProfilePage p) {
		imgLabel = new JLabel(p.getProfilePicture());
    	imgLabel.setBounds(5, 5, 100, 100);
    	nLabel= new JLabel("Name: " + p.getName());
    	nLabel.setBounds(120, 15, 150, 25);
    	sLabel = new JLabel("Status: " + p.getStatus());
    	sLabel.setBounds(120, 35, 150, 25);
    	
    	JButton addButton;
    	JButton removeButton;
    	
    	addButton = new JButton("+ Add Friend");
    	removeButton = new JButton("- Remove Friend");
    	addButton.setBounds(380, 20, 140, 40);
    	addButton.addActionListener(e -> {
    		createFriendship(IDandProfile.get(ID), p);
    		addButton.setEnabled(false);
    		removeButton.setEnabled(true);
    	});
    	removeButton.setBounds(380, 70, 140, 40);
    	removeButton.addActionListener(e -> {
    		removeFriendship(IDandProfile.get(ID), p);
    		addButton.setEnabled(true);
    		removeButton.setEnabled(false);
    	});
    	
    	boolean friendConfirm = checkFriendship(IDandProfile.get(ID), p);
    	if(friendConfirm) {
    		addButton.setEnabled(false);
    		removeButton.setEnabled(true);
    	}else {
    		addButton.setEnabled(true);
    		removeButton.setEnabled(false);
    	}
    	
    	eachUserPanel = new JPanel();
    	eachUserPanel.setPreferredSize(new Dimension(540, 120));
    	eachUserPanel.setLayout(null);
		eachUserPanel.add(imgLabel);
    	eachUserPanel.add(nLabel);
    	eachUserPanel.add(sLabel);
    	eachUserPanel.add(addButton);
    	eachUserPanel.add(removeButton);
		eachUserPanel.setBackground(Color.gray);
		eachUserPanel.setBorder(blackline);
		return eachUserPanel;
	}
	
	public Iterable<ProfilePage> getAllUser() {
		return allProfiles.getAllVertices();
	}
} // end ProfileManager
