package default_package;

import controller.MainController;
import view.MainView;

public class App {

	public static void main(String[] args) {
		MainView view = new MainView();
		MainController controller = new MainController(view);

	}

}
