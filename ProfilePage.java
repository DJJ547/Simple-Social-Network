import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ProfilePage{
	private String userID;
	private String password;
	private String name;
	private String status;
	private ImageIcon picture;
	
	/** Constructor for an instance of a profile. */
	public ProfilePage(String userID, String password, String name, ImageIcon picture, String status) {
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.picture = picture;
		this.status = status;
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
	
	public String getID() {
		return userID;
	}

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
	
	public void setPassword(String password) {
		this.password = password;
	}
	
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
	
	public void displayModifyPage(ProfileManager manager) {
		JFrame frame = new JFrame();
		JLabel newFirstNameLabel = new JLabel("New First Name: ");
		JLabel newLastNameLabel = new JLabel("New Last Name: ");
		JLabel newPasswordLabel = new JLabel("New Password: ");
		JLabel newStatusLabel = new JLabel("New Status: ");
		JLabel newImageLabel = new JLabel("New Image: ");
		JLabel warningLabel = new JLabel("Please leave network after finish to apply changes.");
		
		JTextField newFirstNameField = new JTextField();
		JTextField newLastNameField = new JTextField();
		JPasswordField newPasswordField = new JPasswordField();
		
		ImageIcon image1 = new ImageIcon(getClass().getResource("images/icon1.png"));
		ImageIcon image2 = new ImageIcon(getClass().getResource("images/icon2.png"));
		ImageIcon image3 = new ImageIcon(getClass().getResource("images/icon3.png"));
		ImageIcon image4 = new ImageIcon(getClass().getResource("images/icon4.png"));
		
		String str1 = new String("none");
		String str2 = new String("single");
		String str3 = new String("married");
		
		ImageIcon[] images = {image1, image2, image3, image4};
		String[] status = {str1, str2, str3};
		
		JComboBox newImageCombo = new JComboBox(images);
		JComboBox newStatusCombo = new JComboBox(status);
		
		JButton finishButton = new JButton("Finish");
		JButton cancelButton = new JButton("Cancel");
		
		newFirstNameLabel.setBounds(55, 10, 150, 35);
		newLastNameLabel.setBounds(55, 60, 150, 35);
		newPasswordLabel.setBounds(55, 110, 150, 35);
		newStatusLabel.setBounds(55, 160, 150, 35);
		newImageLabel.setBounds(55, 210, 150, 35);
		warningLabel.setBounds(55, 320, 300, 35);
		
		newFirstNameField.setBounds(220, 10, 150, 35);
		newLastNameField.setBounds(220, 60, 150, 35);
		newPasswordField.setBounds(220, 110, 150, 35);
		newStatusCombo.setBounds(220, 160, 150, 35);
		newImageCombo.setBounds(220, 210, 120, 120);
		
		finishButton.setBounds(55, 350, 130, 35);
		finishButton.setFocusable(false);
		finishButton.addActionListener(e -> {
			frame.dispose();
			String name = newFirstNameField.getText() + " " + newLastNameField.getText();
			String pw = String.valueOf(newPasswordField.getPassword());
			String stat = (String)newStatusCombo.getSelectedItem();
			ImageIcon img = (ImageIcon)newImageCombo.getSelectedItem();
			setName(name);
			setPassword(pw);
			setStatus(stat);
			setProfilePicture(img);
			
			manager.modifyPageChange(userID, name, pw, stat, img);
		});
		
		cancelButton.setBounds(250, 350, 130, 35);
		cancelButton.setFocusable(false);
		cancelButton.addActionListener(e -> {
			frame.dispose();
			
		});
		
		frame.add(newFirstNameLabel);
		frame.add(newLastNameLabel);
		frame.add(newPasswordLabel);
		frame.add(newImageLabel);
		frame.add(newStatusLabel);
		frame.add(warningLabel);
		frame.add(newFirstNameField);
		frame.add(newLastNameField);
		frame.add(newPasswordField);
		frame.add(newImageCombo);
		frame.add(newStatusCombo);
		frame.add(finishButton);
		frame.add(cancelButton);
		
		frame.setTitle("Modify Profile");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 450);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public void displayProfilePage(ProfileManager manager) {
		JFrame frame;
		JLabel ImageLabel;
		JLabel titleLabel = new JLabel("Your Profile");
		JLabel nameLabel;
		JLabel statusLabel = new JLabel("Status: " + status);
		JLabel friendAmountLabel = new JLabel("# of friends: 0");
		JLabel searchLabel = new JLabel("Name Search: ");
		
		
		JPanel usersPanel = new JPanel();
		
		JScrollPane scrollPane;
		
		JButton leaveButton = new JButton("Leave Network");
		JButton modifyButton = new JButton("Modify Profile");
		JButton searchButton = new JButton("Search");
		
		JTextField nameSearchField = new JTextField();
		frame = new JFrame();
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
		leaveButton.addActionListener(e -> {
			frame.dispose();
			manager.displayLoginPage(manager);
		});
		modifyButton.setBounds(30, 350, 130, 35);
		modifyButton.setFocusable(false);
		modifyButton.addActionListener(e -> {
			displayModifyPage(manager);
		});
		searchButton.setBounds(570, 400, 130, 30);
		searchButton.setFocusable(false);
		searchButton.addActionListener(e -> {
			
		});
		
		int y = 120;
		usersPanel.setBounds(200, 30, 550, y);
		usersPanel.setBackground(Color.LIGHT_GRAY);
		usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
		usersPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		
		for(ProfilePage p: manager.getAllUser()) {
			if(p.getID().equals(userID)) {
				//do nothing if the user is myself
			}else {
				usersPanel.add(manager.createEachUserPanel(userID, p));
				y = y + 120;
				usersPanel.setBounds(200, 30, 550, y);
			}
		}
		scrollPane = new JScrollPane(usersPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
				   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(200, 30, 550, 350);
		
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
		frame.add(scrollPane);
		
		frame.setTitle(name + "'s Profile Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
	}
} // end ProfilePage