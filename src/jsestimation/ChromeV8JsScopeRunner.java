package jsestimation;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

public class ChromeV8JsScopeRunner  {
	
    private ScriptEngine jsEngine;
    private ScriptContext engineContext;
	private Bindings engineScope;
	
    public ChromeV8JsScopeRunner() {
        ScriptEngineManager manager = new ScriptEngineManager();
        jsEngine = manager.getEngineByName("jav8");
        
        engineContext = new SimpleScriptContext();
        engineScope = engineContext.getBindings(ScriptContext.ENGINE_SCOPE);
        initial();
    }
    
    public static void println(String str) {
    	System.out.println(str);
    }   

    public static void main(String[] args) throws Exception {
    	
    	try {
    		ChromeV8JsScopeRunner jsRunner = new ChromeV8JsScopeRunner();
    		
    		jsRunner.put("x", "hello world");
    		jsRunner.eval("println(x)");
    		
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
	public void put(String key, Object value) {
    	engineScope.put(key, value);		
	}

	public Object get(String key) {
		return engineScope.get(key);
	}
	
	public Object eval(String script) {
		try {
			return jsEngine.eval(script, engineContext);
		} catch(ScriptException scriptException) {
			throw new RuntimeException(scriptException);
		}
	}
	
    protected void initial() {
    	try {
			put("println", this.getClass().getMethod("println", String.class));
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}
}

