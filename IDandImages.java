import java.util.HashMap;

import javax.swing.ImageIcon;

public class IDandImages {
	private HashMap<String, ImageIcon> IDimage = new HashMap<String, ImageIcon>();

	public IDandImages(){
		
	}
	
	protected HashMap<String, ImageIcon> getLoginInfo() {
		return IDimage;
	}
	
	public void putIDandImage(String ID, ImageIcon image) {
		IDimage.put(ID, image);
	}
}
