package test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.ToDoListManager;
import domain.Constants ;
import domain.ListItem ;
import domain.ToDoList ;


public class ToDoListManagerTest {


	
	public void setUpTestData(ToDoListManager tdlm,String listName) {

		for(int i = 1 ;i<6;i++) {
			tdlm.addItemToList(listName,listName+"TODO"+i, "TODODescription"+i);
		}
	}
	
	@Test
	public void testListCreation() {
		ToDoListManager tdlm = new ToDoListManager();
		tdlm.createList(Constants.GROCERY);
		ToDoList list = tdlm.getListFromDatabase(Constants.GROCERY);
		assertEquals(list.getListName(), Constants.GROCERY);
	}
	@Test
	public void testListItemAddition() {
		ToDoListManager tdlm = new ToDoListManager();
		setUpTestData(tdlm, Constants.GROCERY);

		ToDoList list = tdlm.getListFromDatabase(Constants.GROCERY);
		assertEquals(5,list.getItems().size());
	}
	@Test
	public void testListItemAdditionWithTheSameItemName() {

		ToDoListManager tdlm = new ToDoListManager();

		tdlm.createList(Constants.PERSONAL);
		for(int i = 1 ;i<6;i++) {
			tdlm.addItemToList(Constants.PERSONAL,"TODOPersonal", "TODODescription"+i);
		}
		ToDoList list = tdlm.getListFromDatabase(Constants.PERSONAL);

		//only the  first addition will be successful the rest of them will not be effective.
		int size = list.getItems().size();
		System.out.println("***************** size of the PERSONAL List ***************" + size);

		assertEquals(1, size);

		System.out.println(list.getItems().get("TODOPersonal").getItemDescription());

		assertEquals("TODODescription1", list.getItems().get("TODOPersonal").getItemDescription().trim());
	}
	@Test
	public void testListItemUpdation() {
		ToDoListManager tdlm = new ToDoListManager();

		//create list
		tdlm.createList(Constants.OFFICE);

		//Add items to the list
		setUpTestData(tdlm, Constants.OFFICE);

		ToDoList list = tdlm.getListFromDatabase(Constants.OFFICE);

		String itemdescription = list.getItems().get("OfficeTODO1").getItemDescription();

		assertEquals(itemdescription.trim(),"TODODescription1");

		//Update items from the list
		tdlm.updateItemInTheList(Constants.OFFICE, "OfficeTODO1", "itemDescription1");


		ToDoList list1 = tdlm.getListFromDatabase(Constants.OFFICE);
		String itemdescription1 = list1.getItems().get("OfficeTODO1").getItemDescription();

		String itemStatus = list1.getItems().get("OfficeTODO1").getStatus();

		System.out.println("status :" +itemStatus) ;

		//Check if the status is changed to update.
		assertEquals(Constants.STATUS_UPDATE,itemStatus);

		//get the updated item description and check if it matches with the current update.
		assertEquals(itemdescription1.trim(),"itemDescription1");
	}

	@Test
	public void testListItemStatusUpdateToDone() {
		ToDoListManager tdlm = new ToDoListManager();

		//create list
		tdlm.createList(Constants.TESTLIST2);

		//Add items to the list
		setUpTestData(tdlm,Constants.TESTLIST2);

		ToDoList list = tdlm.getListFromDatabase(Constants.TESTLIST2);

		String itemStatus = list.getItems().get("TestList2TODO1").getStatus();

		assertEquals(itemStatus.trim(),"Added");

		//Update items from the list
		tdlm.updateStatus(Constants.TESTLIST2, "TestList2TODO1", Constants.STATUS_DONE);

		// The item should be moved to RFIL if the status is updated to Done ..Check if it exists in the RFIL
		ToDoList list1 = tdlm.getListFromDatabase(Constants.RECENTLY_FINISHED_LIST);

		//The itemName is prefixed with the listname  and added to RFIl ..
		String status = list1.getItems().get("TestList2TestList2TODO1").getStatus();

		//Checkif the status of the item is done .
		assertEquals(status,Constants.STATUS_DONE);
	}

	@Test
	public void testListItemDeletion() {
		ToDoListManager tdlm = new ToDoListManager();

		//create list
		tdlm.createList(Constants.WEDDING);

		//Add items to the list
		setUpTestData(tdlm,Constants.WEDDING);

		tdlm.deleteItemFromTheList(Constants.WEDDING, "WeddingTODO1");
		ListItem  todo1 = tdlm.getListFromDatabase(Constants.WEDDING).getListItem("WeddingTODO1");

		assertEquals(null,todo1);
	}
	@Test
	public void testRecentlyFinishedTasks() {
		ToDoListManager tdlm = new ToDoListManager();

		//create list
		tdlm.createList(Constants.TESTLIST1);

		//Add items to the list
		setUpTestData(tdlm,Constants.TESTLIST1);

		ListItem recentlydoneInToDOList = tdlm.getListFromDatabase(Constants.TESTLIST1).getListItem("TestList1TODO1") ;

		tdlm.updateStatus(Constants.TESTLIST1,"TestList1TODO1",Constants.STATUS_DONE);


		//check if the item is moved to the recently finished items list
		ToDoList recentlyFinishedList = tdlm.getRecentlyFinishedList() ;
		ListItem recentlyFinishedItem = recentlyFinishedList.getListItem("TestList1TODO1") ;


		System.out.println("assertEquals(recentlyFinishedItem.getItemName(), recentlydoneInToDOList.getItemName());");
				assertEquals(recentlyFinishedItem.getItemName(), recentlydoneInToDOList.getItemName());
		System.out.println("*******  checking assertion done ******************");
		assertEquals(recentlyFinishedItem.getItemDescription(), recentlydoneInToDOList.getItemDescription());


		//The item should no longerbe present in the TESTLIST1 list
		ListItem recentlydoneInToDOList1 = tdlm.getListFromDatabase(Constants.TESTLIST1).getListItem("TestList1TODO1");

		assertEquals(null ,recentlydoneInToDOList1);
	}
	@Test
	public void testIfTheItemNeedsToBeDeletedFromTheRFList()
	{

		ToDoListManager tdlm = new ToDoListManager();
		//create list
		tdlm.createList(Constants.TESTLIST3);

		//Add items to the list
		setUpTestData(tdlm,Constants.TESTLIST3);

		ListItem recentlyDoneInToDOList = tdlm.getListFromDatabase(Constants.TESTLIST3).getListItem("TestList3TODO1") ;

		tdlm.updateStatus(Constants.TESTLIST3,"TestList3TODO1",Constants.STATUS_DONE);

		//check if the item is moved to the recently finished items list
		ToDoList recentlyFinishedList = tdlm.getRecentlyFinishedList() ;
		ListItem recentlyFinishedItem = recentlyFinishedList.getListItem("TestList3TODO1") ;

		assertEquals(recentlyFinishedItem.getStatus(),Constants.STATUS_DONE);

//		//Set the time stamp for the recently finished list item to be 3 days older than today .
//
//		Date currentDate = new Date();
//		Date threeDaysEarlierDate = new Date();
//
//		threeDaysEarlierDate.setTime(currentDate.getTime() - (long) 3 * 24 * 60 * 60 * 1000);
//
//		recentlyFinishedItem.setTimeStamp(threeDaysEarlierDate);
//
//		tdlm.cleanUpRecentlyFinishedList();
//
//		ListItem currentItemAfterCleanUp = recentlyFinishedList.getListItem("TestList3TODO1") ;
//
//		assertEquals(currentItemAfterCleanUp,null);

	}

}
