package model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.rec.LoginVO;

public class JoinDAO {
	
	public JoinDAO() throws Exception{
		connectDB();
	}
	Connection con;
//	String url = "jdbc:oracle:thin:@192.168.0.17:1521:kibwa";
    String url = "jdbc:oracle:thin:@localhost:1521:kibwa";
    String user = "bigfarm";
//    String user = "javadb";
    String pass = "pass";
    Statement stmt = null;
    PreparedStatement ps = null;
    String driver = "oracle.jdbc.driver.OracleDriver";
	
	 void  connectDB() throws Exception {
         /*
               1. 드라이버를 드라이버메니저에 등록
               2. 연결 객체 얻어오기
         */
   	
   		Class.forName(driver);
       	con = DriverManager.getConnection(url,user,pass);
   }
//회원가입
	 public void regist(LoginVO vo)throws Exception{
         /*
               1.  sql 작성하기      ( insert 문 작성 : 멤버필드를 변수로 지정하여 )
               2.  sql  전송객체 얻어오기   ( PreparedStatement가 더 적합할 듯 )
               3.  sql  전송         ( 전송전에 ?로 지정 )
               4.  닫기            ( Connection은 닫으면 안됨 )
         */
		 String id = vo.getId();
		 String pwd = vo.getPwd();
		 String name = vo.getName();
		 String jumin = vo.getJumin();
		 String phonenum = vo.getPhoneNum();
		 int position = vo.getPosition();
		 
		 
   	String sql = "insert into USER_MANAGE(USERNUM,USER_ID,USER_PWD,NAME,REGISTRATIONNUM, PHONENUM, CATEGORYNUM)values(user_seq.nextval,?,?,?,?,?,?)";
   	ps = con.prepareStatement(sql);
   	ps.setString(1, id);
   	ps.setString(2, pwd);
   	ps.setString(3, name);
   	ps.setString(4, jumin);
   	ps.setString(5, phonenum);
   	ps.setInt(6, position);
   	
   	ps.executeUpdate();
   	ps.close();

   }
	 public void manageRegist(LoginVO vo) throws Exception{
		 String id = vo.getId();
		 String pwd = vo.getPwd();
		 String gi = vo.getGi();
		 String gibun = vo.getGibun();
		 
		 
		 String sql = "insert into HOST_ORGANIZATION(HO_NUM,HO_ID, HO_PWD, HO_NAME, HO_TEL)values(ho_seq.nextval,?,?,?,?)";
		 ps = con.prepareStatement(sql);
		   	ps.setString(1, id);
		   	ps.setString(2, pwd);
		   	ps.setString(3, gi);
		   	ps.setString(4, gibun);
		   	
		   	ps.executeUpdate();
		   	ps.close();
	 }
	 //회원 로그인
	 public boolean login(String id, String pwd, int position){
		 boolean i = false;
		 String sql = "select user_id,user_pwd, CATEGORYNUM from USER_MANAGE where USER_ID = ? and CATEGORYNUM = ?";
		 try {
			ps = con.prepareStatement(sql);
			 ps.setString(1, id);
			 ps.setInt(2, position);
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
				 LoginVO vo = new LoginVO();
				 vo.setId(rs.getString("user_id"));
				 vo.setPwd(rs.getString("user_pwd"));
				 String gpwd = rs.getString("user_pwd");
				 int gposition = rs.getInt("CATEGORYNUM");
				   if(pwd.equals(gpwd) && position == gposition) {
					 i = true;
				   } else {
					   i = false;
				   }
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return i;
	 }
	 public boolean managelogin(String id, String pwd){
		 boolean i = false;
		 String sql = "select HO_ID,HO_PWD from HOST_ORGANIZATION where HO_ID = ? and HO_PWD = ?";
		 try {
			ps = con.prepareStatement(sql);
			 ps.setString(1, id);
			 ps.setString(2, pwd);
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
				 LoginVO vo = new LoginVO();
				 vo.setId(rs.getString("HO_id"));
				 vo.setPwd(rs.getString("HO_pwd"));
				 String gid = rs.getString("HO_ID");
				 String gpwd = rs.getString("HO_PWD");
				 
				   if(pwd.equals(gpwd)&& id.equals(gid)) {
					 i = true;
				   } else {
					   i = false;
				   }
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return i;
	 }
	 

	 
	 //id찾기
	 public LoginVO idsearch(String name, String jumin, String phone) throws Exception{
		 String sql = "select user_id, PHONENUM from USER_MANAGE where name = ? and REGISTRATIONNUM = ? and phonenum = ?";
		
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, jumin);
				ps.setString(3, phone);
				ResultSet rs = ps.executeQuery();
				
				LoginVO vo = new LoginVO();
				if(rs.next()) {
					vo.setId(rs.getString("user_id"));
					vo.setPhoneNum(rs.getString("PHONENUM"));
				}
				rs.close();
				ps.close();
			
		 return vo;
	 }
	 
	 public LoginVO pwdsearch(String name, String jumin, String id) throws Exception{
		 String sql = "select user_id, user_pwd from USER_MANAGE where name = ? and REGISTRATIONNUM = ? and user_id = ?";
		
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, jumin);
				ps.setString(3, id);
				ResultSet rs = ps.executeQuery();
				
				LoginVO vo = new LoginVO();
				if(rs.next()) {
					vo.setPwd(rs.getString("user_pwd"));
				}
				rs.close();
				ps.close();
			
		 return vo;
	 }
	 public int selectNum(String name) {
		 
		 int number = 0;
		 String sql = "select categorynum from category where categoryname = ?";
		 
		 try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				number = rs.getInt("categorynum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return number;
	 }
	 

	 

}
