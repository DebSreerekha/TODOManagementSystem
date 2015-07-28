package domain;


import java.util.Date;

public class LineItem {
	
	private String itemName;
	private String itemDescription;
	private int status;
	private Date timeStamp;
	       
	public LineItem(String key, String value) {
		
		this.itemName = key;
		this.itemDescription = value;
		this.status = Constants.STATUS_ADDED ;
		this.timeStamp = new Date();
				
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
