import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.Constants;
import domain.LineItem;
import domain.ToDoList;

/**
 * This class contains the unit test cases for testing the TODOList component
 * @author sreerekhadeb
 *
 */
public class ToDoListTest {
	
	@Test
	public void checkListAddition()
	{
		//create a list
		ToDoList tdl = new ToDoList(Constants.PERSONAL);
		
		//add items to the list 
		tdl.addLineItem("Key1", "Value1");
		tdl.addLineItem("Key2", "Value2");
		
		// test if the item is added
		int size = tdl.getItems().size();
		assertEquals(size,2);
		
	}
	@Test
	public void checkListItemExtraction()
	{
		//create a list
		ToDoList tdl = new ToDoList(Constants.PERSONAL);
		
		//add items to the list 
		tdl.addLineItem("Key1", "Value1");
		tdl.addLineItem("Key2", "Value2");
		
		String Value1 = (String)((LineItem)tdl.getLineItem("Key1")).getItemDescription();
		String Value2 = (String)((LineItem)tdl.getLineItem("Key1")).getItemDescription();
		assertEquals(Value1,"Value1");
		assertEquals(Value2,"Value2");
		
	}
	@Test
	public void checkListItemDeletion()
	{
		//create a list
		//add items to the list
		// delete an item 
		// check if the item still exists in the list
		
		//create a list
		ToDoList tdl = new ToDoList(Constants.PERSONAL);
		
		//add items to the list 
		tdl.addLineItem("Key1", "Value1");
		tdl.addLineItem("Key2", "Value2");
		
		//delete an item from the list
		tdl.deletItem("Key1");
		//String Value1 = (String)((LineItem)tdl.getLineItem("Key1")).getItemDescription();
		//String Value2 = (String)((LineItem)tdl.getLineItem("Key1")).getItemDescription();
	
	}
	@Test
	public void checkListItemUpdation()
	{
		
	}
	@Test
	public void checkListCreation()
	{
		
	}
	
	

}
