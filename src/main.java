import main.AskingUser;
import main.MapFrame;

public class main {

	public static void main(String[] args) {

		/*
		 * THIS CODE IS MY OWN WORK. I DID NOT CONSULT TO ANY PROGRAM WRITTEN BY OTHER
		 * STUDENTS. I READ AND FOLLOWED THE GUIDELINE GIVEN IN THE PROGRAMMING
		 * ASSIGNMENT. NAME: SARIEH SRDAR ID:0066940
		 * 
		 */

		// getting data from user
		AskingUser askUser = new AskingUser();
		askUser.addScreen();

		// create the map according to users input
		MapFrame map = new MapFrame(16, askUser);
	}

}
