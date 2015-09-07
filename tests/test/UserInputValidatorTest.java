package test;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import view.UserInput;
import view.UserInputValidator;

public class UserInputValidatorTest {
	@Test
	public void checkIfInputIsValid() {
		UserInputValidator inputValidator = new UserInputValidator();
		assertEquals(true,inputValidator.isValidInput("1|GROCERIES"));
		assertEquals(true,inputValidator.isValidInput("2|GROCERIES|TODO1"));
		assertEquals(true,inputValidator.isValidInput("3|GROCERIES|TODO1|DESCRIPTION"));
		assertEquals(true,inputValidator.isValidInput("4|GROCERIES|TODO1|DESCRIPTION"));
	}
	@Test
	public void checkIfInputIsValidAndCreateUserInputObject() {
		//Validate Data and create a user input object
		UserInputValidator inputValidator = new UserInputValidator();
		
		UserInput inputObject1 = inputValidator.validateInputAndCreateInputObject("1|GROCERIES");
		assertEquals(inputObject1.getListName(),"GROCERIES");
		assertEquals(inputObject1.getChoice(),1);
		
		UserInput inputObject2 = inputValidator.validateInputAndCreateInputObject("2|GROCERIES|TODO1|Description1");
		assertEquals(inputObject2.getListName(),"GROCERIES");
		assertEquals(inputObject2.getChoice(),2);
		assertEquals(inputObject2.getItemName(),"TODO1");
		assertEquals(inputObject2.getItemDescription(),"Description1");
		
		UserInput inputObject3 = inputValidator.validateInputAndCreateInputObject("3|GROCERIES|TODO1|Description1");
		assertEquals(inputObject3.getListName(),"GROCERIES");
		assertEquals(inputObject3.getChoice(),3);
		assertEquals(inputObject3.getItemName(),"TODO1");
		assertEquals(inputObject3.getItemDescription(),"Description1");
		
		UserInput inputObject4 = inputValidator.validateInputAndCreateInputObject("4|GROCERIES|TODO1|testing");
		assertEquals(inputObject4.getListName(),"GROCERIES");
		assertEquals(inputObject4.getChoice(),4);
		assertEquals(inputObject4.getItemName(),"TODO1");
		assertEquals(inputObject4.getItemDescription(),"testing");
		
		
		UserInput inputObject5 = inputValidator.validateInputAndCreateInputObject("5|GROCERIES|TODO1|testing1");
		assertEquals(inputObject5.getListName(),"GROCERIES");
		assertEquals(inputObject5.getChoice(),5);
		assertEquals(inputObject5.getItemName(),"TODO1");
		assertEquals(inputObject5.getItemDescription(),"testing1");
		
		UserInput inputObject6 = inputValidator.validateInputAndCreateInputObject("6|GROCERIES");
		assertEquals(inputObject6.getListName(),"GROCERIES");
		assertEquals(inputObject6.getChoice(),6);
		
		
		UserInput inputObject7 = inputValidator.validateInputAndCreateInputObject("7|GROCERIES|TODO1|D");
		assertEquals(inputObject7.getListName(),"GROCERIES");
		assertEquals(inputObject7.getChoice(),7);
		assertEquals(inputObject7.getItemName(),"TODO1");
		assertEquals(inputObject7.getItemStatus(),"D");

		UserInput inputObject8 = inputValidator.validateInputAndCreateInputObject("8|DISPLAY");
		assertEquals(inputObject8.getChoice(),8);

		UserInput inputObject9 = inputValidator.validateInputAndCreateInputObject("9|RFIL");
		assertEquals(inputObject9.getListName(),"RFIL");
		assertEquals(inputObject9.getChoice(),9);

	}

}
