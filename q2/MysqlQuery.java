import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MysqlQuery {
	private Connection conn;
	//private boolean connError = false;
	
	public MysqlQuery(String ipAdress) throws ClassNotFoundException, SQLException{
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://"+ipAdress+":3306/tweet", "tweetuser", "tweetmysql");
//			
//		} catch (Exception e) {
//			System.out.println("MYSQL CONNECTION ERROR...");
//			connError = true;
//		}
		Class.forName("com.mysql.jdbc.Driver");
		//System.out.println("here");
		conn = DriverManager.getConnection("jdbc:mysql://"+ipAdress+":3306/tweet", "tweetuser", "tweetmysql");
		//System.out.println("haha");
	}
	
	public String tweetRecord(String user,String time){
		try {
			//if(connError) return null;
			Statement statement = conn.createStatement();

		String user_time = user+"&&"+time;
		String stmt = "select tweet_id,score_text from tweetRecord where user_time='"+user_time+"';";
		//System.out.println("1");
		
		ResultSet  set = statement.executeQuery(stmt);
		//System.out.println("2");
		StringBuffer result = new StringBuffer();
		while(set.next()){
			//System.out.println("3");
			result.append(set.getString("tweet_id")+":");
			result.append(set.getString("score_text"));
		}
		return result.toString();
		} catch (SQLException e) {
			System.out.println("CREATE MYSQL STATEMENT ERROR...");
			return null;
		}
		
	}
	
	public void closeConn(){
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("CLOSE CONNECTION ERROR...");
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
		MysqlQuery a = new MysqlQuery("52.91.249.229");
		//System.out.println(a.tweetRecord(args[0],args[1]));
		PrintStream out = new PrintStream(System.out,true,"UTF-8");
		out.println(a.tweetRecord("1718747148","2014-05-12+23:38:36"));

	}

}
