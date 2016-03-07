import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/*
 * This class define mapping process. It takes JSON format file, fetch 
 * a certain columns, and output formated string to system out.
 */
public class Mapper {
	private static HashMap<String,Integer> scoreMap;
	private static HashSet<String> badWords;
	private static int score;
	private static StringBuffer censoredText;
	private static String date;
	
	/*
	 * This function take in text, calculate score and rewrite text if have 
	 * censored text.
	 */
	private static void parseText(String text){
		score = 0;
		censoredText = new StringBuffer();
		int size = text.length();
		StringBuffer sbf = new StringBuffer();
		for(int i=0;i<size;i++){
			if(isValid(text.charAt(i))) sbf.append(text.charAt(i));
			else{			
				if(sbf.length()!=0){
					String word = sbf.toString();
					String lowerStr = word.toLowerCase();
					if (scoreMap.containsKey(lowerStr)) score+=scoreMap.get(lowerStr);
					if (badWords.contains(lowerStr)){
						for(int j=1;j<sbf.length()-1;j++){
							sbf.setCharAt(j, '*');
						}
						censoredText.append(sbf.toString());
					}else censoredText.append(word);	
					sbf = new StringBuffer();
				}
				censoredText.append(text.charAt(i));
				
			}
		}		
		if(sbf.length()!=0){
			String word = sbf.toString();
			String lowerStr = word.toLowerCase();
			if (scoreMap.containsKey(lowerStr)) score+=scoreMap.get(lowerStr);
			if (badWords.contains(lowerStr)){
				for(int j=1;j<sbf.length()-1;j++){
					sbf.setCharAt(j, '*');
				}
				censoredText.append(sbf.toString());
			}else censoredText.append(word);	
			sbf = new StringBuffer();
		}
	}
	
	/*
	 * This function checks if a given character is alphanumerical. If it is
	 * return true, else return false;
	 */
	private static boolean isValid(char a){
		if((a>=48 && a<=57)||(a>=65 && a<=90)||(a>=97 && a<=122)) return true;
		else return false;
	}
	
	/*
	 * This function takes in a time, and change it to a specific format.
	 */
	private static void parseTime(String time) throws ParseException{
		SimpleDateFormat sdformat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
		SimpleDateFormat sdformat2 = new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss");
		date=sdformat2.format(sdformat.parse(time));
	}
	


	public static void main(String[] args){
		try{
		TransferBackWords tfWords = new TransferBackWords();
		badWords = tfWords.readAndAdd();
		SentiScore stScore = new SentiScore();
		scoreMap = stScore.readAndAdd();
		Gson gson = new Gson();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
		String readLine = "";
		while((readLine = reader.readLine())!=null){		
			Data data = gson.fromJson(readLine, Data.class);
			try{
			parseTime(data.getTime());
			}catch (Exception e){
				continue;
			}
			if(date.compareTo("2014-04-20+00:00:00")<0) continue;	
			try{
			parseText(data.getText());		
			}catch (Exception e){
				continue;
			}
			StringBuffer result = new StringBuffer();
			result.append(data.getUser().getId()+"&&"+date+"\t");
			result.append(data.getId()+"\t");
			result.append(score+":");
			result.append(censoredText.toString().replace("\\", "\\\\").replace("\n", "\\n").replace("\t", "\\t").replace("\r", "\\r"));		
			
			PrintStream out = new PrintStream(System.out,true,"UTF-8");
			out.println(result.toString());
			
		}
		}catch (Exception e){
			return;
		}
	}

}
