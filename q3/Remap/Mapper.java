import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


public class Mapper {

	public static void main(String[] args) {
		try{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
		//BufferedReader reader = new BufferedReader(new FileReader("try2.txt"));
		String readLine = "";
		while((readLine = reader.readLine())!=null){	
			String[] column = readLine.split("\t");
			if(column[2].compareTo("0")==0) continue;			
			PrintStream out = new PrintStream(System.out,true,"UTF-8");
			out.println(readLine.toString());
			
		}
		}catch (Exception e){
			return;
		}

	}

}
