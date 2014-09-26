package jsestimation;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class BaseJsRunner implements IJsRunner {
	
	protected ScriptEngine jsEngine;
	
	protected BaseJsRunner(String scriptEngineName) {
        ScriptEngineManager manager = new ScriptEngineManager();
        jsEngine = manager.getEngineByName(scriptEngineName);
        
        initial();
	}
    
	@Override
	public void put(String key, Object value) {
		jsEngine.put(key, value);		
	}

	@Override
	public Object get(String key) {
		return jsEngine.get(key);
	}

	@Override
	public Object eval(String script) {
		try {
			return jsEngine.eval(script);
		} catch(ScriptException scriptException) {
			throw new RuntimeException(scriptException);
		}
	}
	
	protected void initial() {
		
	}
}
