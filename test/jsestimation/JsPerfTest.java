package jsestimation;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;

public class JsPerfTest {
	
	ScriptEngineManager scriptEngineManager;
	FileReader jsFile;

	@Rule
    public TestRule benchmarkRun = new BenchmarkRule();
 
    @Before
    public void prepare() throws FileNotFoundException
    {
    	scriptEngineManager = new ScriptEngineManager();		
    	jsFile = new FileReader("test.js");
		
    }
	
	@Test
    public void runV8Js() throws Exception
    {
		ScriptEngine jsEngine = scriptEngineManager.getEngineByName("jav8");
        runTest(jsEngine);
    }
 
    @Test
    public void runRhinoJs() throws Exception
    {
    	ScriptEngine jsEngine = scriptEngineManager.getEngineByName("rhino");
    	runTest(jsEngine);
    }

	private void runTest(ScriptEngine jsEngine) {
		int iter = 10000;
		
		try {
			long acum = 0;
			for(int i=0; i<iter; i++){
				long start = System.currentTimeMillis();
				Object ob = jsEngine.eval(jsFile);
				long end = System.currentTimeMillis();
				
				acum += end - start;
			}
			System.out.println(acum);
		} catch (ScriptException ex) {
			ex.printStackTrace();
		}
	}

}
