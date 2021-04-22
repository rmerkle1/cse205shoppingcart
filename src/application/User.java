package application;

import java.util.ArrayList;

public class User {
	private String uID, username, type, fname, lname, email;
	private double balance;
	private ArrayList<Item> shoppingCart;
	
	public User(String uID) {
		System.out.println("user constructor flag");
		this.uID = uID;
//		Get user info from Users data table
		String info = DBController.getUserInfoByID(uID);
//		Parse user info String to get data for user
		int commacursor_prev = 0;
		int commacursor_next = info.indexOf(",", commacursor_prev);
		this.username = info.substring(commacursor_prev, commacursor_next);

		commacursor_prev = commacursor_next + 1;
		commacursor_next = info.indexOf(",", commacursor_prev);
		this.type = info.substring(commacursor_prev, commacursor_next);

		commacursor_prev = commacursor_next + 1;
		commacursor_next = info.indexOf(",", commacursor_prev);
		this.fname = info.substring(commacursor_prev, commacursor_next);

		commacursor_prev = commacursor_next + 1;
		commacursor_next = info.indexOf(",", commacursor_prev);
		this.lname = info.substring(commacursor_prev, commacursor_next);

		commacursor_prev = commacursor_next + 1;
		commacursor_next = info.indexOf(",", commacursor_prev);
		this.email = info.substring(commacursor_prev, commacursor_next);

		commacursor_prev = commacursor_next + 1;
		this.balance = Double.parseDouble(info.substring(commacursor_prev));
		
		shoppingCart = new ArrayList<Item>();
	}
	
	public String getuID() {
		return uID;
	}

	public String getUsername() {
		return username;
	}

	public String getType() {
		return type;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getEmail() {
		return email;
	}

	public double getBalance() {
		return balance;
	}

	public ArrayList<Item> getShoppingCart() {
		return shoppingCart;
	}

	public String toString() {
		return uID +" "+ username +" "+ type +" "+ fname +" "+ lname +" "+ email +" "+ balance;
	}
}
