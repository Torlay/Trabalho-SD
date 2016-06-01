import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ReceberArquivo {
	public void receber(String nome) {
		OutputStream socketOut = null;
		ServerSocket servsock = null;
		FileInputStream fileIn = null;
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			// Abrindo porta para conexao de clients
			servsock = new ServerSocket(9003);
			
			// Cliente conectado
			Socket sock = servsock.accept();
			
			is = sock.getInputStream();
			String path = new File("../BolsaEscravo/arquivos/" +nome).getCanonicalPath();
			fos = new FileOutputStream(new File(path));
			
			byte[] cbuffer = new byte[1024];
			int bytesRead;
			
			System.out.println("Recebendo arquivo...");
			while ((bytesRead = is.read(cbuffer)) != -1) {
				fos.write(cbuffer, 0, bytesRead);
				fos.flush();
			}
			
			System.out.println("Arquivo recebido!");
			
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
		 
			if (servsock != null) {
		        try {
		            servsock.close();
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
