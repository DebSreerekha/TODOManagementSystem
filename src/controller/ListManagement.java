package controller;

import domain.LineItem;
import domain.ToDoList;

/**
 * Created by sreerekhadeb on 27/08/15.
 */
public interface ListManagement {

    public void updateStatus(String listName,String itemName,String status);
    public void cleanUpRecentlyFinishedList() ;
    public void viewListItem(String listName, String itemName);
    public boolean addItemToList(String listName, String itemName, String description);
    public ToDoList getList(String listName);
    public void viewList(String listName);
    public void deleteItemFromTheList(String listName , String itemName);
    public void updateItemInTheList(String listName, String itemName,String itemDescription);
    public ToDoList createList(String listName);
    public ToDoList getRecentlyFinishedList();
    public void setItemInTheRecentlyFinishedList(LineItem completed_item);
}
