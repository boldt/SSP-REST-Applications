package de.dennisboldt;
import java.io.IOException;
import java.util.LinkedList;

import de.dennis_boldt.RXTX;
import de.dennisboldt.RestClient;

public class Display {
	
	public Display(RXTX rxtx) throws Exception {

		String sparql = "SELECT * WHERE { <http://gruppe20.pit.itm.uni-luebeck.de/gruppe20> <http://gruppe20.pit.itm.uni-luebeck.de/sensorValue> ?o }";
		String ip = "141.83.151.196";
		String port = "8080";
		
		RestClient rc = new RestClient(ip, port, sparql, "text/csv");
        
		while(true) {
			
			try {
				LinkedList<String> ll = rc.getResult();
				Integer i = Integer.parseInt(ll.get(1));
				System.out.println("Value from SSP: " + i);

				// Send measured number to LCD
				System.out.println("Send command to display: " + i);
				rxtx.write(i.toString().getBytes());
				rxtx.write((byte)10); // NEW LINE
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println();
			Thread.sleep(500);
		}
	}

}