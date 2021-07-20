import java.util.HashMap;

import javax.swing.ImageIcon;

public class accountAndImage {
	HashMap<String, ImageIcon> accountInfo = new HashMap<String, ImageIcon>();

	public accountAndImage(){
		ImageIcon image1 = new ImageIcon(getClass().getResource("images/icon1.png"));
		accountInfo.put("123", image1);
	}
	
	protected HashMap getLoginInfo() {
		return accountInfo;
	}
}
