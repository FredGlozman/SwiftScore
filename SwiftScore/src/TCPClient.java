import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class TCPClient implements Runnable{
	
	String address;
	int sPort;
	public TCPClient(String addr, int port){
		address = addr;
		sPort = port;
	}
	
	public void fetch() throws UnknownHostException, IOException{
		String line;
		
		Socket client = new Socket(address,sPort);
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		line = in.readLine();
		System.out.println(line);
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException{
		
		while(true){
			new TCPClient("localhost",8520).fetch();
		}
//		while(true){
//			Thread t = new Thread(new TCPClient("localhost",8520));
//			t.start();
//		}

	}

	@Override
	public void run() {
		try {
			fetch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(-1);
		} 
		
	}
}
