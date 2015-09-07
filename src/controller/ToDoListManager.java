package controller;


import domain.Constants;
import domain.ListItem;
import domain.ToDoList;
import services.ListManagerService;


public class ToDoListManager {
	public ListManagerService listManagerService ;
	public ToDoListManager(){
		listManagerService = new ListManagerService();
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
		listManagerService.updateListItemDescription(itemName,itemDescription,Constants.STATUS_UPDATE,listName);
	}
	public void deleteItemFromTheList(String listName , String itemName) {
		listManagerService.deleteListItem(listName, itemName);
	}
	public void viewList(String listName) {
		listManagerService.listListItems(listName);
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
	
	public void viewListItem(String listName, String itemName) {
		listManagerService.showListItem(listName, itemName);
	}
	public void updateStatus(String listName,String itemName,String status) {

		listManagerService.updateListItemStatus(listName, itemName, status);


	}
	public void cleanUpRecentlyFinishedList() {

		listManagerService.cleanUpRecentlyFinishedList();
	}

	public void displayAllTheLists()
	{
		listManagerService.displayAllTheLists();
	}
}
