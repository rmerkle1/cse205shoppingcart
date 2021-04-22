package application;

//import java.net.URL;
//import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

//import javafx.fxml.Initializable;

public class MainController /*implements Initializable*/ {
	private static WebView myWebView = new WebView();
	private static final WebEngine engine = myWebView.getEngine();
	private static String homepage;
	
	public static Scene init(String homeURL) {
		homepage = homeURL;
		engine.load(homepage);
//		engine.load("https://www.google.com");
		
		bridgeJS();
		
		VBox root = new VBox(myWebView);
		VBox.setVgrow(myWebView, Priority.ALWAYS);
		
		return new Scene(root, 1200, 800);
	}
	
//	private static void bridgeJS() {
//		engine.getLoadWorker().stateProperty().addListener(
//			new ChangeListener<Object>() {
//				@Override
//				public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
//					if (newValue != Worker.State.SUCCEEDED)
//						return;
//					
//					JSObject jso = (JSObject) engine.executeScript("window");
//					jso.setMember("myApp", new ControllerMethods());
//				}
//			}
//		);
//		System.out.println("JavaScript should be bridged");
//		engine.reload();
//	}
	
	private static void bridgeJS() {
		final ControllerMethods controller = new ControllerMethods();
		engine.setJavaScriptEnabled(true);
		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
			@Override
			public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
				final JSObject jso = (JSObject) engine.executeScript("window");
				jso.setMember("myApp", controller);
			}
		});
	}
	
	public static class ControllerMethods {
//		private boolean loginStatus = false;
		private User current = null;
		
		public void refreshPage() {
			System.out.println("refreshed");
			engine.reload();
		}
		
		public void exit() {
			System.out.println("exited");
			Platform.exit();
		}
		
		public String test(int n) {
			return n + "test";
		}
		
		public String login(String loginInfo) {
			System.out.println(loginInfo);
			
			String username = login_parseUsername(loginInfo);
			String password = login_parsePassword(loginInfo);
			
//			Query Users data table for username and password, if found log them in, if not return improper credentials
			login(username, password);
			engine.reload();
			
			return username + password;
		}
		private String login_parseUsername(String info) {
			int begindex = info.indexOf("username=") + 9;
			System.out.print(begindex + " ");
			int endindex = info.indexOf("&password=");
			System.out.println(endindex);
			return info.substring(begindex,endindex);
		}
		private String login_parsePassword(String info) {
			int begindex = info.indexOf("password=") + 9;
			System.out.println(begindex);
			return info.substring(begindex);
		}
		
		public String signup(String signupInfo) {
			System.out.println(signupInfo);
			
			String acctType = signup_parseAccountType(signupInfo);
			String fname = signup_parseFname(signupInfo);
			String lname = signup_parseLname(signupInfo);
			String email = signup_parseEmail(signupInfo);
			String username = signup_parseUsername(signupInfo);
			String password = signup_parsePassword(signupInfo);
			
//			Check if username already exists in database.
			if (DBController.usernameExists(username))
				return "Username has already been taken.";
//			Create a user in the database with these credentials.
			DBController.createUser(acctType, fname, lname, email, username, password);
			
			login(username, password);
			
			engine.load(homepage);
			
			return acctType + email + username + password;
		}
		private String signup_parseAccountType(String info) {
			int begindex = info.indexOf("acctType=") + 9;
			System.out.println(begindex);
			int endindex = info.indexOf("&email=");
			return info.substring(begindex, endindex);
		}
		private String signup_parseFname(String info) {
			int begindex = info.indexOf("&fname=") + 7;
			int endindex = info.indexOf("&lname=");
			return info.substring(begindex, endindex);
		}
		private String signup_parseLname(String info) {
			int begindex = info.indexOf("&lname=") + 7;
			int endindex = info.indexOf("&email=");
			return info.substring(begindex, endindex);
		}
		private String signup_parseEmail(String info) {
			int begindex = info.indexOf("&email=") + 7;
			int endindex = info.indexOf("&username=");
			return info.substring(begindex, endindex);
		}
		private String signup_parseUsername(String info) {
			int begindex = info.indexOf("&username=") + 10;
			int endindex = info.indexOf("&password=");
			return info.substring(begindex, endindex);
		}
		private String signup_parsePassword(String info) {
			int begindex = info.indexOf("&password=") + 10;
			return info.substring(begindex);
		}
		
		private void login(String username, String password) {
			current = new User(DBController.getUserIDByUsernameAndPassword(username, password));
			System.out.println("current user: " + current.toString());
		}
		
		public boolean checkLoginStatus() {return (current != null);}
		
		public String getCurrentUsername() {return current.getUsername();}
		public String getCurrentType() {return current.getType();}
		public String getCurrentFname() {return current.getFname();}
		public String getCurrentLname() {return current.getLname();}
		public String getCurrentEmail() {return current.getEmail();}
		public double getCurrentBalance() {return current.getBalance();}
		
	}
}
