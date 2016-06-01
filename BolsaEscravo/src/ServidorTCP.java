import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP{
	
	

	
	
	public String receberMensagem(int port) throws IOException {
		ServerSocket a = new ServerSocket(port);
		
		Socket sk = a.accept();
		InputStream in = sk.getInputStream();
		InputStreamReader reader = new InputStreamReader(in);
		BufferedReader buffReader = new BufferedReader(reader);
		String requestLine = null;

		
		requestLine = buffReader.readLine();
		
		System.out.println(requestLine);
			
			
			buffReader.close();
			in.close();
			sk.close();
			Main.expressoes.add(requestLine);
			return requestLine;

		}
}
