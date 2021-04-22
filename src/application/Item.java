package application;

public class Item {
	private int num;
	private String name;
	private double quantity;
	
	public Item(int num, String name, double quantity) {
		this.num = num;
		this.name = name;
		this.quantity = quantity;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
}
