package view;

import java.util.regex.Pattern;

public class UserInputValidator {

	private String regex;

	private Pattern pattern ;
	

	public UserInputValidator() {
		regex= "[1-9]{1}([|][a-z1-9A-Z]{1,}){1,3}" ;
		pattern = Pattern.compile(regex);
	}
	

	public boolean isValidInput(String inputString) {
		return pattern.matches(regex, inputString);
	}
	

	public UserInput validateInputAndCreateInputObject(String textRead) {
		
		if(isValidInput(textRead)) {

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
