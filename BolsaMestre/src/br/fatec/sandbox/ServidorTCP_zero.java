package br.fatec.sandbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP_zero {

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
				
			}//while(requestLine != null && !requestLine.trim().equals(""));
			while(requestLine != null && requestLine != "fim");
			
			
			buffReader.close();
			in.close();
			sk.close();
		}
		

	}

}
