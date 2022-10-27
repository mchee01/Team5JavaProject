package model;

import java.awt.*;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//import model.rec.EducationBusinessVO;
import model.rec.EducationUserVO;

public class EducationUserDAO {
//  constructor
	public EducationUserDAO() throws Exception {
		connectDB();
	}

	// ###########################################################
	// DB control method
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:kibwa";
	String user = "bigfarm";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";

	Statement stmt = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	void connectDB() throws Exception {
		/*
		 * 1. 드라이버를 드라이버메니저에 등록 2. 연결 객체 얻어오기
		 */

		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);

	}

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ   

	// 제목 , 대상 , 내용으로 교육사업 검색
	public ArrayList EducationSearch(int sel, String text) throws Exception {

		String[] selcol = { "eb_name", "eb_target", "eb_content" };

		ArrayList list = new ArrayList();
		String sql = "SELECT eb_num, eb_name, eb_bgdate, eb_enddate, "
				+ "eb_person_num , eb_content , eb_target , area_dong_no " + "FROM education_business WHERE "
				+ selcol[sel] + " like '%" + text + "%'";

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ArrayList vo = new ArrayList();
			vo.add(rs.getInt("eb_num"));
			vo.add(rs.getString("eb_name"));
			vo.add(rs.getString("eb_bgdate"));
			vo.add(rs.getString("eb_enddate"));
			vo.add(rs.getInt("eb_person_num"));
			vo.add(rs.getString("eb_content"));
			vo.add(rs.getString("eb_target"));
			vo.add(rs.getInt("area_dong_no"));

			list.add(vo);
		}

		// rs.close();
		stmt.close();

		return list;
	}
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	// 해당하는 교육의 상세정보 보여주기

	public EducationUserVO findByNum(int ebnum) throws Exception {

		EducationUserVO vo = new EducationUserVO();
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

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ   

	// 교육 리스트 출력
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
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	// 교육 신청하기
	public void EducationBusinessAssign(String userName, int eb_Num) throws Exception {

		String sql = "INSERT INTO education_register (er_num , usernum , eb_num , er_date) "
				+ "VALUES (er_seq.nextval, (select usernum from user_manage where user_id = ?), " + eb_Num
				+ ",sysdate)";
		ps = con.prepareStatement(sql);
		ps.setString(1, userName);
		ps.executeUpdate(); // 이걸 해줘야 디비에 반영이 된다.
		System.out.println(sql);
		ps.close();
	}

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ	
	
	// 교육신청 리스트 출력
		public ArrayList showAssignList(String id) throws Exception {

			ArrayList list = new ArrayList();

			String sql = "select edr.er_num, um.name, eb.EB_NAME, edr.er_date from EDUCATION_REGISTER edr, user_manage um, EDUCATION_BUSINESS eb where um.usernum = edr.usernum and edr.eb_num = eb.eb_num and um.USER_ID = '"+id+"'";
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ArrayList temp = new ArrayList();

				temp.add(rs.getInt("er_num"));
				temp.add(rs.getString("name"));
				temp.add(rs.getString("EB_NAME"));
				temp.add(rs.getString("er_date"));
				
				list.add(temp);
			}

			rs.close();
			ps.close();
			return list;
		}
		
		//교육신청 취소
		public void erCancel(int num) throws Exception {
			String sql = "delete education_register where er_num =?";
			ps = con.prepareStatement(sql);

			ps.setInt(1, num);

			ps.executeUpdate();
			ps.close();
		}
		
		
}


