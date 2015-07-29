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
public class TDListManager  {
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
	 * Updates the item in the list with the specified list name
	 * @param list
	 * @param itemname
	 */
	public void updateItemInTheList(String listname, String itemname,String itemDescription)
	{
		ToDoList list = ToDoLists.get(listname);
		list.updateLineItem(itemname,itemDescription);
	}
	/**
	 * Deletes the item with the specified key from the specified list
	 * @param list
	 * @param key
	 */
	public void deleteItemFromTheList(String listname , String itemName)
	{
		ToDoList list = ToDoLists.get(listname);
		list.deletItem(itemName);
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
	/**
	 * Updates the status of the item in the specified list.
	 * 
	 * @param listName
	 * @param itemName
	 * @param status
	 */
	public void updateStatus(String listName,String itemName,String status) {
		ToDoList list = ToDoLists.get(listName);
		list.getLineItem(itemName).setStatus(status);
	}
}
