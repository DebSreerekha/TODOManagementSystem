package domain;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * 
 * @author sreerekha
 *
 */
public class ToDoList {
	
	private String listName;
	
	private ArrayList<LineItem> items;
	
	public LineItem getLineItem(String key)
	{
		Iterator<LineItem> iterator = items.iterator();
		LineItem  item = null;
		while(iterator.hasNext())
		{
			LineItem tempItem = iterator.next();
			String tempKey = tempItem.getItemName();
			if (tempKey.equals(key)){
				item = tempItem;
				break;
			}
		}
		
		return item;
	}
	public void addLineItem(LineItem item)
	{
		this.items.add(item);
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public ArrayList<LineItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<LineItem> items) {
		this.items = items;
	}

}
