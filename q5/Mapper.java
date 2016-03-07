import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.google.gson.Gson;

/*
 * This class define mapping process. It takes JSON format file, fetch 
 * a certain columns, and output formated string to system out.
 */
public class Mapper {

	


	public static void main(String[] args) throws IOException{
		Gson gson = new Gson();
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
		String readLine = "";
		while((readLine = reader.readLine())!=null){	
		//for(int i=0;i<10;i++){
			Data data = gson.fromJson(readLine, Data.class);
			
			StringBuffer result = new StringBuffer();
			result.append(data.getUser().getId()+"&&");

			result.append(data.getId()+"\t"+1);
			
			PrintStream out = new PrintStream(System.out,true,"UTF-8");
			out.println(result.toString());
			
		}
		
	}

}
