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
    		
    		jsRunner.put("x", "hello world");            
    		jsRunner.eval("println(x)");
    		
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

