package domain;

import static org.junit.Assert.assertEquals;

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
		
		items = new Hashtable <String,LineItem>();
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
	 * update the line item with the specified value
	 * @param item
	 */
	public void updateLineItem(LineItem item)
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
		
		items.remove(key);
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
