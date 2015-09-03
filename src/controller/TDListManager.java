package controller;


import domain.Constants;
import domain.LineItem;
import domain.ToDoList;
import services.ListManagerService;

/**
 * This class is the controller class in the application 
 * with commands to add update delete and view the items in the 
 * toDoLists created .
 * @author sreerekhadeb
 * 
 */
public class TDListManager  {
	/**
	 * The service to perform  the list management
	 */
	public ListManagerService listManagerService ;
	/**
	 * Default constructor for the list manager 
	 */
	public TDListManager(){
		listManagerService = new ListManagerService();
		cleanUpRecentlyFinishedList();
	}

	public ToDoList getRecentlyFinishedList() {
		return ListManagerService.getListCollection().getToDoLists().get(Constants.RECENTLY_FINISHED_LIST);
	}

	/**
	 * set the items which are recently finished in the list
	 * @param completed_item
	 */
	public void setItemInTheRecentlyFinishedList(LineItem completed_item) {

		getRecentlyFinishedList().addLineItem(completed_item);
	}


	/**
	 * Creates the list with the specified name and adds it to the hashtable containing all the 
	 * toDoLists
	 * 
	 * @param listName
	 * @return
	 */
	public ToDoList createList(String listName) {
		return listManagerService.createList(listName);
	}
	
	/**
	 * Updates the item in the list with the specified list name
	 * @param itemDescription
	 * @param itemName
	 */
	public void updateItemInTheList(String listName, String itemName,String itemDescription) {
		listManagerService.updateLineItemDescription(itemName,itemDescription,Constants.STATUS_UPDATE,listName);
	}
	/**
	 * Deletes the item with the specified key from the specified list
	 * @param listName
	 * @param itemName
	 */
	public void deleteItemFromTheList(String listName , String itemName) {
		listManagerService.deleteLineItem(listName, itemName);
	}
	/**
	 * Displays the contents of the list
	 */
	public void viewList(String listName) {
		listManagerService.listLineItems(listName);
	}
	
	/**
	 * returns the list with the specified list name
	 * @param listName
	 * @return
	 */
	public ToDoList getList(String listName) {
		return listManagerService.getListCollection().getToDoLists().get(listName);
	}
	
	/**
	 * create the lineitem with the specified itemName and description and add it to the list with the given listName
	 * @param listName
	 * @param itemName
	 * @param description
	 */
	public boolean addItemToList(String listName, String itemName, String description) {
		return listManagerService.addListItem(listName,itemName,description,Constants.STATUS_ADDED);
	}
	
	/**
	 * Displays the list item in the specified list with the given listName.
	 * @param listName
	 * @param itemName
	 */
	public void viewListItem(String listName, String itemName) {
		listManagerService.showListItem(listName, itemName);
	}
	/**
	 * Updates the status of the item in the specified list.
	 * 
	 * @param listName
	 * @param itemName
	 * @param status
	 */
	public void updateStatus(String listName,String itemName,String status) {

		listManagerService.updateLineItemStatus(listName,itemName,status);


	}
	/**
	 * This method is used to clean up the recently finished items list before any further processing
	 * If an item has spent more than 3 days in the list it should get deleted when this method is invoked.
	 */
	public void cleanUpRecentlyFinishedList() {

		listManagerService.cleanUpRecentlyFinishedList();
	}

	public void displayAllTheLists()
	{
		listManagerService.displayAllTheLists();
	}
}
