import java.util.Hashtable;
import java.util.Scanner;

import domain.LineItem;
import domain.ToDoList;


public class TDListManager {
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
		list.viewItemsInTheList();
	}
	/**
	 * displays the command line menu
	 */
	public static void displayMenu ()
	{
		System.out.println("*********************************************************");
		System.out.println("Welcome to the TODO Management System");
		System.out.println("*********************************************************");
		
		System.out.println("1 <Listname>				      		- CREATE LIST") ;
		System.out.println("2 <ListName,itemname,description>	 	- ADD ITEM TO LIST") ;
		System.out.println("3 <ListName,itemname>				  	- DELETE ITEM FROM LIST") ;
		System.out.println("4 <ListName,itemname,updateddescription>- UPDATE AN ITEM IN THE LIST") ;
		System.out.println("5 <ListName,itemname> 					- FETCH AN ITEM FROM THE LIST") ;
		System.out.println("6 <ListName>					  		- DISPLAY ALL THE ITEMS IN THE LIST") ;
		
		System.out.println(" *************************************************************************") ;
		System.out.println(" *************************************************************************") ;
		
	}
	/**
	 * Main function to drive the ToDoListManager 
	 * @param args
	 */
	public static void main(String args[])
	{
		TDListManager manager = new TDListManager() ;
		
		displayMenu();
		
		
		Scanner scanner = new Scanner (System.in);
		
		String inputdata = scanner.nextLine();
		 
	}
	public ToDoList getList(String listName) {
		// TODO Auto-generated method stub
		return null;
		
	}
	
}
