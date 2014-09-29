package jsestimation;

public class MozillaRhinoJsRunner extends BaseJsRunner {
	
    public MozillaRhinoJsRunner() {
    	super("rhino");
    }

    public static void main(String[] args) throws Exception {
    	
    	try {
    		IJsRunner jsRunner = new MozillaRhinoJsRunner();
    		
    		jsRunner.put("x", 12);            
    		jsRunner.put("y", 12);
    		Object retObj = jsRunner.eval("x*y");
    		
    		System.out.println(retObj);
    		
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
}

