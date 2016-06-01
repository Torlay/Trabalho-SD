

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
	
	public String[] lerArquivo(int pos) {
		// TODO Auto-generated method stub
		List<CSVRecord> records;
		try {
    		String fileName = "arquivos/" + this.arquivo;
    		FileReader reader = new FileReader(fileName);
			CSVParser fileParser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
			records = fileParser.getRecords();
			fileParser.close();
			
			
			int n = records.size();
			String retorno[] = new String[24];
			if(pos <= n) {			
				retorno[0] = records.get(pos).get("Open");
				retorno[1] = records.get(pos).get("High");
				retorno[2] = records.get(pos).get("Low");
				retorno[3] = records.get(pos).get("Close");
				
				retorno[4] = records.get(pos+1).get("Open");
				retorno[5] = records.get(pos+1).get("High");
				retorno[6] = records.get(pos+1).get("Low");
				retorno[7] = records.get(pos+1).get("Close");
				
				retorno[8] = records.get(pos+2).get("Open");
				retorno[9] = records.get(pos+2).get("High");
				retorno[10] = records.get(pos+2).get("Low");
				retorno[11] = records.get(pos+2).get("Close");
				
				retorno[12] = records.get(pos+3).get("Open");
				retorno[13] = records.get(pos+3).get("High");
				retorno[14] = records.get(pos+3).get("Low");
				retorno[15] = records.get(pos+3).get("Close");
				
				retorno[16] = records.get(pos+4).get("Open");
				retorno[17] = records.get(pos+4).get("High");
				retorno[18] = records.get(pos+4).get("Low");
				retorno[19] = records.get(pos+4).get("Close");
				
				retorno[20] = records.get(pos).get("Close");
				retorno[21] = records.get(pos-1).get("Close");
				retorno[22] = records.get(pos-2).get("Close");
				retorno[23] = records.get(pos-3).get("Close");
			} else retorno = null;
			
			return retorno;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("erro carga dados");
		}
	}

}
