package br.fatec.sandbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

	/*
	 		Browser: GET / HTTP/1.1
            Browser: Host: www.google.com
            Browser:
            Server : HTTP/1.1 200 OK
            Server : Content-Type: text/html
            Server : Content-Length: 24
            Server :
            Server :<html>sample html</html>
	 */
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		ServerSocket a = new ServerSocket(4555);
		while(true){
			
			Socket sk = a.accept();
			InputStream in = sk.getInputStream();
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader buffReader = new BufferedReader(reader);
			String requestLine = null;
			do{
				requestLine = buffReader.readLine();
				System.out.println(">"+requestLine+"<");
				
			}while(!requestLine.trim().equals(""));
			
			OutputStream out = sk.getOutputStream();
			String response = "HTTP/1.1 200 OK\r\n";
			response += "Content-Type: text/html\r\n";
			response += "Content-Length: 24\r\n";
			response += "\r\n";
			response += "<html>sample html</html>\r\n";
			
			out.write(response.getBytes());
			out.flush();
			out.close();
			in.close();
			sk.close();
		}
		

	}

}
