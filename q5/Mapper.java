import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

/*
 * This class define mapping process. It takes JSON format file, fetch 
 * a certain columns, and output formated string to system out.
 */
public class Mapper {
	private static String date;
	
	/*
	 * This function takes in a time, and change it to a specific format.
	 */
	private static void parseTime(String time) throws ParseException{
		SimpleDateFormat sdformat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
		SimpleDateFormat sdformat2 = new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss");
		date=sdformat2.format(sdformat.parse(time));
	}
	


	public static void main(String[] args) throws JsonSyntaxException, IOException{
		//try{
		Gson gson = new Gson();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
		//BufferedReader reader = new BufferedReader(new FileReader("jasonTry.csv"));
		//BufferedReader reader = new BufferedReader(new FileReader("try2.txt"));
		String readLine = "";
		while((readLine = reader.readLine())!=null){	
		//for(int i=0;i<10;i++){
			//System.out.println(readLine);
			Data data = gson.fromJson(readLine, Data.class);
			//System.out.println("haha");
			//System.out.println(data.getTime());
			//System.out.println(data.getText());
			//System.out.println(data.getUser().getId());
			try{
				parseTime(data.getTime());
			}catch (Exception e){
				continue;
			}
			//System.out.println("1");
			Hashtags[] hashtags = data.getEntities().getHashtag();
			int size = hashtags.length;
			if(size==0) continue;
			//System.out.println("2");
			StringBuffer result = new StringBuffer();
			String[] dates = date.split("\\+");
			result.append(dates[0]+"\t");	
			result.append(dates[1]+"\t");
			result.append(data.getUser().getId()+"\t");
			result.append(data.getText().replace("\\", "\\\\").replace("\n", "\\n").replace("\t", "\\t").replace("\r", "\\r"));
			PrintStream out = new PrintStream(System.out,true,"UTF-8");
			//System.out.println("3");
			for(int i=0;i<size;i++){
				out.println(hashtags[i].getText()+result.toString());
			}			
		}
//		}catch (Exception e){
//			return;
//		}
	}

}
