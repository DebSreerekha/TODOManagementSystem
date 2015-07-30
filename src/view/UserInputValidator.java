package view;

import java.util.regex.Pattern;
/**
 * This class validates the data entered by the user using regular expressions 
 * and also constructs the UserInput Object if the data is valid.
 * 
 * @author sreerekhadeb
 *
 */
public class UserInputValidator {
	/**
	 * regular expression string to validate the user input
	 */
	private String regex;
	/**
	 * Pattern object to perform regex matching.
	 */
	private Pattern pattern ;
	
	/**
	 * No args Constructor
	 */
	public UserInputValidator()
	{
		regex= "[1-7]{1}([|][a-z1-9A-Z]{1,}){1,3}" ;
		pattern = Pattern.compile(regex);
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
	 * This method contains the code to process the entered input string and perform the 
	 * corresponding operation .
	 * 
	 * @param textRead
	 */
	public UserInput validateInputAndCreateInputObject(String textRead) {
		
		if(isValidInput(textRead))
		{
			//create user input object
			Pattern pattern1 = Pattern.compile("\\W");
			
			String []inputs = pattern1.split(textRead);
			
			switch(inputs.length){ 
				case 2: return new UserInput(inputs[0],inputs[1], null,null); 
				
				case 3:	return new UserInput(inputs[0],inputs[1],inputs[2],null);
				
				case 4: 
					if (Integer.parseInt(inputs[0]) == 7)
					
						return new UserInput(inputs[0],inputs[1],inputs[2],inputs[3],7);	
					else
						return new UserInput(inputs[0],inputs[1],inputs[2],inputs[3]);		
			}
		}
		return null;
	}
}
