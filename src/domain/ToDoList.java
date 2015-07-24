package domain;

import java.util.Enumeration;
import java.util.Hashtable;
/**
 * 
 * @author sreerekha
 *
 */
public class ToDoList {
	
	private String listName;
	
	private Hashtable<String,LineItem> items;
	
	public ToDoList(String listName2) {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Fetch the line item with the specified key
	 * @param key
	 * @return
	 */
	public LineItem getLineItem(String key)
	{
		Enumeration<LineItem> enumeration = items.elements();
		LineItem  item = null;
		while(enumeration.hasMoreElements())
		{
			LineItem tempItem = enumeration.nextElement();
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
	public void updateLineItem(String key, String Value)
	{
		LineItem item = getLineItem(key);
		item.setItemDescription(Value);
	}
	/**
	 *  add item
	 * 
	 * @param item
	 */
	public void addLineItem(LineItem item)
	{
		this.items.put(item.getItemName(),item);
	}
	public void addLineItem(String key,String value)
	{
		items.put(key,new LineItem(key,value));
	}
	/**
	 * Get the list name
	 * @return
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
	public Hashtable<String,LineItem> getItems() {
		return items;
	}
	/**
	 * Set the items in the list 
	 * @param items
	 */
	public void setItems(Hashtable<String,LineItem> items) {
		this.items = items;
	}
	/** 
	 * Delete an item from the list
	 * @param string
	 */
	public void deletItem(String key) {
		// TODO Auto-generated method stub
		items.remove(key);
		
	}

}
