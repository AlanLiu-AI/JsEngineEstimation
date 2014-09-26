package jsestimation;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;

public class JSTest {
	
    private static int COUNT = 10;
    
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();
 
    @BeforeClass
    public static void prepare()
    {
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
    	IJsRunner jsRunner = new MozillaRhinoJsRunner();    	
        runTest(jsRunner);
    }
 
    private void runTest(IJsRunner jsRunner)
    {
        assert jsRunner!=null;
         
        for (int i = 0; i < COUNT; i++) {
        	jsRunner.put("x", "hello world");            
    		jsRunner.eval("println(x)");
        }
    }

}
