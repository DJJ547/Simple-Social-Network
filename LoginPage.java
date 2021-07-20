import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	JButton signupButton = new JButton("SignUp");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("userID:");
	JLabel userPasswordLabel = new JLabel("password:");
	JLabel messageLabel = new JLabel();
	
	HashMap<String, String> loginInfo = new HashMap<String, String>();
	HashMap<String, ImageIcon> accountInfo = new HashMap<String, ImageIcon>();
	HashMap<String, String> nameInfo = new HashMap<String, String>();
	
	UndirectedGraph graph = new UndirectedGraph();
	
	public LoginPage(HashMap<String, String> loginInfoOriginal, HashMap<String, ImageIcon> 
	accountInfoOriginal, HashMap<String, String> nameInfoOriginal, UndirectedGraph pm){
		loginInfo = loginInfoOriginal;
		accountInfo = accountInfoOriginal;
		nameInfo = nameInfoOriginal;
		
		graph = pm;
		
		userIDLabel.setBounds(50, 100, 75, 25);
		userPasswordLabel.setBounds(50, 150, 75, 25);
		messageLabel.setBounds(125, 170, 250, 35);
		messageLabel.setFont(new Font(null, Font.ITALIC, 20));
		
		userIDField.setBounds(125, 100, 200, 25);
		userPasswordField.setBounds(125, 150, 200, 25);
		
		loginButton.setBounds(125, 200, 100, 25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		
		resetButton.setBounds(225, 200, 100, 25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		signupButton.setBounds(225, 250, 100, 25);
		signupButton.setFocusable(false);
		signupButton.addActionListener(this);
		
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
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == resetButton) {
			userIDField.setText("");
			userPasswordField.setText("");
			messageLabel.setText("");
		}
		
		if(e.getSource() == loginButton){
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			if(loginInfo.containsKey(userID)) {
				if(loginInfo.get(userID).equals(password)) {
					messageLabel.setForeground(Color.green);
					messageLabel.setText("Login successful");
					frame.dispose();
					ProfilePage p = new ProfilePage(userID, loginInfo, accountInfo, nameInfo, graph);
				}else {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("wrong password!");
				}
			}else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("username not found!");
			}
		}
		
		if(e.getSource() == signupButton) {
			frame.dispose();
			SignUpPage signup = new SignUpPage(loginInfo, accountInfo, nameInfo, graph);
		}
	}
}
