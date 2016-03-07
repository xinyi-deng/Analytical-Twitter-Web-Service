import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
 * This class reads in all words with a score, and store them in to hashmap.
 */
public class SentiScore {	
	public HashMap<String,Integer> readAndAdd(){
		HashMap<String, Integer> sentiPoints = new HashMap<String, Integer>();
		try{			
		InputStream in = this.getClass().getResourceAsStream("senti.txt");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line = null;      
        while((line = bufferedReader.readLine()) != null) {        	
        	String[] word_score = line.split("\t");      	
        	String word = word_score[0];
        	int score = Integer.parseInt(word_score[1]);
            sentiPoints.put(word, score);           
        }
        return sentiPoints;	
        }catch (Exception e){
        	return sentiPoints;
        }
		
	}
        
	
	
	
	
	
}
