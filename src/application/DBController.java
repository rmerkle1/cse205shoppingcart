package application;

public class DBController {
	public static String getUserInfoByID(String uID) {
//		run SQL query:
//			select username, acctType, fname, lname, email, balance
//				from Users
//				where userID = uID;
		
//		convert query results to a string delimited by commas
		System.out.println("got user info");
		
		String resultM = "manager1,Manager,Saul,G,lawyer@gmail.com,100.00";
		String resultB = "buyer1,Buyer,Jesse,P,capncook@gmail.com,20.01";
		String resultS = "seller1,Seller,Walter,W,heisenberg@gmail.com,15000000.00";
		
		return resultB;
	}

	public static String getUserIDByUsernameAndPassword(String username, String password) {
//		run SQL query:
//			select userID
//				from Users
//				where username = username and password = password;
		
//		query results should already be formatted as a String
//		if not found then return null
		System.out.println("got UserID");
		
		String resultM = "1000000001";
		String resultB = "1000000002";
		String resultS = "1000000003";
		
		return resultB;
	}

	public static boolean usernameExists(String uname) {
//		run SQL query:
//			select username
//				from Users
//				where username = uname;
		
//		if not found then return true, else return false
		String results = null;
		return (results == null) ? false : true;
	}
	
	public static void createUser(String type, String fname, String lname, String email, String username, String password) {
//		generate unique userID (might be able to do this automatically with SQL, needs more research)
		
//		set balance to zero
		double balance = 0.00;
		
//		run SQL query:
//			insert into Users(UserID, acctType, fname, lname, email, username, password, balance)
//				values (uID, type, fname, lname, email, username, password, balance);
		
//		This method should return nothing.
	}
	
	
}
