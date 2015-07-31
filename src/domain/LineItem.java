package domain;
import java.util.Date;
/**
 * @author sreerekhadeb
 *
 */
public class LineItem {
	/**
	 * unique name of the item to be added
	 */
	private String itemName;
	/**
	 * item description
	 */
	private String itemDescription;
	/**
	 * item status
	 */
	private String status;
	/**
	 * date when the item was created.
	 */
	private Date timeStamp;

	/**
	 * Public constructor.
	 * @param key
	 * @param value
	 */

	public LineItem(String key, String value) {
		
		this.itemName = key;
		this.itemDescription = value;
		this.status = Constants.STATUS_ADDED ;
		this.timeStamp = new Date();
				
	}

	/**
	 * returns the item name
	 * @return
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * Sets the item name
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Returns the item description
	 * @return
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * Sets the item description
	 * @param itemDescription
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * Returns  the status
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * returns the date when the object was created
	 * @return
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}
	/**
	 * sets the date when the object was created
	 */
	public void setTimeStamp(Date timeStamp){
		this.timeStamp = timeStamp ;
	}


}
