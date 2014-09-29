package jsestimation;

import java.util.HashMap;
import java.util.Map;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.google.common.hash.Hashing;

public class BaseJsRunner implements IJsRunner {
	
	private static Map<String, CompiledScript> mappedScript = new HashMap<>();
	
	protected ScriptEngine jsEngine;
	protected CompiledScript compiledScript;
	
	protected BaseJsRunner(String scriptEngineName) {
        ScriptEngineManager manager = new ScriptEngineManager();
        jsEngine = manager.getEngineByName(scriptEngineName);
        
        initial();
	}
	
	public String getHash(String input) {
		return Hashing.md5().hashBytes(input.getBytes()).toString();
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
	
	@Override
	public void compile(String script) {
		String hash = getHash(script);
		if (!mappedScript.containsKey(hash)) {
			Compilable c = (Compilable) jsEngine;    
			try {
				compiledScript = c.compile(script);
			} catch (ScriptException e) {
				throw new RuntimeException(e);
			}
			mappedScript.put(hash, compiledScript);
		} else {
			compiledScript = mappedScript.get(hash);
		}
	}
	
	@Override
	public Object evalCompiledScript() {
		try {
			return compiledScript.eval();
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
	}
    
	protected void initial() {
		
	}
}
