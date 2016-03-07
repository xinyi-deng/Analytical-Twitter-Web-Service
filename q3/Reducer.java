import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/*
 * This class reads from system in, and ignore duplicte records.
 */
public class Reducer {	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
		String line = null;		
		String previousID = null;
		while ((line = br.readLine()) != null){
			String[] tmp = line.split("\t");
			if(tmp.length<=1) continue;
			String currID = tmp[3];	
			if(previousID!=null && previousID.compareTo(currID)==0) continue;
			else{
				PrintStream out = new PrintStream(System.out,true,"UTF-8");
				out.println(line);								
				previousID = currID;
			}						
		}		
	}
	
	
	
}
