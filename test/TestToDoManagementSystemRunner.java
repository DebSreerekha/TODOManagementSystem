	import org.junit.runner.JUnitCore;
	import org.junit.runner.Result;
	import org.junit.runner.notification.Failure;

	/** 
	 * This class is used to test all the tests and publish the results.
	 * @author sreerekha
	 *
	 */
	public class TestToDoManagementSystemRunner {
	 public static void main(String []args)
	 {
		 System.out.println("inside the test runner class");
		 Result result = JUnitCore.runClasses(ToDoListTest.class);
		 for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(result.wasSuccessful());
	 }
	}
