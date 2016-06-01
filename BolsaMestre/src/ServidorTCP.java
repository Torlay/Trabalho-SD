

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class ServidorTCP extends Thread{
	
	private LinkedList<InetAddress> escravosLivres = new LinkedList<InetAddress>();
	
  
	
	public void validarEscravo(InetAddress ip) {
		boolean a = false;
		EnviaExpressao sender;
		for(InetAddress elem : escravosLivres) {
			if(elem.equals(ip)) {
				a = true;
				break;
			}
		}
		if(a == false) escravosLivres.push(ip);
		
		sender = EnviaExpressao.getInstance(escravosLivres);
		sender.setEscravosLivres(escravosLivres);
	}
	
	public void setEscravosLivres(LinkedList<InetAddress> o) {
		this.escravosLivres = o;
	}
	public LinkedList getEscravosLivres() {
		  return this.escravosLivres;
	}
	
	
	
	 
	 public void addLivres(InetAddress o) {
		 this.escravosLivres.push(o);
	 }
	 
  public void run() {
	  ServerSocket a;
	  
	try {
		a = new ServerSocket(9001);
		
		while(true){
			
			Socket sk = a.accept();
			InputStream in = sk.getInputStream();
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader buffReader = new BufferedReader(reader);
			String requestLine = null;
			InetAddress ip = sk.getInetAddress();
			
			do{
				requestLine = buffReader.readLine();
				System.out.println(ip.getHostAddress() + " : " +requestLine);
				
			} while(requestLine != null && requestLine != "fim");
			
			
			buffReader.close();
			in.close();
			sk.close();
			this.validarEscravo(ip);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}

      
  }
  
}