package jsestimation;

public class ChromeV8JsRunner extends BaseJsRunner {
	
    public ChromeV8JsRunner() {
    	super("jav8");
    }
    
    public static void println(String str) {
    	System.out.println(str);
    }    

    public static void main(String[] args) throws Exception {
    	
    	try {
    		IJsRunner jsRunner = new ChromeV8JsRunner();
    		
    		jsRunner.put("x", 12);            
    		jsRunner.put("y", 12);
    		Object retObj = jsRunner.eval("x*y");
    		
    		System.out.println(retObj);
    		
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	
    	try {
    		IJsRunner jsRunner = new ChromeV8JsRunner();
    		
    		jsRunner.put("x", "hello world in compiled script");            
    		jsRunner.compile("println(x); x");
    		
    		Object retObj = jsRunner.evalCompiledScript();
    		System.out.println(retObj);
    		
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
    @Override
    protected void initial() {
    	try {
			put("println", this.getClass().getMethod("println", String.class));
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}
}

