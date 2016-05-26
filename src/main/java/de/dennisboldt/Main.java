package de.dennisboldt;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import de.dennis_boldt.RXTX;

public class Main {

	@Option(name="--port",usage="Set USB port")
	private String port = "/dev/ttyACM0";
		
	@Option(name="--rxtxlib",usage="Set RXTX lib")
	private String rxtxlib = "/usr/lib/jni";

	@Option(name="--app",usage="Application (display, pump)", required=true)
	private String app = null;
	
	private RXTX rxtx;

	public Main(String[] args) throws Exception {

		rxtx = new RXTX();
		
		CmdLineParser parser = new CmdLineParser(this);
        try {
        	parser.parseArgument(args);
        	rxtx.start(this.port, this.rxtxlib, null);
        } catch (Exception e) {
        	//e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println();
            parser.printUsage(System.err);
        }
        
        if("display".equals(this.app)) {
        	System.out.println("#################################################");
        	System.out.println("Run DISPLAY app");
        	System.out.println("#################################################");
        	new Display(rxtx);
        } else if("pump".equals(this.app)) {
        	System.out.println("#################################################");
        	System.out.println("Run PUMP app");
        	System.out.println("#################################################");
        	new Pump(rxtx);
        }
        
	}
	
	public static void main(String[] args) throws Exception {
		new Main(args);
	}
	
}
