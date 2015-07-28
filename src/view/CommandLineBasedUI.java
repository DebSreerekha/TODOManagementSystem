package view;

import java.util.Scanner;

import java.util.regex.Pattern;

import controller.TDListManager;
import domain.LineItem;

public class CommandLineBasedUI  {
	private TDListManager tdlmanager ;
	private Scanner scanner ;
	private String regex;
	private Pattern pattern ;
	
	public CommandLineBasedUI()
	{
		tdlmanager = new TDListManager();
		regex= "[1-6]{1}([|][a-zA-Z]{1,}){1,3}" ;
		pattern = Pattern.compile(regex);
		
		displayMenu();
		scanner = new Scanner(System.in) ;
		acceptUserInputInteractively();	
	}
	
	/**
	 * Displays the interactive menu
	 */
	public void displayMenu()
	{
		System.out.println("*********************************************************");
		System.out.println("Welcome to the TODO Management System");
		System.out.println("*********************************************************");
		
		System.out.println("1|Listname				      		- CREATE LIST") ;
		System.out.println("2|ListName|itemname|description	 	- ADD ITEM TO LIST") ;
		System.out.println("3|ListName|itemname				  	- DELETE ITEM FROM LIST") ;
		System.out.println("4|ListName|itemname|updateddescription- UPDATE AN ITEM IN THE LIST") ;
		System.out.println("5|ListName|itemname 					- FETCH AN ITEM FROM THE LIST") ;
		System.out.println("6|ListName					  		- DISPLAY ALL THE ITEMS IN THE LIST") ;
		
		System.out.println(" *************************************************************************") ;
		System.out.println(" *************************************************************************") ;
	}
	/**
	 * accepts the user input from the command line interactively.
	 */
	public void acceptUserInputInteractively()
	{
		String textRead;
		while(scanner.hasNextLine())
		{
			textRead = scanner.nextLine() ;
			if(isValidInput(textRead) )
			{
				processInput(textRead);
			}
			else
			{
				System.out.println("Invalid user input .. displaying menu again .. enter the data  in the suggested format");
				displayMenu();
				//acceptUserInputInteractively();
			}
		}
		
		
	}
	/**
	 * This method contains the code to process the entered input string and perform the 
	 * corresponding operation .
	 * 
	 * @param textRead
	 */
	public void processInput(String textRead) {
		
		Pattern pattern1 = Pattern.compile("\\W");
		String [] words = pattern1.split(textRead);
		for(String string:words)
		{
			System.out.println(string);
		}
		
		int option = Integer.parseInt(words[0]);
		
		switch(option)
		{
			case 1:
				System.out.println("Listname to be created :" +words[1]);
				tdlmanager.createList(words[1]);
				break;
			case 2:
				System.out.println("Adding item to list with name :"+words[1]+"item to be added "+words[2]+ ","+words[3]);
				tdlmanager.addItemToList(words[1], words[2],words[3]);
				break;
			case 3:
				System.out.println("Delete item from the list with the list name:"+words[1]+"item to be deleted" +words[2]);
				tdlmanager.deleteItemFromTheList(words[1], words[2]);
				break;
			case 4:
				System.out.println("Update item in the list with the listname :" +words[1]+"item to be updated"+words[2] +"value to be updated"+words[3]);
				tdlmanager.updateItemInTheList(words[1], new LineItem(words[2],words[3]));
				break;
			case 5:
				System.out.println("Fetch item from the list with the listname :"+words[1]+"item name "+words[2]);
				tdlmanager.viewListItem(words[1],words[2]);
				break;
			case 6:
				System.out.println("Display all the items in the list with listname :"+words[1]);
				tdlmanager.viewList(words[1]);
				break;
				
		}
		
	}
	/**
	 * Validates the user input using regular expression pattern matching .
	 * 
	 * @param inputString
	 * @return
	 */
	public boolean isValidInput(String inputString)
	{
		return pattern.matches(regex, inputString);
		
	}
	/**
	 * main fuction to test run 
	 * @param args
	 */
	public static void main(String []args)
	{
		CommandLineBasedUI ui = new CommandLineBasedUI();
		
 
	}
}
