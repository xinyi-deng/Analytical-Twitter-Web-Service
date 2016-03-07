import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class Reducer {

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		//FileReader fileReader = new FileReader("try.txt");
		
		//BufferedReader br = new BufferedReader(fileReader);
		String line = null;

		ArrayList<String> info_list = new ArrayList<String>();

		String previous_tag = "";
		String previous_date = "";
		String previous_time = "";

		boolean isFirstTime = true;

		// time
		info_list.add("");
		// user_id
		info_list.add("");
		// tweet
		info_list.add("");

		int count = 0;

		while ((line = br.readLine()) != null) {
			//System.out.println("haha");
			String[] tmp = line.split("\t");
			// fault detection
			if (tmp.length <= 1)
				continue;
			// deal with tag
			String tag_date = tmp[0];
			int endIndex = tag_date.length();
			String date = tag_date.substring(endIndex-10, endIndex);
			String tag = tag_date.substring(0,endIndex-10);

			// deal with date and time
			String time = tmp[1];
			

			// deal with usr_id;
			String usr_id = tmp[2];

			// deal with the tweet
			String tweet = tmp[3];

			// if info_list is empty, create the first entry
			if (isFirstTime) {
				previous_date = date;
				previous_time = time;
				previous_tag = tag;
				count = 1;

				// also should set the info_list
				info_list.set(0, time);
				info_list.set(1, usr_id);
				info_list.set(2, tweet);

				// set first time to false

				isFirstTime = false;

			}

			else {
				
				// now we have data in the info_list already
				// see if this line's hashtag is the same as the previous one.
				if (tag.equals(previous_tag)) {
					
					// now we compare their date, if date changes, we print out
					// everything
					if (date.equals(previous_date)) {
						
						// first, we get the current user IDs.
						String cur_usr_id = info_list.get(1);
						cur_usr_id += (","+usr_id);
						info_list.set(1, cur_usr_id);
						
						// do update if needed
						if (time.compareTo(previous_time) < 0) {
							
							previous_time = time;
							// set the new tweet
							info_list.set(2, tweet);
							// set the new time
							info_list.set(0, time);
						}

						count += 1;

					}

					else {
						
						// first, we make a result string

						String result = "";

						result += (tag + '\t' + previous_date + '\t');
						result += (String.valueOf(count) + '\t' + info_list.get(1) + '\t' + info_list.get(2));

						// secondly, we clear the count and every fields of
						// info_list
						count = 1;

						info_list.set(0, time);
						info_list.set(1, usr_id);
						info_list.set(2, tweet);

						previous_date = date;
						// set previous_time
						previous_time = time;
						// then we print out every thing
						
						PrintStream out = new PrintStream(System.out, true,
								"UTF-8");
						out.println(result);
						
						
					}

				}

				else {

					// let's print what we have 
					
					String result = "";

					result += (previous_tag + '\t' + previous_date + '\t');
					result += (String.valueOf(count) + '\t' + info_list.get(1) + '\t' + info_list.get(2));

					// secondly, we clear the count and every fields of
					// info_list
					count = 1;

					info_list.set(0, time);
					info_list.set(1, usr_id);
					info_list.set(2, tweet);

					previous_date = date;
					// then we print out every thing

					PrintStream out = new PrintStream(System.out, true,
							"UTF-8");
					out.println(result);
					
					

					
					// set the previous_tag to our new tag
					previous_tag = tag;

					// set previous_time
					previous_time = time;
				}

			}// else isfirsttime
			

		}// end of while
		
		// now end of file, we should print out the last record
		String result = "";

		result += (previous_tag + '\t' + previous_date + '\t');
		result += (String.valueOf(count) + '\t' + info_list.get(1) + '\t' + info_list.get(2));

		PrintStream out = new PrintStream(System.out, true,
				"UTF-8");
		out.println(result);
		
		
	}

}
