import static org.junit.Assert.assertEquals;

import org.junit.Test;

import controller.TDListManager;
import domain.Constants;
import domain.LineItem;
import domain.ToDoList;
/**
 * Junit test cases for the TODOListManager class .
 * @author sreerekhadeb
 *
 */
public class ToDoListManagerTest {
	
	@Test
	public void testListCreation()
	{
		TDListManager tdlm = new TDListManager();
		tdlm.createList(Constants.GROCERY);
		ToDoList list = tdlm.getList(Constants.GROCERY);
		assertEquals(list.getListName() ,Constants.GROCERY);
	}
	@Test
	public void testListItemAddition()
	{
		TDListManager tdlm = new TDListManager();
		tdlm.createList(Constants.GROCERY);
		tdlm.addItemToList(Constants.GROCERY,"TODO1","TODODescription1");
		tdlm.addItemToList(Constants.GROCERY,"TODO2","TODODescription2");
		tdlm.addItemToList(Constants.GROCERY,"TODO3","TODODescription3");
		tdlm.addItemToList(Constants.GROCERY,"TODO4","TODODescription4");
		
		ToDoList list = tdlm.getList(Constants.GROCERY);
		assertEquals(4,list.getItems().size());
	}
	@Test
	public void testListItemUpdation()
	{
		TDListManager tdlm = new TDListManager();
		
		//create list
		tdlm.createList(Constants.GROCERY);
		
		//Add items to the list
		tdlm.addItemToList(Constants.GROCERY,"TODO1","TODODescription1");
		tdlm.addItemToList(Constants.GROCERY,"TODO2","TODODescription2");
		tdlm.addItemToList(Constants.GROCERY,"TODO3","TODODescription3");
		tdlm.addItemToList(Constants.GROCERY,"TODO4","TODODescription4");
		
		ToDoList list = tdlm.getList(Constants.GROCERY);
		String itemdescription = list.getItems().get("TODO1").getItemDescription();
		assertEquals(itemdescription,"TODODescription1");
		
		//Update items from the list
		tdlm.updateItemInTheList(Constants.GROCERY, "TODO1", "itemDescription1");
		ToDoList list1 = tdlm.getList(Constants.GROCERY);
		String itemdescription1 = list1.getItems().get("TODO1").getItemDescription();
		
		//get the updated item description and check if it matches with the current update.
		assertEquals(itemdescription1,"itemDescription1");
		
	}
	@Test
	public void testListItemDeletion()
	{
		TDListManager tdlm = new TDListManager();
		
		//create list
		tdlm.createList(Constants.GROCERY);
		
		//Add items to the list
		tdlm.addItemToList(Constants.GROCERY,"TODO1","TODODescription1");
		tdlm.addItemToList(Constants.GROCERY,"TODO2","TODODescription2");
		tdlm.addItemToList(Constants.GROCERY,"TODO3","TODODescription3");
		tdlm.addItemToList(Constants.GROCERY,"TODO4","TODODescription4");
		
		tdlm.deleteItemFromTheList(Constants.GROCERY,"TODO1");
		LineItem  todo1 = tdlm.getList(Constants.GROCERY).getLineItem("TODO1");
		
		assertEquals(null,todo1);
		
	}
}
