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
	private String regex;
	private Pattern pattern ;
	/**
	 * No args Constructor
	 */
	public UserInputValidator()
	{
		regex= "[1-6]{1}([|][a-z1-9A-Z]{1,}){1,3}" ;
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
		
		UserInput input = null ;
		if(isValidInput(textRead))
		{
			//create user input object
			Pattern pattern1 = Pattern.compile("\\W");
			String [] inputs = pattern1.split(textRead);
			for(String value:inputs)
			{
				System.out.println(value);
			}	
			 input  = new UserInput(inputs[0],inputs[1],inputs[2],inputs[3]);	
		}
		
		return input;
	}

	
}
