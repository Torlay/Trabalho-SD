import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class RespostaTCP {
	
	
	public void responder(String msg, InetAddress ip, int port) throws IOException {
		OutputStream socketOut = null;
		Socket a = null;
		byte[] buf = msg.getBytes();
		System.out.println("Enviando expressão para " + ip.getHostAddress()+" : " + ip.getHostName());
		a = new Socket(ip, port);
		socketOut = a.getOutputStream();
		
		socketOut.flush();
		socketOut.write(buf, 0, buf.length);
		socketOut.flush();
		a.close();
		
		
		
	}
}
