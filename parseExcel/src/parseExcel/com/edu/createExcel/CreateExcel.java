package parseExcel.com.edu.createExcel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parseExcel.com.edu.another.db.ConnectionUtil;

/**
 * Éú³ÉExcelÄ£°å
 * @author tery
 *
 */
public class CreateExcel {

	public static void main(String[] args) {
		ConnectionUtil util=new ConnectionUtil();
    	Connection conn=util.getConnection();
    	String sql="select * from t_student where 1=1";
    	try {
    		PreparedStatement pre=conn.prepareStatement(sql);
    		ResultSet rs=pre.executeQuery();
    		Map<Integer, Object> map=null;
    		while(rs.next()){
    			int i=0;
    			map=new HashMap<>();
    			int id=rs.getInt(1);
    			map.put(i++, id);
    			String name=rs.getString(2);
    			map.put(i++, name);
    			int gender=rs.getInt(3);
    			map.put(i++, gender);
    			int age=rs.getInt(4);
    			map.put(i++, age);
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
