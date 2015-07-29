package view;

import domain.Constants;

public class UserInput {
	private int choice ;
	private String listname;
	private String itemname;
	private String itemDescription;
	private int itemStatus ;
	
	
	public UserInput(String choice, String listname, String itemname ,String itemDescription) {
		// TODO Auto-generated constructor stub
		this.choice = Integer.parseInt(choice) ;
		this.itemname = itemname ;
		this.listname = listname ;
		this.itemDescription = itemDescription;
		this.itemStatus = Constants.STATUS_ADDED ;
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public String getListname() {
		return listname;
	}
	public void setListname(String listname) {
		this.listname = listname;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public int  getItemStatus() {
		// TODO Auto-generated method stub
		return itemStatus;
	}
	public void setItemStatus(int status) {
		this.itemStatus = status ;
	}
}
