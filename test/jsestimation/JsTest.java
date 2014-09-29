package jsestimation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;

public class JsTest {
	
    private static int COUNT = 100;    		
    
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();
 
    @BeforeClass
    public static void prepare()
    {
    }
 
    @Test
    public void runV8JsInterpretor() throws Exception
    {
    	IJsRunner jsRunner = new ChromeV8JsRunner();    	
    	runScript(jsRunner);
    }
 
    @Test
    public void runRhinoJsInterpretor() throws Exception
    {
    	IJsRunner jsRunner = new MozillaRhinoJsRunner();    	
    	runScript(jsRunner);
    }
    
    @Test
    public void runV8JsCompiled() throws Exception
    {
    	IJsRunner jsRunner = new ChromeV8JsRunner();    	
    	runCompiledScript(jsRunner);
    }
 
    @Test
    public void runRhinoJsCompiled() throws Exception
    {
    	IJsRunner jsRunner = new MozillaRhinoJsRunner();    	
    	runCompiledScript(jsRunner);
    }
 
    private void runScript(IJsRunner jsRunner)
    {
        assert jsRunner!=null;
        
        String script = "9 === (x*x)";
         
        for (int i = 0; i < COUNT; i++) {
        	jsRunner.put("x", 3);
        	
        	Boolean result = (Boolean)jsRunner.eval(script);
        	
        	Assert.assertTrue(result);
        }
    }
    
    private void runCompiledScript(IJsRunner jsRunner)
    {
        assert jsRunner!=null;
        
        String script = "9 === (x*x)";
        jsRunner.compile(script);
        
        for (int i = 0; i < COUNT; i++) {
        	jsRunner.put("x", 3);
        	
        	Boolean result = (Boolean)jsRunner.evalCompiledScript();
        	
        	Assert.assertTrue(result);
        }
    }

}
