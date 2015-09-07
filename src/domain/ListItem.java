package domain;
import java.io.Serializable;
import java.util.Date;
/**
 * @author sreerekhadeb
 */
public class ListItem implements Serializable{

	private long id ;

	private String itemName;

	private String itemDescription;

	private String status;

	private Date lastUpdatedAt;

	public ListItem()
	{

	}

	public ListItem(String key, String value) {
		
		this.itemName = key;
		this.itemDescription = value;
		this.status = Constants.STATUS_ADDED ;
		this.lastUpdatedAt = new Date();
				
	}
	public ListItem(String key,String value,String listName)
	{
		this.itemName = key ;
		this.itemDescription = value;
		this.status = Constants.STATUS_ADDED ;
		this.lastUpdatedAt = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(Date lastUpdatedAt){
		this.lastUpdatedAt = lastUpdatedAt ;
	}

}
