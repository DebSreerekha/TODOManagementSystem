package view;

import domain.Constants;
/**
 * This class encapsulates the user input object.
 * 
 * @author sreerekhadeb
 *
 */
public class UserInput {
	private int choice ;
	private String listName;
	private String itemName;
	private String itemDescription;
	private String itemStatus ;
	
	
	public UserInput(String choice, String listName, String itemName ,String itemDescription) {

		this.choice = Integer.parseInt(choice) ;
		this.itemName = itemName ;
		this.listName = listName ;
		this.itemDescription = itemDescription;
		this.itemStatus = Constants.STATUS_ADDED ;
	}
	
	public UserInput(String choice,String listName,String itemName,String status,int mode ) {
		if(mode ==7) {
			this.choice = Integer.parseInt(choice) ;
			this.itemName = itemName ;
			this.listName = listName ;
			this.itemStatus = status ;
		}
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String  getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String status) {
		this.itemStatus = status ;
	}
}
