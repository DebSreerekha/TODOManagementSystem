package controller;


import domain.Constants;
import domain.ListItem;
import domain.ToDoList;
import domain.ToDoListCollection;
import services.ListManagerService;
import view.ConsolePrinter;


public class ToDoListManager {

	private ListManagerService listManagerService ;

	private ConsolePrinter printer ;

	public ToDoListManager(){
		listManagerService = new ListManagerService();
		printer = new ConsolePrinter();
		cleanUpRecentlyFinishedList();
	}

	public ToDoList getRecentlyFinishedList() {
		return listManagerService.getRecentlyFinishedList();
	}

	public void setItemInTheRecentlyFinishedList(ListItem completed_item) {
		getRecentlyFinishedList().addListItem(completed_item);
	}
	public void createList(String listName) {
		 listManagerService.createList(listName);
	}
	
	public void updateItemInTheList(String listName, String itemName,String itemDescription) {
		listManagerService.updateListItemDescription(itemName, itemDescription, Constants.STATUS_UPDATE, listName);
	}
	public void deleteItemFromTheList(String listName , String itemName) {
		listManagerService.deleteListItem(listName, itemName);
	}
	private ToDoList getList(String listName) {
		return listManagerService.fetchTheList(listName);
	}

	public ToDoList getListFromDatabase(String listName) {
		return listManagerService.fetchTheList(listName);
	}

	public boolean addItemToList(String listName, String itemName, String description) {
		return listManagerService.addListItem(listName, itemName, description, Constants.STATUS_ADDED);
	}

	public void updateStatus(String listName,String itemName,String status) {
		listManagerService.updateListItemStatus(listName, itemName, status);
	}
	public void cleanUpRecentlyFinishedList() {
		listManagerService.cleanUpRecentlyFinishedList();
	}

	public void viewListItem(String listName, String itemName) {
		ListItem listItem = listManagerService.fetchTheListItem(itemName) ;
		printer.displayData(listItem);
	}
	public void displayAllTheLists() {
		ToDoListCollection listsCollection = listManagerService.fetchAllTheLists();
		printer.displayData(listsCollection);
	}
	public void viewList(String listName) {
		ToDoList list = listManagerService.fetchTheList(listName);
		printer.displayData(list);
	}
}
