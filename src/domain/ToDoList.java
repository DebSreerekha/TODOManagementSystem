package domain;

import java.util.Enumeration;
import java.util.Hashtable;
/**
 * This class encapsulates the ToDOList Object .
 * 
 * @author sreerekha
 *
 */
public class ToDoList {
	
	/**
	 * list name
	 */
	private String listName;
	
	/**
	 * items  stored in the current list
	 */
	private Hashtable<String,LineItem> items;
	
	/**
	 * public constructor 
	 * 
	 * @param listName2
	 */
	public ToDoList(String listName2) {
		
		items = new Hashtable <String,LineItem>();
		this.listName = listName2 ;
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
	public void updateLineItem(String itemName, String description)
	{
		LineItem item = getLineItem(itemName);
		item.setItemDescription(description);
	}
	/**
	 * update the line item with the specified value
	 * @param item
	 */

	public  void updateLineItem(LineItem item)
	{
		items.put(item.getItemName(),item);
		
	}
	/**
	 *  add item to the list
	 * 
	 * @param item
	 */
	
	public void addLineItem(LineItem item)
	{
		this.items.put(item.getItemName(),item);
	}
	/**
	 * Constructs an new LineItem object and adds it to the list
	 * 
	 * @param key
	 * @param value
	 */
	public void addLineItem(String itemName,String description)
	{
		items.put(itemName,new LineItem(itemName,description));
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
	public void deletItem(String itemName) {
		
		items.remove(itemName);
	}
	
	/**
	 * Displays the items in the list on the console
	 * 
	 */
	public void viewItemsInTheList(String listname) {
		
		Enumeration<LineItem> enumeration = items.elements();
		
		System.out.println("TODOList name :" + this.getListName()) ;
		System.out.println("Printing the contents ....");
		
		System.out.println("****************************************************");
		System.out.println(" name 		Description		Status		Timestamp  ");
		System.out.println("****************************************************");
		
		while(enumeration.hasMoreElements())
		{
			LineItem item = (LineItem) enumeration.nextElement();
			System.out.println(item.getItemName() + "\t"+item.getItemDescription()+"/t" +item.getStatus()+"\t"+item.getTimeStamp());	
		}
		
	}
	
}
