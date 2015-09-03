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

    public ToDoListCollection()
    {
        toDoLists = new Hashtable<String , ToDoList>();
        ToDoList recentlyFinishedList = new ToDoList(Constants.RECENTLY_FINISHED_LIST);
        toDoLists.put(Constants.RECENTLY_FINISHED_LIST,recentlyFinishedList);
    }

    public Hashtable<String, ToDoList> getToDoLists() {
        return toDoLists;
    }

    public void setToDoLists(Hashtable<String, ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
    }

    public ToDoList getRecentlyFinishedList()
    {
        return toDoLists.get(Constants.RECENTLY_FINISHED_LIST) ;
    }
}