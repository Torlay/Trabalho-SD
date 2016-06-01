

import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class LeituraArquivo {

	private String arquivo;
	/**
	 * @param args
	 */
	
	public LeituraArquivo(String arquivo){
		this.arquivo = arquivo;
	}
	
	public int getSize() throws IOException{
		List<CSVRecord> records;
		String fileName = "arquivos/" + this.arquivo;
		FileReader reader = new FileReader(fileName);
		CSVParser fileParser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
		records = fileParser.getRecords();
		fileParser.close();
		
		return records.size();
	}
	
	public String lerArquivo(String valor, int pos) {
		// TODO Auto-generated method stub
		List<CSVRecord> records;
		try {
    		String fileName = "arquivos/" + this.arquivo;
    		FileReader reader = new FileReader(fileName);
			CSVParser fileParser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
			records = fileParser.getRecords();
			fileParser.close();
			
			
			int n = records.size();
			String retorno;
			if(pos <= n) {			
				retorno = records.get(pos).get(valor);
			} else retorno = null;
			
			return retorno;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("erro carga dados");
		}
	}

}
