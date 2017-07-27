package parseExcel.com.edu.another.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionUtil {

	public static final String url = "jdbc:mysql://127.0.0.1/student";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "root";  
    
    private Connection conn;
    public  Connection getConnection(){
    	try {
			Class.forName(name);
			conn=DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return conn;
    }
    
    public  void close(){
    	if(conn!=null){
    		try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    public static void main(String[] args) {
    	ConnectionUtil util=new ConnectionUtil();
    	Connection conn=util.getConnection();
    	String sql="select * from t_student where 1=1";
    	try {
    		PreparedStatement pre=conn.prepareStatement(sql);
    		ResultSet rs=pre.executeQuery();
    		while(rs.next()){
    			System.out.println(rs.getString(2));
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
