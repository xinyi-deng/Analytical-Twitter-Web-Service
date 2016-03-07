import org.apache.hadoop.conf.*;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HBaseConnection {
    
    public final static byte[] TABLE_NAME = Bytes.toBytes("tweetTable");
    public final static byte[] FAMILY_NAME = Bytes.toBytes("data");
    public final static byte[] COLUMN_NAME_1 = Bytes.toBytes("tweet_id");
    public final static byte[] COLUMN_NAME_2 = Bytes.toBytes("score_txt");
    
    public static HConnection connection;

    public static String query(String rowKey){
    	Configuration configuration = HBaseConfiguration.create();;


    	configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "172.31.58.138");
        configuration.set("hbase.master", "172.31.58.138:60000");
    		
    	System.out.println("Created configuration");
    	
        try {
			connection = HConnectionManager.createConnection(configuration);
		} catch (ZooKeeperConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.println("Connection done");

        
    	
    	HTablePool pool = new HTablePool(configuration, 10);
    	HTableInterface table;
        
    	
    	try {
    		
    		table = pool.getTable(TABLE_NAME);
    		
            //table = connection.getTable(TABLE_NAME);
            //table = new HTable(configuration, TABLE_NAME);
            Get get = new Get(Bytes.toBytes(rowKey));
            
            Result result = table.get(get);
            String values_1 = Bytes.toString(result.getValue(FAMILY_NAME, COLUMN_NAME_1));
            String values_2 = Bytes.toString(result.getValue(FAMILY_NAME, COLUMN_NAME_2));
            String final_values = values_1+values_2;
     
            table.close();
            return final_values;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
//    public static void main(String[] args) throws IOException{
//      System.out.println(query("1223409360&&2014-05-12+23:38:25"));
//    }
    
}