package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.rec.farmVO;
import model.rec.sidoVO;

public class FarmDAO {
	Connection con;
//	String url = "jdbc:oracle:thin:@192.168.0.17:1521:kibwa";
    String url = "jdbc:oracle:thin:@localhost:1521:kibwa";
//    String user = "javadb";
    String user = "bigfarm";
    String pass = "pass";
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
    String driver = "oracle.jdbc.driver.OracleDriver";
    
    
    public FarmDAO(){
       connectDB();
    }
    public void  connectDB() {
          /*
                1. 드라이버를 드라이버메니저에 등록
                2. 연결 객체 얻어오기
          */try {
    		Class.forName(driver);
        	con = DriverManager.getConnection(url,user,pass);
          }catch (ClassNotFoundException e) {
          }catch(SQLException e) {
        	  
          }
          
    	
    }
    public void disconnectDB() {

        try {

                 if(ps != null) ps.close();

                 if(rs != null) rs.close();

                 if(con != null) con.close();

        } catch (SQLException e) {

        }

    }
    
    public ArrayList searchSido() {

        ArrayList sidoList = new ArrayList();

        
            try {
                 String sql = "select distinct(AREA_SIDO_NAME) from AREA_SIDO";
                 System.out.println("시도");

                 ps = con.prepareStatement(sql);

                 rs = ps.executeQuery();

                 while(rs.next()){
                	

                     sidoVO vo = new sidoVO();

//                         sv.setSidoName(rs.getString("AREA_SIDO_NAME"));
//                         temp.add(sv);
                	 vo.setSidoName(rs.getString("AREA_SIDO_NAME"));
                     sidoList.add(vo);
                     

                 }
                 rs.close();
      		     ps.close();
            }catch (SQLException e) {
            	JOptionPane.showMessageDialog(null, "searchSido 안돼 " + e.getMessage());
            }


        return sidoList;

       

    }
    public ArrayList<sidoVO> searchGugun(String sido) {

        ArrayList<sidoVO> gugunList = new ArrayList<>();

       

        try {

                 String sql = "select distinct(gungu.SIGUNGU_NAME) from AREA_SIDO si, AREA_SIGUNGU gungu where si.AREA_SIDO_NO = gungu.AREA_SIDO_NO and si.AREA_SIDO_NAME = \'" + sido + "\' ";

                 ps = con.prepareStatement(sql);

                 rs = ps.executeQuery();

                 while(rs.next()){

                         sidoVO sicode = new sidoVO();

                         sicode.setSigunguName(rs.getString("SIGUNGU_NAME"));

                         gugunList.add(sicode);

                 }

        } catch (SQLException e) {

        }

                        

        return gugunList;         

    }
    public ArrayList<sidoVO> searchDong(String sido, String gugun) {

        ArrayList<sidoVO> dongList = new ArrayList<>();

       

        try {

                 String sql = "select distinct(dong.AREA_DONG_NAME) from AREA_DONG dong, AREA_SIGUNGU gungu, AREA_SIDO si where si.AREA_SIDO_NO = gungu.AREA_SIDO_NO and gungu.AREA_SIGUNGU_NO = dong.AREA_SIGUNGU_NO and si.AREA_SIDO_NAME = \'" + sido + "\'  and gungu.SIGUNGU_NAME = \'" + gugun + "\'";

                 ps = con.prepareStatement(sql);

                 rs = ps.executeQuery();

                 while(rs.next()){

                	     sidoVO sicode = new sidoVO();

                         sicode.setDongName(rs.getString("AREA_DONG_NAME"));

                         dongList.add(sicode);

                 }

        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "search동 안돼 " + e.getMessage());

        }

       

       

        return dongList;          

}
    
    
    
    public ArrayList projSearch(String sido, String sigun) throws Exception
	   {
	      /*============================================
	      비디오 검색하기

	         1.  sql 작성하기         ( select 문 작성 : 조건 지정하여 )
	            # SELECT videoNum, videoJanre, videoTitle, videoDirector, videoActor, videoRegDate FROM videoTab WHERE key값 like '%word값%'

	         2.  sql  전송객체 얻어오기   ( Statement여야함 )
	         3.  sql  전송            ( executeQuery() 이용 )
	                              ( 결과 받아 Vector 에 지정 )
	         4.  닫기            ( Connection은 닫으면 안됨 )
	      */
		   
    	String sql = "select ad.area_dong_name, wp.wp_name, wp.wp_bgdate, wp.wp_enddate, wp.participants from workconnection_project wp, farm f, area_dong ad where\r\n"
    			+ "wp.farm_num = f.farm_num and\r\n"
    			+ "f.area_dong_no = ad.area_dong_no and\r\n"
    			+ " wp.farm_num in \r\n"
    			+ "(select farm_num from farm where area_dong_no in \r\n"
    			+ "(select area_dong_no from area_dong where area_sigungu_no = (select area_sigungu_no from area_sigungu where sigungu_name = '"+sigun+"')))";
    	stmt = con.createStatement();
    	rs = stmt.executeQuery(sql);
	       ArrayList list   = new ArrayList();
		   while(rs.next()) {
			   ArrayList temp = new ArrayList();
			   
			   temp.add(rs.getString(1));
			   temp.add(rs.getString("WP_NAME"));
			   temp.add(rs.getString("WP_BGDATE"));
			   temp.add(rs.getString("WP_ENDDATE"));
			   temp.add(rs.getString("PARTICIPANTS"));
			   list.add(temp);
		   }
		   rs.close();
		   stmt.close();
	      return list;
	   }
    
    public ArrayList edubSearch(String sido, String sigun) throws Exception
	   {
	      /*============================================
	      비디오 검색하기

	         1.  sql 작성하기         ( select 문 작성 : 조건 지정하여 )
	            # SELECT videoNum, videoJanre, videoTitle, videoDirector, videoActor, videoRegDate FROM videoTab WHERE key값 like '%word값%'

	         2.  sql  전송객체 얻어오기   ( Statement여야함 )
	         3.  sql  전송            ( executeQuery() 이용 )
	                              ( 결과 받아 Vector 에 지정 )
	         4.  닫기            ( Connection은 닫으면 안됨 )
	      */
		   
		   String sql = "select ad.AREA_DONG_NAME,eb.EB_NAME,eb.EB_BGDATE,eb.EB_ENDDATE,eb.EB_PERSON_NUM from education_business eb, area_dong ad where eb.area_dong_no = ad.area_dong_no and eb.area_dong_no in (select area_dong_no from area_dong where area_sigungu_no = (select area_sigungu_no from area_sigungu where sigungu_name = '"+sigun+"'))";
		   stmt = con.createStatement();
		   rs = stmt.executeQuery(sql);
	       ArrayList list   = new ArrayList();
		   
		   while(rs.next()) {
			   ArrayList temp = new ArrayList();
			   
			   temp.add(rs.getString("AREA_DONG_NAME"));
			   temp.add(rs.getString("EB_NAME"));
			   temp.add(rs.getString("EB_BGDATE"));
			   temp.add(rs.getString("EB_ENDDATE"));
			   temp.add(rs.getInt("EB_PERSON_NUM"));
			   list.add(temp);
		   }
		   rs.close();
		   stmt.close();
	      return list;
	   }
    public int dongNum(String dong) {
    	int number = 0;
    	String sql = "select AREA_DONG_NO from AREA_DONG where AREA_DONG_NAME = ?";
    	try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dong);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				number = rs.getInt("AREA_DONG_NO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return number;
    	
    }
    
    public void insertFarm(String name, String use, int dongnum) throws Exception{
    	
    	String sql = "insert into FARM(FARM_NUM,FARM_NAME,FARM_USE,AREA_DONG_NO,FARM_STATE,FARM_DATE)"
    			+"values(farm_seq.nextval,?,?,?,'신청중',sysdate)";
    	ps = con.prepareStatement(sql);
    	ps.setString(1,name);
    	ps.setString(2, use);
    	ps.setInt(3, dongnum);
    	ps.executeUpdate();
	    ps.close();
    }

}
