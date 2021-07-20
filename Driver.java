import javax.swing.ImageIcon;

public class Driver {
	public static void main(String[] args) {
		IDandPasswords idp = new IDandPasswords();
		accountAndImage ai = new accountAndImage();
		IDandName in = new IDandName();
		
		UndirectedGraph graph = new UndirectedGraph();
		//ProfileManager manager = new ProfileManager();
		LoginPage login = new LoginPage(idp.getLoginInfo(), ai.getLoginInfo(),in.getLoginInfo(),graph);
		//ProfilePage pp = new ProfilePage("123", idp.getLoginInfo(), ai.getLoginInfo(), in.getLoginInfo(), graph);
	} // end main
} // end Drive