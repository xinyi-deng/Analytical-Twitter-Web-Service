import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;


public class Censored {
	private static HashSet<String> badWords;
	
	private static  String parseText(String text){
		StringBuffer censoredText = new StringBuffer();
		int size = text.length();
		StringBuffer sbf = new StringBuffer();
		for(int i=0;i<size;i++){
			if(isValid(text.charAt(i))) sbf.append(text.charAt(i));
			else{			
				if(sbf.length()!=0){
					String word = sbf.toString();
					String lowerStr = word.toLowerCase();
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
			if (badWords.contains(lowerStr)){
				for(int j=1;j<sbf.length()-1;j++){
					sbf.setCharAt(j, '*');
				}
				censoredText.append(sbf.toString());
			}else censoredText.append(word);	
		}
		return censoredText.toString();
	}
	
	private static boolean isValid(char a){
		if((a>=48 && a<=57)||(a>=65 && a<=90)||(a>=97 && a<=122)) return true;
		else return false;
	}
	
	public static void main(String[] args) throws IOException{
		TransferBackWords tfWords = new TransferBackWords();
		badWords = tfWords.readAndAdd();
		//System.out.println(badWords);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
		String line = "";
		while((line=reader.readLine())!=null){
			String result = parseText(line);
			PrintStream out = new PrintStream(System.out,true,"UTF-8");
			out.println(result.toString());
		
	}
	}
}
