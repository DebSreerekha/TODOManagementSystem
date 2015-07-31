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
	private String listname;
	private String itemname;
	private String itemDescription;
	private String itemStatus ;
	
	
	public UserInput(String choice, String listname, String itemname ,String itemDescription) {

		this.choice = Integer.parseInt(choice) ;
		this.itemname = itemname ;
		this.listname = listname ;
		this.itemDescription = itemDescription;
		this.itemStatus = Constants.STATUS_ADDED ;
	}
	
	public UserInput(String choice,String listname,String itemname,String status,int mode ) {
		if(mode ==7) {
			this.choice = Integer.parseInt(choice) ;
			this.itemname = itemname ;
			this.listname = listname ;
			this.itemStatus = status ;
		}
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
	public String  getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String status) {
		this.itemStatus = status ;
	}
}
