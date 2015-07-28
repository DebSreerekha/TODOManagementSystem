package controller;
import domain.LineItem;
import domain.ToDoList;

public interface IListOperations {
	
	public ToDoList createList(String listname) ;
	public void addItemToList(String  listname,LineItem item);
	public void addItemToList(String listname, String itemName,String description);
	public void deleteItemFromTheList(String listname , String key);
	public void viewList(String listname);
	public void updateItemInTheList(String listname, LineItem item);

}
