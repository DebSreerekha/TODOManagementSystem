package domain;


import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;

public class ToDoList implements Serializable{

	private int id;
	private String listName;
	private Map<String,LineItem> items;
	private Date timeStamp;

	public ToDoList() {
		items = new HashMap<String,LineItem>();
		timeStamp = new Date();
	}

	public ToDoList(String listName) {
		items = new HashMap<String,LineItem>();
		this.listName = listName ;
		timeStamp = new Date();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public LineItem getLineItem(String key) {

		Collection<LineItem> values = items.values();
		LineItem  item = null;
		Iterator<LineItem>  iterator = values.iterator();
		while(iterator.hasNext()) {
			LineItem tempItem = iterator.next();
			String tempKey = tempItem.getItemName();
			if (tempKey.equals(key)){
				item = tempItem;
				break;
			}
		}
		return item;
	}

	/**
	 * update the line item with the specified key
	 */
	public void updateLineItem(String itemName, String description) {
		LineItem item = getLineItem(itemName);
		item.setItemDescription(description);
	}
	/**
	 * update the line item with the specified value
	 * @param item
	 */

	public  void updateLineItem(LineItem item) {
		items.put(item.getItemName(),item);
		
	}
	/**
	 *  add item to the list
	 * 
	 * @param item
	 */
	
	public void addLineItem(LineItem item) {
		this.items.put(item.getItemName(), item);
	}
	/**
	 * Constructs an new LineItem object and adds it to the list
	 * 
	 * @param itemName
	 * @param description
	 */
	public void addLineItem(String itemName,String description) {
		items.put(itemName,new LineItem(itemName,description));
	}
	/**
	 * Get the list name
	 * @return string
	 */
	public String getListName() {
		return listName;
	}
	/**
	 * Set the list name
	 * @param listName
	 */
	public void setListName(String listName) {
		this.listName = listName;
	}
	/**
	 * Return the list of items
	 * @return
	 */
	public Map<String, LineItem> getItems() {
		return items;
	}

	/**
	 * Set the items in the list 
	 * @param items
	 */
	public void setItems(Map<String,LineItem> items) {
		this.items = items;
	}

	/** 
	 * Delete an item from the list
	 * @param itemName
	 */
	public void deleteItem(String itemName) {
		items.remove(itemName);
	}
	
	/**
	 * Displays the items in the list on the console
	 * 
	 */
	public void viewItemsInTheList(String listname) {

		Collection<LineItem> values = items.values();
		Iterator<LineItem>  iterator = values.iterator();
		
		System.out.println("TODOList name :" + this.getListName()) ;
		System.out.println("Printing the contents ....");
		
		System.out.println("****************************************************");
		System.out.println(" name 		Description		Status		Timestamp  ");
		System.out.println("****************************************************");
		
		while(iterator.hasNext()){
			LineItem item = (LineItem) iterator.next();
			System.out.println(item.getItemName() + "\t"+item.getItemDescription()+"\t" +item.getStatus()+"\t"+item.getTimeStamp());
		}
	}
	
}
