import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class Reducer {
	public static void main(String[] args) {
		try{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
		//BufferedReader reader = new BufferedReader(new FileReader("try2.txt"));
		String readLine = "";
		while((readLine = reader.readLine())!=null){	
			String[] column = readLine.split("\t");
			StringBuffer result = new StringBuffer();
			String[] date = column[1].split("-");
			result.append(column[0]+date[0]+date[1]+date[2]+"\t");
			result.append(column[2]+"\t");
			result.append(column[3]+"\t");
			result.append(column[4]);
			
			PrintStream out = new PrintStream(System.out,true,"UTF-8");
			out.println(result.toString());
			
		}
		}catch (Exception e){
			return;
		}

	}
}
