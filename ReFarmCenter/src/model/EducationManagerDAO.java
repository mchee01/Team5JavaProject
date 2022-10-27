
package model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.rec.EducationManagerVO;

public class EducationManagerDAO {

   public EducationManagerDAO() throws Exception {
      connectDB();
   }

   Connection con;
//	String url = "jdbc:oracle:thin:@192.168.0.17:1521:kibwa";
   String url = "jdbc:oracle:thin:@localhost:1521:kibwa";
// String user = "javadb";
   String user = "bigfarm";
   String pass = "pass";
   java.sql.Statement stmt = null;
   PreparedStatement ps = null;
   String driver = "oracle.jdbc.driver.OracleDriver";

   void connectDB() throws Exception {

      Class.forName(driver);
      con = DriverManager.getConnection(url, user, pass);

   }

   public ArrayList showEducationList() throws Exception {
      ArrayList list = new ArrayList();
      String sql = "select eb.eb_num, eb.eb_name, eb.eb_bgdate, eb.eb_enddate, eb.eb_person_num, eb.eb_content, eb.eb_target, ad.area_dong_name\r\n"
      		+ "from education_business eb, area_dong ad\r\n"
      		+ "where eb.area_dong_no = ad.area_dong_no";
      ps = con.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
         ArrayList temp = new ArrayList();
         temp.add(rs.getInt("eb_num"));
         temp.add(rs.getString("eb_name"));
         temp.add(rs.getString("eb_bgdate"));
         temp.add(rs.getString("eb_enddate"));
         temp.add(rs.getInt("eb_person_num"));
         temp.add(rs.getString("eb_content"));
         temp.add(rs.getString("eb_target"));
         temp.add(rs.getString("area_dong_name"));
         list.add(temp);
      }
      rs.close();
      ps.close();
      return list;
   }

   public ArrayList searchName(String name) throws Exception {
      String sname = name;

      String sql = "select eb_name,eb_bgdate,eb_enddate,eb_perosn_num,eb_content,eb_target,area_dong_no from education_business where eb_name=?";
      ps = con.prepareStatement(sql);
      ps.setString(1, sname);
      ResultSet rs = ps.executeQuery();

      ArrayList list = new ArrayList();

      while (rs.next()) {
         EducationManagerVO vo = new EducationManagerVO();
         vo.setName(rs.getString("eb_name"));
         vo.setStartDate(rs.getString("eb_bgdate"));
         vo.setEndDate(rs.getString("eb_enddate"));
         vo.setParticipants(rs.getInt("eb_perosn_num"));
         vo.setContent(rs.getString("eb_content"));
         vo.setTarget(rs.getString("eb_target"));
         vo.setLocation(rs.getString("area_dong_no"));

         list.add(vo);
      }
      rs.close();
      ps.close();

      return list;

   }

   public ArrayList educationSearch(int sel, String text) throws Exception {

      String[] selCol = { "eb_name", "eb_target", "eb_content" };
      ArrayList list = new ArrayList();

      String sql = "SELECT " + " eb_num, eb_name, eb_bgdate, eb_enddate,"
            + "eb_content, area_dong_no, eb_target,eb_person_num " + "FROM education_business " + "WHERE "
            + selCol[sel] + " like '%" + text + "%'";

      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
         ArrayList temp = new ArrayList();
         temp.add(rs.getInt("eb_num"));
         temp.add(rs.getString("eb_name"));
         temp.add(rs.getString("eb_bgdate"));
         temp.add(rs.getString("eb_enddate"));
         temp.add(rs.getString("eb_content"));
         temp.add(rs.getInt("area_dong_no"));
         temp.add(rs.getString("eb_target"));
         temp.add(rs.getInt("eb_person_num"));
         list.add(temp);
      }
      rs.close();
      stmt.close();
      return list;
   }

   public void regist(EducationManagerVO vo) {

      String sql = "insert into EDUCATION_BUSINESS " + "(eb_num, eb_name, eb_bgdate , eb_enddate , eb_content, "
            + "area_dong_no, eb_target, eb_person_num) values (eb_seq.nextval , ?, substr(?,0,10), substr(?,0,10), ?, ?, ?, ?)";
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, vo.getName());
         ps.setString(2, vo.getStartDate());
         ps.setString(3, vo.getEndDate());
         ps.setString(4, vo.getContent());
         ps.setString(5, vo.getLocation());
         ps.setString(6, vo.getTarget());
         ps.setInt(7, vo.getParticipants());
         ps.executeUpdate();
         ps.close();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         System.out.println(e.getMessage());
      }

   }

   public void modify(EducationManagerVO vo, int eb_num) throws Exception {

      String sql = "update EDUCATION_BUSINESS set eb_name=?,eb_bgdate=?,eb_enddate=?,eb_content=?, area_dong_no=?, eb_target=?, eb_person_num=? where eb_num=" + eb_num;
      
      ps = con.prepareStatement(sql);
      ps.setString(1, vo.getName());
      ps.setString(2, vo.getStartDate());
      ps.setString(3, vo.getEndDate());
      ps.setString(4, vo.getContent());
      ps.setString(5, vo.getLocation());
      ps.setString(6, vo.getTarget());
      ps.setInt(7, vo.getParticipants());

      ps.executeUpdate();

      ps.close();

   }

   public void ebDelete(int num) throws Exception {
      String sql = "delete education_business where eb_num =?";
      ps = con.prepareStatement(sql);

      ps.setInt(1, num);

      ps.executeUpdate();
      ps.close();
   }

   public EducationManagerVO findByNum(int ebnum) throws Exception {

      EducationManagerVO vo = new EducationManagerVO();
      String sql = "SELECT * FROM education_business WHERE eb_num = " + ebnum;
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
         vo.seteb_num(rs.getInt("eb_Num"));
         vo.setName(rs.getString("eb_name"));
         vo.setStartDate(rs.getString("EB_BGDATE"));
         vo.setEndDate(rs.getString("eb_endDate"));
         vo.setContent(rs.getString("eb_content"));
         vo.setTarget(rs.getString("eb_target"));
         vo.setLocation(rs.getString("AREA_DONG_NO"));
         vo.setParticipants(rs.getInt("EB_PERSON_NUM"));
      }
      rs.close();
      stmt.close();

      return vo;
   }

   public void educationDelete(int num) throws Exception {
      String sql = "delete education_business where eb_num =?";
      ps = con.prepareStatement(sql);

      ps.setInt(1, num);

      ps.executeUpdate();
      ps.close();
   }
}