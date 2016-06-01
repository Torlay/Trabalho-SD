import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) throws IOException, InterruptedException {
		//Variáveis de uso geral
		Scanner in = new Scanner(System.in);
		ServidorTCP listener = new ServidorTCP();
		SearchBroadcastAddress broadcast = new SearchBroadcastAddress();		
		LinkedList<InetAddress> escravosLivres = null;
		String exp;
		
		
		//1º - Localizar os escravos
		
			listener.start();
			broadcast.broadcast();
			Thread.sleep(3000);
			escravosLivres = listener.getEscravosLivres();
			
			
			EnviaExpressao sender = EnviaExpressao.getInstance(escravosLivres);
			//sender.start();
			
			//2º - Enviar todos os arquivos para todos os escravos
			File diretorio = new File(new File("../BolsaMestre/arquivos").getCanonicalPath());
			File arquivos[] = diretorio.listFiles();
			EnviarArquivo arq = new EnviarArquivo();
			int tam = arquivos.length;
			RespostaTCP tcp = new RespostaTCP();
			
			for(InetAddress escravoL : escravosLivres) {
				tcp.responder(""+(tam), escravoL, 9005);
				Thread.sleep(100);
				for(File arquivo : arquivos) {
					arq.enviarArquivo(escravoL, 9003, arquivo.getName());
					Thread.sleep(100);
				}
			}
			
			while(true){
				//receber expressões e enviá-las para os escravos
				try {
					System.out.println("Informe uma expressão: ");
					exp = in.nextLine();
					sender.addExpressao(exp);
					sender.enviar();
					Thread.sleep(1000);
					} catch (EmptyStackException e) {
						System.out.println("Nenhum escravo disponível!");
					}
			}
		
		
	}
}
