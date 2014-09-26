package jsestimation;


import javax.script.*;

public class MozillaRhinoJsRunnerEx implements IJsRunner {
	
	private ScriptEngine jsEngine;
	private ScriptContext engineContext;
	private Bindings engineScope;
	
    public MozillaRhinoJsRunnerEx() {
        ScriptEngineManager manager = new ScriptEngineManager();
        jsEngine = manager.getEngineByName("rhino");
        
        engineContext = new SimpleScriptContext();
        engineScope = engineContext.getBindings(ScriptContext.ENGINE_SCOPE);
    }

    public static void main(String[] args) throws Exception {
    	
    	try {
    		IJsRunner jsRunner = new MozillaRhinoJsRunnerEx();
    		
    		jsRunner.put("x", "hello world");
    		jsRunner.eval("println(x)");
    		
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
    @Override
	public void put(String key, Object value) {
    	engineScope.put(key, value);		
	}

	@Override
	public Object get(String key) {
		return engineScope.get(key);
	}

	@Override
	public Object eval(String script) {
		try {
			return jsEngine.eval(script, engineContext);
		} catch(ScriptException scriptException) {
			throw new RuntimeException(scriptException);
		}
	}
}

