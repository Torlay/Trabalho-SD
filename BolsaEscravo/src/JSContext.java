import java.io.IOException;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;


public class JSContext {

	/**
	 * @param args
	 * @throws ScriptException 
	 * @throws IOException 
	 */
	public String processarArquivo(String nome, String exp) throws ScriptException, IOException {
		
		ScriptEngineManager factory = new ScriptEngineManager();
	    ScriptEngine engine = factory.getEngineByName("JavaScript");
	    
	    LeituraArquivo arq = new LeituraArquivo(nome);
	  
	    
	   
	    int counter = 0;
	    int acertos = 0;
		ScriptContext newContext = new SimpleScriptContext();
        Bindings engineScope = newContext.getBindings(ScriptContext.ENGINE_SCOPE);
        
        for(int tam = arq.getSize()-5; tam >=3; tam --){
        	engineScope.put("P1_OPEN" ,Double.parseDouble(arq.lerArquivo("Open", tam)));
        	engineScope.put("P1_MAX" ,Double.parseDouble(arq.lerArquivo("High", tam)));
            engineScope.put("P1_MIN" ,Double.parseDouble(arq.lerArquivo("Low", tam)));
            engineScope.put("P1_CLOSE" ,Double.parseDouble(arq.lerArquivo("Close", tam)));
            
            engineScope.put("P2_OPEN" ,Double.parseDouble(arq.lerArquivo("Open", tam+1)));
            engineScope.put("P2_MAX" ,Double.parseDouble(arq.lerArquivo("High", tam+1)));
            engineScope.put("P2_MIN" ,Double.parseDouble(arq.lerArquivo("Low", tam+1)));
            engineScope.put("P2_CLOSE" ,Double.parseDouble(arq.lerArquivo("Close", tam+1)));
            
            engineScope.put("P3_OPEN" ,Double.parseDouble(arq.lerArquivo("Open", tam+2)));
            engineScope.put("P3_MAX" ,Double.parseDouble(arq.lerArquivo("High", tam+2)));
            engineScope.put("P3_MIN" ,Double.parseDouble(arq.lerArquivo("Low", tam+2)));
            engineScope.put("P3_CLOSE" ,Double.parseDouble(arq.lerArquivo("Close", tam+2)));
            
            engineScope.put("P4_OPEN" ,Double.parseDouble(arq.lerArquivo("Open", tam+3)));
            engineScope.put("P4_MAX" ,Double.parseDouble(arq.lerArquivo("High", tam+3)));
            engineScope.put("P4_MIN" ,Double.parseDouble(arq.lerArquivo("Low", tam+3)));
            engineScope.put("P4_CLOSE" ,Double.parseDouble(arq.lerArquivo("Close", tam+3)));
            
            engineScope.put("P5_OPEN" ,Double.parseDouble(arq.lerArquivo("Open", tam+4)));
            engineScope.put("P5_MAX" ,Double.parseDouble(arq.lerArquivo("High", tam+4)));
            engineScope.put("P5_MIN" ,Double.parseDouble(arq.lerArquivo("Low", tam+4)));
            engineScope.put("P5_CLOSE" ,Double.parseDouble(arq.lerArquivo("Close", tam+4)));
            
            
            if(engine.eval(exp, newContext).toString().equals("true")){ 
            	System.out.println("true");
            	counter++;
            	engineScope.put("P1_CLOSE" ,Double.parseDouble(arq.lerArquivo("Close", tam)));
            	engineScope.put("P2_CLOSE" ,Double.parseDouble(arq.lerArquivo("Close", tam-1)));
            	engineScope.put("P3_CLOSE" ,Double.parseDouble(arq.lerArquivo("Close", tam-2)));
            	engineScope.put("P4_CLOSE" ,Double.parseDouble(arq.lerArquivo("Close", tam-3)));
            	if(engine.eval("P1_CLOSE * 3 <= (P2_CLOSE + P3_CLOSE + P4_CLOSE)", newContext).toString().equals("true")){
            		acertos++;
            	}
            } else {
            	System.out.println("false");
            }
        }
        String result = counter + " vezes encontradas e " + acertos + " acertos na simulação de compra.";
        
        return result;
	}

}
