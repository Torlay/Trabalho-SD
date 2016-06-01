import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class RespostaTCP {
	
	
	public void responder(String msg, InetAddress ip, int port) throws IOException {
		OutputStream socketOut = null;
		Socket a = null;
		byte[] buf = msg.getBytes();
		System.out.println(ip.getHostAddress()+" : " + ip.getHostName());
		a = new Socket(ip, port);
		System.out.println("Consegui conectar com o mestre!");
		socketOut = a.getOutputStream();
		
		socketOut.flush();
		socketOut.write(buf, 0, buf.length);
		socketOut.flush();
		a.close();
		
		
		
	}
}
