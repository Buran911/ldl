package application;


public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		App app = new App(args);
		app.readFiles(); 
		app.checkErrors();
		app.generateEQ();
		app.makeQuery();
		app.writeYAML();
	}

}
