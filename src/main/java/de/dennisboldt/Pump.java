package de.dennisboldt;
import java.io.IOException;
import java.util.LinkedList;

import de.dennis_boldt.RXTX;
import de.dennisboldt.RestClient;

public class Pump {
	
	public Pump(RXTX rxtx) throws Exception {

		String sparql = "SELECT * WHERE { <http://gruppe20.pit.itm.uni-luebeck.de/gruppe20> <http://gruppe20.pit.itm.uni-luebeck.de/sensorValue> ?o }";
		String ip = "141.83.151.196";
		String port = "8080";
		
		RestClient rc = new RestClient(ip, port, sparql, "text/csv");
		
		while(true) {
			
			try {
				LinkedList<String> ll = rc.getResult();
				Integer i = Integer.parseInt(ll.get(1));
				System.out.println("Value from SSP: " + i);
				
				String cmd = "stop";
				if(i < 250) {
					cmd = "start";
				}
				
				// Send 'start' or 'stop' to Motor
				System.out.println("Send value to pump: " + cmd);
				rxtx.write(cmd.getBytes());
				rxtx.write(10); // NEW LINE
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println();
			Thread.sleep(500);
		}
	}

}