package jsestimation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;

public class JsValidationTest {
	
	class TestObject
	{
		public String name = "name";
		public String type = "type";
		public boolean typeIsEmpty = false;
	}
	
	private String script;
    
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();
 
    @Before
    public void prepare()
    {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("var result = { pass: false }; ");
    	sb.append("if(obj.type.length === 0 && obj.typeIsEmpty === true) { ");
    	sb.append("result.pass = false; ");
    	sb.append("} else { ");
    	sb.append("result.pass = true; ");
    	sb.append("}");
    	
    	script = sb.toString();
    }
	
	@Test
    public void runV8Js() throws Exception
    {
    	IJsRunner jsRunner = new ChromeV8JsRunner();    	
        runTest(jsRunner);
    }
 
    @Test
    public void runRhinoJs() throws Exception
    {
    	//IJsRunner jsRunner = new MozillaRhinoJsRunner();    	
        //runTest(jsRunner);
    }

	private void runTest(IJsRunner jsRunner) {
		try {
			TestObject obj = new TestObject();
			jsRunner.put("obj", obj);
			
			Object retObj = jsRunner.eval(script);
			return;
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
