package Runner;

import Steps.Login;
import cucumber.api.cli.Main;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@CucumberOptions(
//		features ="Feature"
//		,glue= {"Steps"}
//		)
public class TestRunner extends AbstractTestNGCucumberTests{
	public static void main(String[] args) throws Throwable {
		 
        
         Main.main(new String[]{"-g", "Steps", "Feature"});
         Login.main(null);
        
     }
}
