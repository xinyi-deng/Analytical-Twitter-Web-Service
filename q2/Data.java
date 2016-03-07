/*
 * This class works with GSON API, which is used to fetch and set
 * fields we are interested in.
 */
public class Data {
	private String created_at;
	private String id_str;
	private String text;
	private User user;	
	
	/*
	 * This function set time tweet created.
	 */
	public void setTime(String created_at){
		this.created_at = created_at;
	}
	
	/*
	 * This function set tweet id.
	 */
	public void setID(String id_str){
		this.id_str = id_str;
	}
	
	/*
	 * This function set tweet text.
	 */
	public void setText(String text){
		this.text = text;
	}
	
	/*
	 * This function set user.
	 */
	public void setUser(User user){
		this.user = user;
	}
	
	/*
	 * This function get time tweet created.
	 */
	public String getTime(){
		return created_at;
	}
	
	/*
	 * This function gets tweet id.
	 */
	public String getId(){
		return id_str;
	}
	
	public String getText(){
		return text;
	}
	
	/*
	 *  This function gets user.
	 */
	public User getUser(){
		return user;
	}
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("User ID:");
		str.append(user.getId());
		str.append(" Tweet ID:");
		str.append(id_str);
		str.append(" Time:");
		str.append(created_at);
		str.append(" Text:");
		str.append(text);
		return str.toString();
		
	}
	
	

}
