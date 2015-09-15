package domain;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;

public class ToDoList implements Serializable{

	private int id;
	private String listName;
	private Map<String,ListItem> items;
	private Date lastUpdatedAt;

	public ToDoList() {
		items = new HashMap<String,ListItem>();
		lastUpdatedAt = new Date();
	}

	public ToDoList(String listName) {
		items = new HashMap<String,ListItem>();
		this.listName = listName ;
		lastUpdatedAt = new Date();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Date getlastUpdatedAt() {
		return lastUpdatedAt;
	}
	public void setlastUpdatedAt(Date lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

	public ListItem getListItem(String key) {

		Collection<ListItem> values = items.values();
		ListItem  item = null;
		Iterator<ListItem>  iterator = values.iterator();
		while(iterator.hasNext()) {
			ListItem tempItem = iterator.next();
			String tempKey = tempItem.getItemName();
			if (tempKey.equals(key)){
				item = tempItem;
				break;
			}
		}
		return item;
	}


	public void updateListItem(String itemName, String description) {
		ListItem item = getListItem(itemName);
		item.setItemDescription(description);
	}

	public  void updateListItem(ListItem item) {
		items.put(item.getItemName(), item);
		
	}

	public void addListItem(ListItem item) {
		this.items.put(item.getItemName(), item);
	}

	public void addListItem(String itemName,String description) {
		items.put(itemName,new ListItem(itemName,description));
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public Map<String, ListItem> getItems() {
		return items;
	}


	public void setItems(Map<String,ListItem> items) {
		this.items = items;
	}


	public void deleteItem(String itemName) {
		items.remove(itemName);
	}

	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(this.id);
		builder.append(this.listName);
		builder.append(this.lastUpdatedAt);
		Collection<ListItem> Values = items.values();
		Iterator iterator = Values.iterator();
		while(iterator.hasNext())
		{
			ListItem item = (ListItem) iterator.next();
			builder.append(item.toString());
		}
		return builder.toString() ;
	}
	
}
