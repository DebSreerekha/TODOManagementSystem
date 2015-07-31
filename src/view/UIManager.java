package view;

import java.util.Scanner;
import java.util.regex.Pattern;

import controller.TDListManager;

/**
 * This class is used to handle all the command based user interface related functionality.
 * 
 * @author sreerekhadeb
 *
 */
public class UIManager  {
	/**
	 * Reference to the TDListManager
	 */
	private TDListManager tdlmanager ;
	/**
	 * Scanner class to accept the user input via the command prompt
	 */
	private Scanner scanner ;
	/**
	 * Validator Object to perform validations
	 */
	private UserInputValidator inputValidator ;
	
	/**
	 * No arguments constructor
	 */
	public UIManager() {
		tdlmanager = new TDListManager();
		tdlmanager.cleanUpRecentlyFinishedList();
		displayMenu();
		
		scanner = new Scanner(System.in) ;
		
		inputValidator = new UserInputValidator();
		
		acceptUserInputInteractively();	
	}
	
	/**
	 * Displays the interactive menu
	 */
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
		
		System.out.println(" *************************************************************************") ;
		System.out.println(" *************************************************************************") ;
	}
	/**
	 * Accepts the user input from the command line interactively and uses the 
	 * validator object to validate the data and create the UserInput object if the 
	 * data is valid and processes the input using the TDLManager object from the 
	 * controller package.
	 * 
	 */
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

	/**
	 * This method contains the code to process the entered input string and perform the
	 * corresponding operation .
	 *
	 * @param textRead
	 */
	public void processInput(UserInput  inputObject) {

		switch(inputObject.getChoice()) {
			case 1:
				System.out.println("Listname to be created :" + inputObject.getListname());
				tdlmanager.createList(inputObject.getListname());
				System.out.println("Created list ..:" + inputObject.getListname());
				break;
			case 2:
				System.out.println("Adding item to list with name :" +inputObject.getListname() + "item to be added :" +inputObject.getItemname());
				boolean status = tdlmanager.addItemToList(inputObject.getListname(),inputObject.getItemname(),inputObject.getItemDescription());
				if(status)
					System.out.println("Added the item to the list .." );
				else
					System.out.println("item could not be added as it already exists .. use update option to update the existing object .." );
				break;
			case 3:
				System.out.println("Delete item from the list with the list name:" +inputObject.getListname()+ "item to be deleted"+inputObject.getItemname());
				tdlmanager.deleteItemFromTheList(inputObject.getListname(),inputObject.getItemname());
				System.out.println("Deleted the item from the list ..");
				break;
			case 4:
				System.out.println("Update item in the list with the listname :" +inputObject.getListname()+"item to be updated"+inputObject.getItemname());
				tdlmanager.updateItemInTheList(inputObject.getListname(),inputObject.getItemname(),inputObject.getItemDescription());
				System.out.println("Updated the item in the list .. ");
				break;
			case 5:
				System.out.println("Fetch item from the list with the listname :"+inputObject.getListname() + "item name "+inputObject.getItemname());
				tdlmanager.viewListItem(inputObject.getListname(),inputObject.getItemname());
				System.out.println("Fetched the item from the list ..");
				break;
			case 6:
				System.out.println("Displaying  all the items in the list with listname :" +inputObject.getListname());
				tdlmanager.viewList(inputObject.getListname());
				break;
			case 7:
				System.out.println("Update the status of the item in the list "+inputObject.getItemStatus());
				tdlmanager.updateStatus(inputObject.getListname(), inputObject.getItemname(), inputObject.getItemStatus());
				System.out.println("Status update done ... ");
				break;
		}

	}

	/**
	 * main function to test run 
	 * @param args
	 */
	public static void main(String []args) {
		new UIManager();
	}
}
