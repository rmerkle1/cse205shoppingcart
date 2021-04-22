package application;

//import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
//		WebView myWebView = new WebView();
//		WebEngine engine = myWebView.getEngine();
		
		String homeURL = this.getClass().getResource("GUI/home.html").toString();
//		engine.load(home.toString());
		
//		VBox root = new VBox();
//		root.getChildren().addAll(myWebView);
		
//		Scene scene = new Scene(root, 900, 600);
//		stage.setScene(scene);
		
//		stage.show();
		
		try {
			stage.setScene(MainController.init(homeURL));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}