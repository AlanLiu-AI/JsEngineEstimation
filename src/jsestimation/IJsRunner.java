package jsestimation;

public interface IJsRunner {

	void put(String key, Object value);
	
	Object get(String key);
	
	Object eval(String script);
	
	void compile(String script);
	
	Object evalCompiledScript();
}
