package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.rec.ConsultingVO;
import model.rec.RealeStateVO;
import view.RealeStateView;

public class RealeStateDAO {
	private JFrame frame;
	private PreparedStatement pstmt=null;

	private Connection conn = null;
	private String jdbc_url="jdbc:oracle:thin:@192.168.0.17:1521:kibwa";
	private String user = "bigfarm";
	private String pass = "pass";
//	private String db_id = "mchee01";
//	private String db_pwd = "1234";
	public RealeStateDAO() {
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
	
	
	public ArrayList obSelect() {
		ArrayList list = new ArrayList();
		String sql = "select a.sell_num\"매물번호\",(select b.area_dong_name from area_dong b where a.area_dong_no = b.area_dong_no)\"지역이름\","
				+ "(select c.sell_name from sell_category c where a.sell_categpry_num=c.sell_categpry_num)\"매물이름\","
				+ "(select d.property_name from property d where a.property_num=d.property_num)\"부동산명\","
				+"sell_price,sell_surfacearea from sell a";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
	    	
	    	while(rs.next()) {
	    		ArrayList temp = new ArrayList();
				temp.add(rs.getInt(1));
				temp.add(rs.getString(2));
				temp.add(rs.getString(3));
				temp.add(rs.getString(4));
				temp.add(rs.getString(5));
				temp.add(rs.getString(6));
				list.add(temp);
			}
	    	rs.close();
	    	pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList findByNum(int num) {
		
		ArrayList list= new ArrayList();
		String sql = "select a.sell_num\"매물번호\",(select b.area_dong_name from area_dong b where a.area_dong_no = b.area_dong_no)\"지역이름\",\r\n"
				+ "	(select c.sell_name from sell_category c where a.sell_categpry_num=c.sell_categpry_num)\"매물이름\",\r\n"
				+ "	(select d.property_name from property d where a.property_num=d.property_num)\"부동산명\",\r\n"
				+ "	sell_price,sell_surfacearea from sell a where a.sell_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			ResultSet rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
	    		ArrayList temp = new ArrayList();
				temp.add(rs.getInt(1));
				temp.add(rs.getString(2));
				temp.add(rs.getString(3));
				temp.add(rs.getString(4));
				temp.add(rs.getString(5));
				temp.add(rs.getString(6));
				list.add(temp);
			}
			rs.close();
	    	pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;

	}
	public ArrayList findBydong(String name) {
		ArrayList list= new ArrayList();
		String sql = "select a.sell_num\"매물번호\",(\r\n"
				+ "select b.area_dong_name from area_dong b\r\n"
				+ " where a.area_dong_no = b.area_dong_no)\"지역이름\",\r\n"
				+ "(select c.sell_name from sell_category c where a.sell_categpry_num=c.sell_categpry_num\r\n"
				+ ")\"매물이름\",\r\n"
				+ "(select d.property_name from property d where a.property_num=d.property_num)\"부동산명\",\r\n"
				+ "sell_price,sell_surfacearea from sell a, area_dong b where b.area_dong_name like '%"+name+"%' and a.area_dong_no = b.area_dong_no";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
	    		ArrayList temp = new ArrayList();
				temp.add(rs.getInt(1));
				temp.add(rs.getString(2));
				temp.add(rs.getString(3));
				temp.add(rs.getString(4));
				temp.add(rs.getString(5));
				temp.add(rs.getString(6));
				list.add(temp);
			}
			rs.close();
	    	pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public RealeStateVO selected(int data) {
		RealeStateVO vo = new RealeStateVO();
		String sql="select (select b.area_dong_name from area_dong b where a.area_dong_no = b.area_dong_no),a.sell_price,a.sell_surfacearea from sell a where a.sell_num=?";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, data);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
	 
				vo.setName(rs.getString(1));
				System.out.println("selected"+rs.getString(1));
				vo.setSel_price(rs.getInt(2));
				vo.setArea(rs.getString(3));
				
			}
			System.out.println("getName"+vo.getName());
			rs.close();
	    	pstmt.close();
	    	
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return vo;
		
	}


	
}
