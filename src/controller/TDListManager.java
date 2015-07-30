package controller;
import java.util.Hashtable;
import java.util.Scanner;

import domain.LineItem;
import domain.ToDoList;

/**
 * This class is the controller class in the application 
 * with commands to add update delete and view the items in the 
 * toDoLists created .
 * @author sreerekhadeb
 * 
 */
public class TDListManager  {
	/**
	 * Instance variable to hold the list of the todo lists
	 */
	private Hashtable<String,ToDoList> toDoLists ;
	
	/**
	 * Default constructor for the list manager 
	 */
	public TDListManager(){
		toDoLists = new Hashtable<String , ToDoList>();
		
	}
	/** 
	 * Creates the list with the specified name and adds it to the hashtable containing all the 
	 * toDoLists
	 * 
	 * @param listName
	 * @return
	 */
	public ToDoList createList(String listName)
	{
		ToDoList todoList = new ToDoList(listName);
		toDoLists.put(listName, todoList);
		return todoList;
	}
	
	/**
	 * Updates the item in the list with the specified list name
	 * @param list
	 * @param itemName
	 */
	public void updateItemInTheList(String listName, String itemName,String itemDescription)
	{
		ToDoList list = toDoLists.get(listName);
		list.updateLineItem(itemName,itemDescription);
	}
	/**
	 * Deletes the item with the specified key from the specified list
	 * @param list
	 * @param key
	 */
	public void deleteItemFromTheList(String listName , String itemName)
	{
		ToDoList list = toDoLists.get(listName);
		list.deletItem(itemName);
	}
	/**
	 * Displays the contents of the list
	 */
	public void viewList(String listName)
	{
		ToDoList list = toDoLists.get(listName);
		list.viewItemsInTheList(listName);
	}
	
	/**
	 * returns the list with the specified list name
	 * @param listName
	 * @return
	 */
	public ToDoList getList(String listName) {
		
		return toDoLists.get(listName);
		
	}
	
	/**
	 * create the lineitem with the specified itemName and description and add it to the list with the given listName
	 * @param listName
	 * @param itemName
	 * @param description
	 */
	public boolean addItemToList(String listName, String itemName, String description) {
		ToDoList list = toDoLists.get(listName);
		LineItem item = list.getLineItem(itemName);
		boolean status = false ;
		if(item == null)
		{
			list.addLineItem(new LineItem(itemName,description));
			status = true ;
		}
		return status ;
	}
	
	/**
	 * Displays the list item in the specified list with the given listName.
	 * @param listName
	 * @param itemName
	 */
	public void viewListItem(String listName, String itemName) {
		
		ToDoList list = toDoLists.get(listName);
		System.out.println(itemName + ":"+list.getLineItem(itemName).getItemDescription());
	}
	/**
	 * Updates the status of the item in the specified list.
	 * 
	 * @param listName
	 * @param itemName
	 * @param status
	 */
	public void updateStatus(String listName,String itemName,String status) {
		ToDoList list = toDoLists.get(listName);
		list.getLineItem(itemName).setStatus(status);
	}
}
