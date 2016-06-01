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
        String[] valores = new String[25];
        for(int tam = arq.getSize()-5; tam >=3; tam --){
        	valores = arq.lerArquivo(tam);
        	
        	engineScope.put("P1_OPEN" ,Double.parseDouble(valores[0]));
        	engineScope.put("P1_MAX" ,Double.parseDouble(valores[1]));
            engineScope.put("P1_MIN" ,Double.parseDouble(valores[2]));
            engineScope.put("P1_CLOSE" ,Double.parseDouble(valores[3]));
            
            engineScope.put("P2_OPEN" ,Double.parseDouble(valores[4]));
            engineScope.put("P2_MAX" ,Double.parseDouble(valores[5]));
            engineScope.put("P2_MIN" ,Double.parseDouble(valores[6]));
            engineScope.put("P2_CLOSE" ,Double.parseDouble(valores[7]));
            
            engineScope.put("P3_OPEN" ,Double.parseDouble(valores[8]));
            engineScope.put("P3_MAX" ,Double.parseDouble(valores[9]));
            engineScope.put("P3_MIN" ,Double.parseDouble(valores[10]));
            engineScope.put("P3_CLOSE" ,Double.parseDouble(valores[11]));
            
            engineScope.put("P4_OPEN" ,Double.parseDouble(valores[12]));
            engineScope.put("P4_MAX" ,Double.parseDouble(valores[13]));
            engineScope.put("P4_MIN" ,Double.parseDouble(valores[14]));
            engineScope.put("P4_CLOSE" ,Double.parseDouble(valores[15]));
            
            engineScope.put("P5_OPEN" ,Double.parseDouble(valores[16]));
            engineScope.put("P5_MAX" ,Double.parseDouble(valores[17]));
            engineScope.put("P5_MIN" ,Double.parseDouble(valores[18]));
            engineScope.put("P5_CLOSE" ,Double.parseDouble(valores[19]));
            
            
            if(engine.eval(exp, newContext).toString().equals("true")){ 
            	counter++;
            	engineScope.put("P1_CLOSE" ,Double.parseDouble(valores[20]));
            	engineScope.put("P2_CLOSE" ,Double.parseDouble(valores[21]));
            	engineScope.put("P3_CLOSE" ,Double.parseDouble(valores[22]));
            	engineScope.put("P4_CLOSE" ,Double.parseDouble(valores[23]));
            	if(engine.eval("P1_CLOSE * 3 <= (P2_CLOSE + P3_CLOSE + P4_CLOSE)", newContext).toString().equals("true")){
            		acertos++;
            	}
            } else {
            	
            }
        }
        String result = counter + " vezes encontradas e " + acertos + " acertos na simula��o de compra.";
        
        return result;
	}

}
