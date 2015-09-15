package view;

import domain.ListItem;
import domain.ToDoList;
import domain.ToDoListCollection;
import java.util.*;

/**
 * This class will have methods to display the contents on the console .
 *
 * Created by sreerekhadeb on 15/09/15.
 */
public class ConsolePrinter {

    public ConsolePrinter()
    {

    }

    public void displayData(ToDoListCollection listsCollection) {

        ArrayList<ToDoList> lists = listsCollection.getToDoLists();

        for (Iterator iterator1 = lists.iterator(); iterator1.hasNext(); )
        {
            System.out.println("*********************** List contents*******************************");
            ToDoList list = (ToDoList) iterator1.next();
            System.out.print("ListName: " + list.getListName() + "   ");
            System.out.print("Creation date: " + list.getlastUpdatedAt() + "    ");
            System.out.print("id: " + list.getId());
            System.out.println();

            Map<String,ListItem> itemsMap = list.getItems() ;
            Collection<ListItem> ListItemsMap = itemsMap.values();
            Iterator iter = ListItemsMap.iterator() ;
            while(iter.hasNext())
            {
                ListItem item = (ListItem)iter.next();
                System.out.println("************ Printing the line items *************");
                System.out.println(item.getItemName());
                System.out.println(item.getItemDescription()) ;
                System.out.println(item.getLastUpdatedAt());
                System.out.println(item.getStatus());
            }
            System.out.println("----------------------------------------------------------------------");
        }

    }

    public void displayData(ToDoList list)
    {
        Map<String,ListItem>  itemsMap = list.getItems();
        Collection<ListItem> values = itemsMap.values();
        Iterator<ListItem>  iterator = values.iterator();

        System.out.println("Printing the contents ....");

        System.out.println("****************************************************");
        System.out.println(" name 		Description		Status		lastUpdatedAt  ");
        System.out.println("****************************************************");

        while(iterator.hasNext()){
            ListItem item = (ListItem) iterator.next();
            displayData(item);
        }
    }

    public void displayData( ListItem item)
    {
        System.out.println(item.toFormattedString());
    }

}
