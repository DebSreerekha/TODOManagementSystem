package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

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


    private ArrayList<ToDoList> toDoLists ;

    public ToDoListCollection()
    {
        toDoLists = new ArrayList<ToDoList>();
    }

    public ArrayList<ToDoList> getToDoLists() {
        return toDoLists;
    }

    public void setToDoLists(ArrayList<ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
    }

    public ToDoList getRecentlyFinishedList()
    {
        Iterator iterator = toDoLists.iterator();

        while(iterator.hasNext())
        {
            ToDoList list = (ToDoList) iterator.next();
            if(list.getListName().equals(Constants.RECENTLY_FINISHED_LIST))
                return list;
        }
        return null;
    }

    public String toString()
    {
       Iterator iterator = toDoLists.iterator();
        StringBuilder builder = new StringBuilder();
        while(iterator.hasNext())
        {
            ToDoList list = (ToDoList)iterator.next();
            builder.append(list.toString());
        }
        return builder.toString() ;
    }
}