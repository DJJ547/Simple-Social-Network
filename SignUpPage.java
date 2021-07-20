import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpPage implements ActionListener{
	
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
	JLabel messageLabel = new JLabel("");
	
	ImageIcon image1 = new ImageIcon(getClass().getResource("images/icon1.png"));
	ImageIcon image2 = new ImageIcon(getClass().getResource("images/icon2.png"));
	ImageIcon image3 = new ImageIcon(getClass().getResource("images/icon3.png"));
	ImageIcon image4 = new ImageIcon(getClass().getResource("images/icon4.png"));
	ImageIcon[] images = {image1, image2, image3, image4};
	JComboBox combo = new JComboBox(images);
	
	HashMap<String, String> loginInfo = new HashMap<String, String>();
	HashMap<String, ImageIcon> accountInfo = new HashMap<String, ImageIcon>();
	HashMap<String, String> nameInfo = new HashMap<String, String>();
	
	UndirectedGraph graph = new UndirectedGraph();
	
	public SignUpPage(HashMap<String, String> loginInfoOriginal, HashMap<String, ImageIcon> 
	accountInfoOriginal, HashMap<String, String> nameInfoOriginal, UndirectedGraph ug) {
		loginInfo = loginInfoOriginal;
		accountInfo = accountInfoOriginal;
		nameInfo = nameInfoOriginal;
		
		graph = ug;
		
		userIDLabel.setBounds(70, 50, 90, 25);
		userPasswordLabel.setBounds(50, 100, 90, 25);
		firstNameLabel.setBounds(45, 150, 90, 25);
		lastNameLabel.setBounds(45, 200, 90, 25);
		chooseImageLabel.setBounds(25, 250, 90, 25);
		messageLabel.setBounds(125, 20, 250, 35);
		messageLabel.setFont(new Font(null, Font.ITALIC, 20));
		
		userIDField.setBounds(125, 50, 200, 25);
		userPasswordField.setBounds(125, 100, 200, 25);
		firstNameField.setBounds(125, 150, 200, 25);
		lastNameField.setBounds(125, 200, 200, 25);
		
		combo.setBounds(125, 250, 120, 120);
		
		signupButton.setBounds(250, 240, 100, 25);
		signupButton.setFocusable(false);
		signupButton.addActionListener(this);
		
		cancelButton.setBounds(250, 290, 100, 25);
		cancelButton.setFocusable(false);
		cancelButton.addActionListener(this);
		
		resetButton.setBounds(250, 340, 100, 25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		
		frame.add(messageLabel);
		frame.add(firstNameLabel);
		frame.add(lastNameLabel);
		frame.add(chooseImageLabel);
		
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(firstNameField);
		frame.add(lastNameField);
		frame.add(signupButton);
		frame.add(cancelButton);
		frame.add(resetButton);
		
		frame.add(combo);
		
		frame.setTitle("SignUp Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	private String nameConvert(String first, String last) {
		return first + " " + last;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == signupButton) {
			if(userIDField.getText().equals("") || userPasswordField.getText().equals("") 
					|| firstNameField.getText().equals("") || lastNameField.getText().equals("") 
					|| loginInfo.containsKey(userIDField.getText())) {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Information Incomplete!");
			}else {
				String userID = userIDField.getText();
				String password = String.valueOf(userPasswordField.getPassword());
				ImageIcon img = (ImageIcon)combo.getSelectedItem();
				loginInfo.put(userID, password);
				accountInfo.put(userID, img);
				//convert first name and last name to a whole name here
				String name = nameConvert(firstNameField.getText(), lastNameField.getText());
				nameInfo.put(userID, name);
				graph.addVertex(userID);
				frame.dispose();
				LoginPage lg = new LoginPage(loginInfo, accountInfo, nameInfo, graph);
			}
		}
		
		if(e.getSource() == cancelButton) {
			LoginPage lg = new LoginPage(loginInfo, accountInfo, nameInfo, graph);
			frame.dispose();
		}
		
		if(e.getSource() == resetButton) {
			userIDField.setText("");
			userPasswordField.setText("");
			firstNameField.setText("");
			lastNameField.setText("");
			messageLabel.setText("");
		}
		
	}
	
}
