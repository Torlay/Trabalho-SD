import java.io.IOException;
import java.net.InetAddress;
import java.util.LinkedList;

import javax.script.ScriptException;

public class Main {
	
	public static LinkedList<String> expressoes = new LinkedList<String>();
	
	public static void main(String[] args) throws IOException, ScriptException, InterruptedException {
		UDPReceive listener = new UDPReceive();
		ReceberArquivo file = new ReceberArquivo();
		JSContext motor = new JSContext();
		ServidorTCP servidor = new ServidorTCP();
		
		//aguarda algum mestre
		System.out.println("Aguardando o mestre.");
		InetAddress ip = listener.listen();
		
		//aguarda a quantidade de arquivos
		String qtd = servidor.receberMensagem(9005);
		
		//aguarda arquivos
		for(int i = 0; i < Integer.parseInt(qtd); i++) {
			file.receber(i+".csv");
		}
		RespostaTCP resp = new RespostaTCP();
		
		while(true){	
			//aguarda a expressão na Thread ServidorTCP
			System.out.println("Aguardando expressão.");
			
				String exp = servidor.receberMensagem(9004);
				String resposta ="Expressão recebida: " + exp + ". Arquivos: ";
				for(int i = 0; i < Integer.parseInt(qtd); i++) {
					resposta += i+".csv : ";
					String x = motor.processarArquivo(i+".csv", exp);
					resposta += x + ". ";
				}
				resposta += " Fim do processamento.";
				resp.responder(resposta, ip, 9001);
				Thread.sleep(1000);
			}
		

	}
}