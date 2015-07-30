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
	
	public void setUpTestData(TDListManager tdlm)
	{
		
		tdlm.createList(Constants.GROCERY);
		for(int i = 1 ;i<6;i++)
		{
			tdlm.addItemToList(Constants.GROCERY,"TODO"+i, "TODODescription"+i);
		}
	}
	
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
		setUpTestData(tdlm);
		
		ToDoList list = tdlm.getList(Constants.GROCERY);
		assertEquals(5,list.getItems().size());
	}
	@Test
	public void testListItemAdditionWithTheSameItemName()
	{
		TDListManager tdlm = new TDListManager();
		tdlm.createList(Constants.GROCERY);
		for(int i = 1 ;i<6;i++)
		{
			tdlm.addItemToList(Constants.GROCERY,"TODO", "TODODescription"+i);
		}
		
		ToDoList list = tdlm.getList(Constants.GROCERY);
		//only the  first addition will be successful the rest of them will not be effective.
		assertEquals(1,list.getItems().size());
		assertEquals("TODODescription1",list.getItems().get("TODO").getItemDescription());
	}
	@Test
	public void testListItemUpdation()
	{
		TDListManager tdlm = new TDListManager();
		
		//create list
		tdlm.createList(Constants.GROCERY);
		
		//Add items to the list
		setUpTestData(tdlm);
		
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
		setUpTestData(tdlm);
		
		tdlm.deleteItemFromTheList(Constants.GROCERY,"TODO1");
		LineItem  todo1 = tdlm.getList(Constants.GROCERY).getLineItem("TODO1");
		
		assertEquals(null,todo1);
		
	}
}
