package controller;
import java.util.*;

import domain.Constants;
import domain.LineItem;
import domain.ToDoList;
import domain.ToDoListCollection;

/**
 * This class is the controller class in the application 
 * with commands to add update delete and view the items in the 
 * toDoLists created .
 * @author sreerekhadeb
 * 
 */
public class TDListManager implements ListManagement {
	/**
	 * Domain object to contain the collection of the lists
	 */
	private ToDoListCollection listCollection ;
	/**
	 * Default constructor for the list manager 
	 */
	public TDListManager(){
		listCollection = new ToDoListCollection();
	}


	/**
	 * get the recently finsihed items list
	 * @return
	 */
	public ToDoList getRecentlyFinishedList() {
		return listCollection.getRecentlyFinishedList() ;
	}

	/**
	 * set the items which are recently finished in the list
	 * @param completed_item
	 */
	public void setItemInTheRecentlyFinishedList(LineItem completed_item) {
		listCollection.getRecentlyFinishedList().addLineItem(completed_item);
	}

//	@Override
//	public void addListName(String grocery) {
//		listCollection.createNewList(grocery);
//	}

	/**
	 * Creates the list with the specified name and adds it to the hashtable containing all the 
	 * toDoLists
	 * 
	 * @param listName
	 * @return
	 */
	public ToDoList createList(String listName) {
		ToDoList todoList = new ToDoList(listName);
		listCollection.getToDoLists().put(listName, todoList);
		return todoList;
	}
	
	/**
	 * Updates the item in the list with the specified list name
	 * @param itemDescription
	 * @param itemName
	 */
	public void updateItemInTheList(String listName, String itemName,String itemDescription) {
		ToDoList list = listCollection.getToDoLists().get(listName);
		list.updateLineItem(itemName, itemDescription);
	}
	/**
	 * Deletes the item with the specified key from the specified list
	 * @param listName
	 * @param itemName
	 */
	public void deleteItemFromTheList(String listName , String itemName) {
		ToDoList list = listCollection.getToDoLists().get(listName);
		LineItem item = list.getLineItem(itemName);
		list.deleteItem(itemName);
		getRecentlyFinishedList().addLineItem(item);
	}
	/**
	 * Displays the contents of the list
	 */
	public void viewList(String listName) {
		ToDoList list = listCollection.getToDoLists().get(listName);
		list.viewItemsInTheList(listName);
	}
	
	/**
	 * returns the list with the specified list name
	 * @param listName
	 * @return
	 */
	public ToDoList getList(String listName) {
		return listCollection.getToDoLists().get(listName);
	}
	
	/**
	 * create the lineitem with the specified itemName and description and add it to the list with the given listName
	 * @param listName
	 * @param itemName
	 * @param description
	 */
	public boolean addItemToList(String listName, String itemName, String description) {
		ToDoList list = listCollection.getToDoLists().get(listName);
		LineItem item = list.getLineItem(itemName);
		boolean status = false ;

		if(item == null) {
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
		
		ToDoList list = listCollection.getToDoLists().get(listName);
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
		ToDoList list = listCollection.getToDoLists().get(listName);
		LineItem tempItem = list.getLineItem(itemName);
		tempItem.setStatus(status);
		// if the status of the item is done , it needs to be removed
		//from the list and added to the recently finished list.
		if(status.equals(Constants.STATUS_DONE)) {
			list.deleteItem(itemName);
			listCollection.getRecentlyFinishedList().addLineItem(tempItem);
		}
	}
	/**
	 * This method is used to clean up the recently finished items list before any further processing
	 * If an item has spent more than 3 days in the list it should get deleted when this method is invoked.
	 */
	public void cleanUpRecentlyFinishedList() {

		Map<String,LineItem>items = getRecentlyFinishedList().getItems();
		Collection<LineItem> Values = items.values();
		Iterator<LineItem> iterator = Values.iterator();
		while(iterator.hasNext()) {
			LineItem item = iterator.next() ;
			Date date = item.getTimeStamp();
			Date todaysDate = new Date();

			long noOfDays = (todaysDate.getTime() - date.getTime() )/(24*60*60*1000);

			if(noOfDays >=3) {
				getRecentlyFinishedList().deleteItem(item.getItemName());
			}
		}
	}
}
