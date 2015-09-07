package view;

import java.util.Scanner;

import controller.ToDoListManager;


public class UIManager  {

	private ToDoListManager toDoListManager;

	private Scanner scanner ;

	private UserInputValidator inputValidator ;
	

	public UIManager() {
		toDoListManager = new ToDoListManager();
		toDoListManager.cleanUpRecentlyFinishedList();
		displayMenu();
		
		scanner = new Scanner(System.in) ;
		
		inputValidator = new UserInputValidator();
		
		acceptUserInputInteractively();	
	}
	

	public void displayMenu() {
		System.out.println("*********************************************************");
		System.out.println("Welcome to the TODO Management System");
		System.out.println("*********************************************************");
		
		System.out.println("1|Listname				      		- CREATE LIST") ;
		System.out.println("2|ListName|itemname|description	 	- ADD ITEM TO LIST") ;
		System.out.println("3|ListName|itemname				  	- DELETE ITEM FROM LIST") ;
		System.out.println("4|ListName|itemname|updateddescription- UPDATE AN ITEM IN THE LIST") ;
		System.out.println("5|ListName|itemname 					- FETCH AN ITEM FROM THE LIST") ;
		System.out.println("6|ListName					  		- DISPLAY ALL THE ITEMS IN THE LIST") ;
		System.out.println("7|ListName|itemname|status 			- UPDATE THE STATUS OF AN ITEM IN THE LIST") ;
		System.out.println("8|DISPLAY 							- DISPLAY THE CONTENTS OF ALL THE LISTS IN THE SYSTEM") ;
		System.out.println("9|RFIL 								- DISPLAY THE RECENTLY FINISHED LIST") ;
		
		System.out.println(" *************************************************************************") ;
		System.out.println(" *************************************************************************") ;
	}

	public  void acceptUserInputInteractively() {

		String textRead;
		while(scanner.hasNextLine())
		{
			textRead = scanner.nextLine() ;
			if(inputValidator.isValidInput(textRead) ) {
				UserInput inputObject = inputValidator.validateInputAndCreateInputObject(textRead);
				processInput(inputObject) ;
				
			}
			else {
				System.out.println("Invalid user input .. displaying menu again .. enter the data  in the suggested format");
				displayMenu();
			}
		}
	}


	public void processInput(UserInput  inputObject) {

		switch(inputObject.getChoice()) {
			case 1:
				System.out.println("Listname to be created :" + inputObject.getListName());
				toDoListManager.createList(inputObject.getListName());
				System.out.println("Created list ..:" + inputObject.getListName());
				break;
			case 2:
				System.out.println("Adding item to list with name :" + inputObject.getListName() + "item to be added :" + inputObject.getItemName());
				boolean status = toDoListManager.addItemToList(inputObject.getListName(),inputObject.getItemName(),inputObject.getItemDescription());
				if(status)
					System.out.println("Added the item to the list .." );
				else
					System.out.println("item could not be added as it already exists .. use update option to update the existing object .." );
				break;
			case 3:
				System.out.println("Delete item from the list with the list name:" +inputObject.getListName()+ "item to be deleted"+inputObject.getItemName());
				toDoListManager.deleteItemFromTheList(inputObject.getListName(), inputObject.getItemName());
				System.out.println("Deleted the item from the list ..");
				break;
			case 4:
				System.out.println("Update item in the list with the listname :" +inputObject.getListName()+"item to be updated"+inputObject.getItemName());
				toDoListManager.updateItemInTheList(inputObject.getListName(), inputObject.getItemName(), inputObject.getItemDescription());
				System.out.println("Updated the item in the list .. ");
				break;
			case 5:
				System.out.println("Fetch item from the list with the listname :"+inputObject.getListName() + "item name "+inputObject.getItemName());
				toDoListManager.viewListItem(inputObject.getListName(), inputObject.getItemName());
				System.out.println("Fetched the item from the list ..");
				break;
			case 6:
				System.out.println("Displaying  all the items in the list with listname :" +inputObject.getListName());
				toDoListManager.viewList(inputObject.getListName());
				break;
			case 7:
				System.out.println("Update the status of the item in the list "+inputObject.getItemStatus());
				toDoListManager.updateStatus(inputObject.getListName(), inputObject.getItemName(), inputObject.getItemStatus());
				System.out.println("Status update done ... ");
				break;
			case 8:
				System.out.println("Display all the lists ");
				toDoListManager.displayAllTheLists();
				System.out.println("Displayed all the lists ...");
			case 9:
				System.out.println("Display the contents of the recently finished items list ..");
				toDoListManager.getRecentlyFinishedList().viewItemsInTheList();
				System.out.println("Displayed the contents of the RFIL ..");
		}

	}


	public static void main(String []args) {
		new UIManager();
	}
}
