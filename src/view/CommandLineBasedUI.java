package view;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class CommandLineBasedUI {
	
	public CommandLineBasedUI()
	{
		displayMenu();
		acceptUserInputInteractively();
		
	}

	public void displayMenu()
	{
		System.out.println("*********************************************************");
		System.out.println("Welcome to the TODO Management System");
		System.out.println("*********************************************************");
		
		System.out.println("1 <Listname>				      		- CREATE LIST") ;
		System.out.println("2 <ListName,itemname,description>	 	- ADD ITEM TO LIST") ;
		System.out.println("3 <ListName,itemname>				  	- DELETE ITEM FROM LIST") ;
		System.out.println("4 <ListName,itemname,updateddescription>- UPDATE AN ITEM IN THE LIST") ;
		System.out.println("5 <ListName,itemname> 					- FETCH AN ITEM FROM THE LIST") ;
		System.out.println("6 <ListName>					  		- DISPLAY ALL THE ITEMS IN THE LIST") ;
		
		System.out.println(" *************************************************************************") ;
		System.out.println(" *************************************************************************") ;
	}
	
	public void acceptUserInputInteractively()
	{
		
		Scanner scanner = new Scanner(System.in) ;
		String textRead = scanner.nextLine() ;
		
		validateInput(textRead) ;
	}
	public boolean validateInput(String inputString)
	{
		
		Pattern pattern = Pattern.compile("[1-6]{1}\b");
		return false;
	}
	public static void main(String []args)
	{
		System.out.println(Pattern.matches("[1-6]{1}\t[a-z]", "1 test"));
	}
}
