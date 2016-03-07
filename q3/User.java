/*
 * This class works with GSON API, which is used to fetch and set
 * fields we are interested in.
 */
public class User {
	private String id_str;
	private long followers_count;
	
	/*
	 * This function sets user id.
	 */
	public void setId(String id_str){
		this.id_str = id_str;
	}
	
	public void setCount(String followers_count){
		this.followers_count = Long.parseLong(followers_count);
	}
	
	/*
	 * This function sets tweet id.
	 */
	public String getId(){
		return id_str;
	}
	
	public Long getCount(){
		return followers_count;
	}
}
