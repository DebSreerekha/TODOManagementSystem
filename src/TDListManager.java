import domain.LineItem;
import domain.ToDoList;


public class TDListManager {
	
	public TDListManager(){
		
	}
	public ToDoList createList(String listName)
	{
		ToDoList todoList = new ToDoList(listName);
		
		return todoList;
	}
	public void addItemToList(ToDoList list,LineItem item)
	{
		list.addLineItem(item);
		
	}
	public void updateItemInTheList(ToDoList list, LineItem item)
	{
		//list.
	}
	public void deleteItemFromTheList(ToDoList list , String key)
	{
		
	}
	public void viewList()
	{
		
	}
	public static void displayMenu ()
	{
		System.out.println("*********************************************************");
		System.out.println("Welcome to the TODO Management System");
		System.out.println("*********************************************************");
		
		System.out.println("1 <Listname>				      - CREATE LIST") ;
		System.out.println("2 <itemname,description>		  - ADD ITEM TO LIST") ;
		System.out.println("3 <itemname>					  - DELETE ITEM FROM LIST") ;
		System.out.println("4 <itemname,updated description> - UPDATE AN ITEM IN THE LIST") ;
		System.out.println("5 <itemname> 					  - FETCH AN ITEM FROM THE LIST") ;
		System.out.println("6 <ListName>					  - DISPLAY ALL THE ITEMS IN THE LIST") ;
		
		System.out.println(" *************************************************************************") ;
		System.out.println(" *************************************************************************") ;
		
	}
	
	public static void main(String args[])
	{
		TDListManager manager = new TDListManager() ;
		
		displayMenu();
		
		if(args.length > 0)
		{
			String firstArg = args[0] ;
			int option = Integer.parseInt(firstArg, firstArg.charAt(0));
			String listname = firstArg.substring(2) ;
			
			switch(option)
			{
				case 1 :
					
					System.out.println("Creating the list with the list name "+ listname)	;
					manager.createList(listname) ;
					break;
					
				//case 2:
					
					
			}
			
		}
	}
	
}
