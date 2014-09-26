package jsestimation;

public class MozillaRhinoJsRunner extends BaseJsRunner {
	
    public MozillaRhinoJsRunner() {
    	super("rhino");
    }

    public static void main(String[] args) throws Exception {
    	
    	try {
    		IJsRunner jsRunner = new MozillaRhinoJsRunner();
    		
    		jsRunner.put("x", "hello world");
    		jsRunner.eval("println(x)");
    		
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
}

