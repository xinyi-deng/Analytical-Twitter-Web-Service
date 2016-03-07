/*
 * This class works with GSON API, which is used to fetch and set
 * fields we are interested in.
 */
public class Data {
	private String id_str;
	private User user;	

	
	/*
	 * This function set tweet id.
	 */
	public void setID(String id_str){
		this.id_str = id_str;
	}

	
	/*
	 * This function set user.
	 */
	public void setUser(User user){
		this.user = user;
	}

	
	/*
	 * This function gets tweet id.
	 */
	public String getId(){
		return id_str;
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
		return str.toString();
		
	}
	
	

}
