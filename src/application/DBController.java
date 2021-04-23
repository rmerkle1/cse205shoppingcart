package application;

public class DBController {
	
	//placeholders for PostgreSQL username and password
	public static String PSQLusername = "makingbad";
	public static String PSQLpassword = "cse205";
	public static String databaseName = "finalproject";
	
	private static String userID, acctType, fname, lname, address, email;
	private static double balance;
	
	public static void initializeUserTable(){
		try {
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + databaseName, PSQLusername, PSQLpassword);
			stmt = c.createStatement();
			
			
			String sql = "CREATE TABLE IF NOT EXISTS USERS" + 
					"(USERID SERIAL PRIMARY KEY," + 
					" USERTYPE TEXT NOT NULL," + 
					" LNAME TEXT NOT NULL," + 
					" FNAME TEXT NOT NULL," + 
					" ADDRESS CHAR(50) NOT NULL," + 
					" EMAIL CHAR(50) NOT NULL," + 
					" BALANCE REAL NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			
			System.out.println("Table created...");
			
		}catch (Exception e){
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":" + e.getMessage());
			System.exit(0);
		}
	}
	
	public static String getUserInfoByID(String uID) {
//		run SQL query:
//			select username, acctType, fname, lname, email, balance
//				from Users
//				where userID = uID;
		
//		convert query results to a string delimited by commas

		try {
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + databaseName, PSQLusername, PSQLpassword);
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * from USERS where USERID = '" + uID + "';");
			while (rs.next()) {

				this.userID = rs.getInt("USERID");
				this.fname = rs.getString("FNAME");
				this.lname = rs.getString("LNAME");
				this.acctType = rs.getString("ACCTTYPE");
				this.address = rs.getString("ADDRESS");
				this.email = rs.getString("EMAIL");
				this.balance = rs.getDouble("BALANCE");
			}
			
			rs.close();
			stmt.close();
			c.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":" + e.getMessage());
			System.exit(0);
		}
		System.out.println("got user info");
		
		String result = userID + "," + fname + "," + lname + "," + acctType + "," + address + "," + email + "," + balance;
		
		/*
		String resultM = "manager1,Manager,Saul,G,lawyer@gmail.com,100.00";
		String resultB = "buyer1,Buyer,Jesse,P,capncook@gmail.com,20.01";
		String resultS = "seller1,Seller,Walter,W,heisenberg@gmail.com,15000000.00";
		*/
		
		return result;
	}

	public static String getUserIDByUsernameAndPassword(String username, String password) {
//		run SQL query:
//			select userID
//				from Users
//				where username = username and password = password;
		
//		query results should already be formatted as a String
//		if not found then return null
		
		try {
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + databaseName, PSQLusername, PSQLpassword);
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT USERID from USERS where USERNAME = '" + username + "' AND PASSWORD = '" + password + "';");
			while(rs.next()) {
				this.userID = rs.getString("USERID");
			}
			
			rs.close();
			stmt.close();
			c.close();
			
			return userID;
			
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":" + e.getMessage());
			System.exit(0);
		}
		
		return null;

	}
		
		
		
		System.out.println("got UserID");
		/*
		String resultM = "1000000001";
		String resultB = "1000000002";
		String resultS = "1000000003";
		*/
	}

	public static boolean usernameExists(String uname) {
//		run SQL query:
//			select username
//				from Users
//				where username = uname;
		
//		if not found then return true, else return false
		try {
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + databaseName, psqlusername, psqlpassword);
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT USERNAME from USERS where USERNAME = '" + uname + "';");
			if (rs.next()) 
				System.out.println("Username does exist!");
				return true;
			
			rs.close();
			stmt.close();
			c.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":" + e.getMessage());
			System.exit(0);
		}
		System.out.println("Username doesn't exist...");
		return false;

		/*
		String results = null;
		return (results == null) ? false : true;
		*/
	}
	
	public static void createUser(String acctType, String fname, String lname, String address, String email, String username, String password) {
//		generate unique userID (might be able to do this automatically with SQL, needs more research)
		// Use the "Serial" command, autoincrements an ID and treats it as a String/int.
		
//		set balance to zero
		
		balance = 0.00;
		try {
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + databaseName, PSQLusername, PSQLpassword);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			String sql = "INSERT INTO USERS(ACCTTYPE,FNAME,LNAME,ADDRESS,EMAIL,USERNAME,PASSWORD,BALANCE)"
					+ "VALUES('" + acctType + "','" + fname + "','" + lname + "','" + address + "','" + email + "','" + username + "','" + password + "'," + balance + ");";
			stmt.executeUpdate(sql);
			
			
			stmt.close();
			c.commit();
			c.close();

		}catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":" + e.getMessage());
			System.exit(0);
		}
		
//		run SQL query:
//			insert into Users(UserID, acctType, fname, lname, email, username, password, balance)
//				values (uID, type, fname, lname, email, username, password, balance);
		
//		This method should return nothing.
	}
	
	
}
