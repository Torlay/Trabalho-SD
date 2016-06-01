import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class EnviarArquivo {
	
	
	public void enviarArquivo(InetAddress ip, int port, String nome) {
		OutputStream socketOut = null;
		Socket sock = null;
		FileInputStream fileIn = null;
		
		
		try {
			sock = new Socket(ip.getHostAddress(), port);
			
			byte[] cbuffer = new byte[1024];
			int bytesRead;
			
			String path = new File("../BolsaMestre/arquivos/" + nome).getCanonicalPath();
			
			System.out.println("Lendo arquivo...");
			File file = new File(path);
			fileIn = new FileInputStream(file);
			
			socketOut = sock.getOutputStream();
			
			System.out.println("Enviando Arquivo...");
			while ((bytesRead = fileIn.read(cbuffer)) != -1) {
				socketOut.write(cbuffer, 0, bytesRead);
				socketOut.flush();
			}
			
			System.out.println("Arquivo Enviado!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socketOut != null) {
				try {
					socketOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (sock != null) {
				try {
					sock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
