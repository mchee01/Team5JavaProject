package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.ReviewVO;


public class ReviewDAO {
	


	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:kibwa";
	String user = "bigfarm";
	String pass = "pass";
	Statement stmt = null;
	PreparedStatement ps = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	
	
	void connectDB() throws Exception{
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,user,pass);
	}
	
	public ReviewDAO() throws Exception
	{
		connectDB();
	}
	
	public void reviewInsert(String grade, String content, String title, String prjName, String userName) throws Exception
	{
		String sql = "insert into workconnection_review(reviewnum, usernum, projectnum, grade, reviewcontent, reviewtitle) "
												+"values(review_seq.nextval, (select usernum from user_manage where user_id = '" + userName + "') , (select project_num from workconnection_project where wp_name = '" + prjName + "') , ?, ?, ?)";
		
		ps = con.prepareStatement(sql);
		ps.setString(1, grade);
		ps.setString(2, content);
		ps.setString(3, title);
		
		ps.executeUpdate();
	
		ps.close();
	}
	
	public ArrayList showReviewlist(String prjName) throws Exception {
	      ArrayList list = new ArrayList();
	      String sql = "select reviewnum, usernum, grade, reviewtitle from workconnection_review where PROJECTNUM = (select project_num from workconnection_project where wp_name = '" + prjName +"')";
	      
	      System.out.println("reviewDao prjName : " + prjName);
	      ps = con.prepareStatement(sql);
	      ResultSet rs = ps.executeQuery();
	      System.out.println("rs : " +rs);
	      
	      while(rs.next()) {
	         ArrayList temp = new ArrayList();
	         temp.add(rs.getInt("reviewnum"));
	         temp.add(rs.getInt("usernum"));
	         temp.add(rs.getString("grade"));
	         temp.add(rs.getString("reviewtitle"));
	         list.add(temp);
	      }
	      rs.close();
	      ps.close();
	      return list;
	   }
	
	public ReviewVO findByNum( int rnum ) throws Exception{
		
		System.out.println("rnum : " + rnum);
        
        ReviewVO vo= new ReviewVO();
        stmt = con.createStatement();
        String sql = "select reviewnum, reviewtitle, grade, reviewcontent from workconnection_review where reviewnum = "+ rnum;
        
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()) {
        	
        	vo.setReviewnum(rs.getInt("reviewnum"));
        	vo.setReviewtitle(rs.getString("reviewtitle"));
        	vo.setGrade(rs.getString("grade"));
        	vo.setReviewcontent(rs.getString("reviewcontent"));
        	
        	
        	System.out.println(rs.getInt("reviewnum"));
          //title
           
        }
        rs.close();
        stmt.close();
        
        System.out.println("vo : " + vo);
        
        return vo;
     }

	public void reviewUpdate(ReviewVO vo, int num) throws Exception {
		
		String sql = "update workconnection_review set reviewcontent = ?, grade = ?, reviewtitle = ? where reviewnum = " + num;

		ps = con.prepareStatement(sql);
		
		ps.setString(1, vo.getReviewtitle());
		ps.setString(2, vo.getReviewcontent());
		ps.setString(3, vo.getGrade());
		
		ps.executeUpdate();
		ps.close();
		
	}
	
	
	public void reviewDelete(int num) throws Exception {
		
		String sql = "delete workconnection_review where reviewnum = ?";
		
		ps = con.prepareStatement(sql);
		ps.setInt(1, num);
		
		ps.executeUpdate();
		ps.close();
		
	}

	public ArrayList searchReview(String grade, String prjName) throws Exception {
		
		
		String sql = "select reviewnum, usernum, grade, reviewtitle from workconnection_review where grade " + "like '%" + grade + "%' and PROJECTNUM = (select PROJECT_NUM from workconnection_project where wp_name = '" + prjName + "')" ;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();
		
		while(rs.next()) {
			
			ArrayList temp = new ArrayList();
			
			temp.add(rs.getInt("reviewnum"));
			temp.add(rs.getInt("usernum"));
			temp.add(rs.getString("grade"));
			temp.add(rs.getString("reviewtitle"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
		
	}
	
public ArrayList searchReview(String prjName) throws Exception {
		
		
		String sql = "select reviewnum, usernum, grade, reviewtitle from workconnection_review where PROJECTNUM = (select PROJECT_NUM from workconnection_project where wp_name = '" + prjName + "')" ;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();
		
		while(rs.next()) {
			
			ArrayList temp = new ArrayList();
			
			temp.add(rs.getInt("reviewnum"));
			temp.add(rs.getInt("usernum"));
			temp.add(rs.getString("grade"));
			temp.add(rs.getString("reviewtitle"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
		
	}
}
