package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import model.rec.*;
import view.RealeStateView;

public class ReservationDAO {
	private JFrame frame;
	private PreparedStatement pstmt=null;
	private Connection conn = null;
	private String jdbc_url="jdbc:oracle:thin:@192.168.0.17:1521:kibwa";
	private String user = "bigfarm";
	private String pass = "pass";
	//	private String db_id = "mchee01";
//	private String db_pwd = "1234";
	public ReservationDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, user, pass);
			System.out.println("성공적으로 로딩되었음");
			
		} catch (ClassNotFoundException e) {
			System.out.println("해당 드러이버를 찾을 수 없습니다.");
		} catch (SQLException se) {
			System.out.println("해당 드러이버를 찾을 수 없습니다.");
		}
	}
	public void Inserted(int id,int num, String date,String text) {
		String sql = "insert into sell_concel_reserve(scr_num,USERNUM,scr_date,sell_num,scr_content) values(scr_seq.nextval,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, date);
			pstmt.setInt(3, num);
			pstmt.setString(4, text);
			pstmt.executeUpdate();
			
	    	pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public int selectNum(String id) {
		 
		 int number = 0;
		 String sql = "select USERNUM  from USER_MANAGE where USER_ID = ?";
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				number = rs.getInt("USERNUM");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return number;
	 }
//    int selNum(String name) {
//        String sql = "select s.sell_num from sell s where s.sell_categpry_num=(select e.sell_categpry_num from sell_category e where e.sell_name = ?)";
//        int number = 0;
//        try {
//           pstmt = conn.prepareStatement(sql);
//           pstmt.setString(1, name);
//           ResultSet rs=pstmt.executeQuery();
//           if(rs.next()) {
//              number = rs.getInt(1);
//           }
//        } catch (SQLException e) {
//           // TODO Auto-generated catch block
//           e.printStackTrace();
//        }
//        
//        return number;
//     }
//    
	
}
