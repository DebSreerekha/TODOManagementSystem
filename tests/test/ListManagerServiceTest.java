package test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.ToDoListManager;
import domain.Constants ;
import domain.ListItem ;
import domain.ToDoList ;
import services.ListManagerService;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by sreerekhadeb on 07/09/15.
 */
public class ListManagerServiceTest {
    public static ListManagerService service = null ;
    //This method will be useful to set up test data before you run the test cases
    @BeforeClass
    public static void setUpClass()
    {
        service = new ListManagerService();
    }
    @Test
    public void testListCreation()
    {
        service.createList("TestListCreate");
        ToDoList list = service.fetchTheList("TestListCreate");
        assertEquals(list.getListName(),"TestListCreate");
    }
    @Test
    public void testUpdateListName()
    {
        service.createList("TestListUpdate");

        ToDoList list = service.fetchTheList("TestListUpdate");
        assertEquals(list.getListName(),"TestListUpdate");
        service.updateList("TestListUpdate", "TestUpdate");
        list = service.fetchTheList("TestUpdate") ;
        assertEquals(list.getListName(),"TestUpdate");
    }
    @Test
    public void testFetchAllListNames()
    {
        service.ListTheListNames();
    }
    @Test
    public void testDeleteListName()
    {
        String listName = "TestListCreate" ;

        service.deleteList(listName);

        ToDoList list = service.fetchTheList(listName);

        assertEquals(null,list);
    }

    @Test
    public void testListItemAddition()
    {
        String listName = "ItemAddTest" ;
        service.createList(listName) ;
        service.addListItem(listName, listName + "Item1", "ItemDescription1", Constants.STATUS_ADDED);
        ListItem item = service.showListItem(listName,listName+"Item1");
        assertEquals(item.getItemDescription().trim() ,"ItemDescription1");
    }
    @Test
    public void testListItemUpdationOfDescription()
    {
        String listName = "ItemUpdateTest1";
        service.createList(listName);
        service.addListItem(listName, listName + "Item1", "ItemDescription1", Constants.STATUS_ADDED);
        service.updateListItemDescription(listName+"Item1","NewDescription",Constants.STATUS_UPDATE,listName);

        ListItem item = service.showListItem(listName,listName+"Item1");
        assertEquals(item.getItemDescription().trim(),"NewDescription");

    }
    @Test
    public void testListItemUpdationOfStatus()
    {
        String listName = "ItemUpdateTest2";
        service.createList(listName);
        service.addListItem(listName,listName+"Item1","ItemDescription1",Constants.STATUS_ADDED);
        service.updateListItemStatus(listName,listName+"Item1",Constants.STATUS_UPDATE);

        ListItem item = service.showListItem(listName,listName+"Item1");
        assertEquals(item.getStatus().trim(),Constants.STATUS_UPDATE);
    }
    @Test
    public void testFetchListItem()
    {
        String listName = "ItemFetchTest" ;
        service.createList(listName);
        service.addListItem(listName,listName+"Item1","ItemDescription1",Constants.STATUS_ADDED);

        ListItem item = service.fetchTheListItem(listName + "Item1");
        assertEquals(item.getItemName().trim(),listName+"Item1");
    }
    @Test
    public void testFetchAllListItems()
    {
        String listName = "FetchAllItemsTest";
        service.createList(listName);

        for(int i = 1;i<6;i++)
        {
            service.addListItem(listName, listName + "Item"+i, "ItemDescription"+i, Constants.STATUS_ADDED);
        }

        ToDoList toDoList = service.fetchTheList(listName);
        Map<String,ListItem> listItemMap = toDoList.getItems();

        Collection<ListItem> Values = listItemMap.values();

        Iterator<ListItem> iterator = Values.iterator() ;

        while(iterator.hasNext())
        {
            ListItem item = iterator.next() ;
            String itemName = item.getItemName();
            int flag = Integer.parseInt(itemName.substring(itemName.length() - 1));
            assertEquals(item.getItemDescription().trim(),"ItemDescription"+flag);

        }
    }
    @Test
    public void testDeleteListItem()
    {
        String listName = "ItemDeletionTest" ;
        service.createList(listName);
        service.addListItem(listName,listName+"Item1","ItemDescription1",Constants.STATUS_ADDED);

        ListItem item = service.fetchTheListItem(listName + "Item1");
        assertEquals(item.getItemName().trim(),listName+"Item1");

        service.deleteListItem(listName, listName + "Item1");
         item = service.fetchTheListItem(listName + "Item1");
        assertEquals(null,item);
    }
}
