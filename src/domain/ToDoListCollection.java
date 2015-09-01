package domain;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by sreerekhadeb on 29/08/15.
 */
public class ToDoListCollection implements Serializable{
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**

     * Instance variable to hold the list of the todo lists
     */
    private Hashtable<String,ToDoList> toDoLists ;
    /**
     * Instance variable to hold the recently finshed items - items whose status is done .
     */
    private ToDoList recentlyFinishedList ;

    public ToDoListCollection()
    {
        toDoLists = new Hashtable<String , ToDoList>();
        recentlyFinishedList = new ToDoList(Constants.RECENTLY_FINISHED_LIST);
    }

    public Hashtable<String, ToDoList> getToDoLists() {
        return toDoLists;
    }

    public void setToDoLists(Hashtable<String, ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
    }

    public ToDoList getRecentlyFinishedList() {
        return recentlyFinishedList;
    }

    public void setRecentlyFinishedList(ToDoList recentlyFinishedList) {
        this.recentlyFinishedList = recentlyFinishedList;
    }

    public void createNewList(String grocery) {
         ToDoList list = new ToDoList(grocery);
         this.toDoLists.put(grocery,list) ;
    }
}