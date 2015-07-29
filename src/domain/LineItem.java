package domain;
import java.util.Date;
/**
 * @author sreerekhadeb
 *
 */
public class LineItem {
	
	private String itemName;
	private String itemDescription;
	private String status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
