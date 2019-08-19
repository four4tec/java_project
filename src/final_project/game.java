package final_project;

import java.util.Timer;

public class game {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timer main_timer = new Timer();
		main_timer.schedule(new frame(), 0, 1000 / 20);

	}
}
