import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Reducer {

	public static void main(String[] args) throws IOException{
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		String twitID = "";
		String userID="";
		int count = 0;
		String newLine = "";
		while ((newLine = reader.readLine()) != null){
			
			String newUserID = newLine.split("\t")[0].split("&&")[0];
			String newTwitID = newLine.split("\t")[0].split("&&")[1];
			if(newUserID.compareTo(userID)!=0){
				if(userID.length()!=0){
					String result = userID+"\t"+count;
					PrintStream out = new PrintStream(System.out, true, "UTF-8");
					out.println(result);				
				}
				
				
				userID = newUserID;
				count=1;
			}else{
				if(newTwitID.compareTo(twitID)!=0){
					count++;
				}else{
					continue;
				}
			}
			twitID = newTwitID;			
		}
		String result = userID+"\t"+count;
		PrintStream out = new PrintStream(System.out, true, "UTF-8");
		out.println(result);			

	}

}
