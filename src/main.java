import main.AskingUser;
import main.MapFrame;

public class main {

	public static void main(String[] args) {

		// getting data from user
		AskingUser askUser = new AskingUser();
		askUser.addScreen();

		// create the map according to users input
		MapFrame map = new MapFrame(14, askUser);
	}

}
