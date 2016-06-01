import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;

public class EnviaExpressao {
	private LinkedList<String> expressoes = new LinkedList<String>();
	private LinkedList<InetAddress> escravosLivres = new LinkedList<InetAddress>();
	private static EnviaExpressao instancia;
	
	
	public LinkedList<InetAddress> getEscravosLivres() {
		return instancia.escravosLivres;
	}


	public void setEscravosLivres(LinkedList<InetAddress> escravosLivres) {
		instancia.escravosLivres = escravosLivres;
	}

	public void addExpressao(String exp) {
		instancia.expressoes.push(exp);
	}
	
	private EnviaExpressao(LinkedList<InetAddress> el) {
		//instancia.escravosLivres = el;
		this.escravosLivres = el;
	}
	
	public static synchronized EnviaExpressao getInstance(LinkedList<InetAddress> el) {
		if(instancia == null) {
			instancia = new EnviaExpressao(el);
		}
		return instancia;
	}
	
	public void enviar(){
		RespostaTCP res = new RespostaTCP();
		InetAddress escravo = null;
		String exp;
		
			if(!(instancia.escravosLivres.isEmpty()) && !(instancia.expressoes.isEmpty())) {
				try {
					while((escravo = instancia.escravosLivres.pollLast()) != null && (exp = instancia.expressoes.pollFirst()) != null) {
						try {
							res.responder(exp, escravo, 9004);
							instancia.escravosLivres.push(escravo);
						} catch (IOException e) {
							System.out.println("Deu ruim. EnviaExpressao.java");
						}
					}
				} catch (EmptyStackException e) {
					System.out.println("Sem expressões para enviar ou sem escravos disponíveis");
				}
				
			}
		
	}
}
