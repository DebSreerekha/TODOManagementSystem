package controller;
import java.util.Hashtable;
import java.util.Scanner;

import domain.LineItem;
import domain.ToDoList;

/**
 * This class is the controller class in the application 
 * with commands to add update delete and view the items in the 
 * todolists created .
 * @author sreerekhadeb
 * 
 */
public class TDListManager implements IListOperations {
	/**
	 * Instance variable to hold the list of the todo lists
	 */
	private Hashtable<String,ToDoList> ToDoLists ;
	
	/**
	 * Default constructor for the list manager 
	 */
	public TDListManager(){
		ToDoLists = new Hashtable<String , ToDoList>();
		
	}
	/** 
	 * Creates the list with the specified name and adds it to the hashtable containing all the 
	 * ToDoLists
	 * 
	 * @param listName
	 * @return
	 */
	public ToDoList createList(String listName)
	{
		ToDoList todoList = new ToDoList(listName);
		ToDoLists.put(listName, todoList);
		return todoList;
	}
	/**
	 * Adds the LineItem to the list with the specified listname 
	 * @param list
	 * @param item
	 */
	public void addItemToList(String  listname,LineItem item)
	{
		ToDoList list = ToDoLists.get(listname);
		list.addLineItem(item);
	}
	/**
	 * Updates the item in the list with the specified list name
	 * @param list
	 * @param item
	 */
	public void updateItemInTheList(String listname, LineItem item)
	{
		ToDoList list = ToDoLists.get(listname);
		list.updateLineItem(item);
	}
	/**
	 * Deletes the item with the specified key from the specified list
	 * @param list
	 * @param key
	 */
	public void deleteItemFromTheList(String listname , String key)
	{
		ToDoList list = ToDoLists.get(listname);
		list.deletItem(key);
	}
	/**
	 * Displays the contents of the list
	 */
	public void viewList(String listname)
	{
		ToDoList list = ToDoLists.get(listname);
		list.viewItemsInTheList(listname);
	}
	
	/**
	 * returns the list with the specified list name
	 * @param listName
	 * @return
	 */
	public ToDoList getList(String listName) {
		
		return ToDoLists.get(listName);
		
	}
	
	/**
	 * create the lineitem with the specified itemname and description and add it to the list with the given listname
	 * @param listname
	 * @param itemname
	 * @param description
	 */
	public void addItemToList(String listname, String itemname, String description) {
		ToDoList list = ToDoLists.get(listname);
		list.addLineItem(new LineItem(itemname,description));
	}
	
	/**
	 * Displays the list item in the specified list with the given listname.
	 * @param listname
	 * @param itemname
	 */
	public void viewListItem(String listname, String itemname) {
		
		ToDoList list = ToDoLists.get(listname);
		System.out.println(itemname + ":"+list.getLineItem(itemname).getItemDescription());
		
		
	}
	
}
