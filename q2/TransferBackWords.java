import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

/*
 * This class reads in all words need to be censored and stored them into a hashmap.
 */
public class TransferBackWords {
	
	public HashSet<String> readAndAdd() throws IOException{
		HashSet<String> badWords = new HashSet<String>();
		try{
		//InputStream in = this.getClass().getResourceAsStream("oldwords.txt");
		BufferedReader bufferedReader = new BufferedReader(new FileReader("oldwords.txt"));
        String line = null;
        
        while((line = bufferedReader.readLine()) != null) {
            int lineLength = line.length();
            StringBuilder afterLine = new StringBuilder();
            
            for (int i = 0; i < lineLength; i++){
            	char curChar = line.charAt(i);
            	
            	if (isCharacter(curChar)){
            		
            		if (curChar >= 110){
            			curChar -= 13;
            		}
            		else{
            			curChar += 13;
            		}
            	}
            	
            	afterLine.append(curChar);          	
            }     
            badWords.add(afterLine.toString());                    
        }
        return badWords;
		}catch (Exception e){
			return badWords;
		}
	}
	
	
	public boolean isCharacter(char c){	
		if (c >= 97 && c <= 122)
			return true;
		return false;
	}
	
	
}
