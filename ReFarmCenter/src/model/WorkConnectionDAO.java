package model;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.rec.EducationManagerVO;
import model.rec.WorkConnectionVO;

/**
 * 파일 명 : WorkConnectionDAO
 * 
 * @author : 주민지
 * @date : 2022.09.05
 * @description : (부가 설명, 전달 사항)
 */

public class WorkConnectionDAO {

	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:kibwa";
	String user = "bigfarm";
	String pass = "pass";
	Statement stmt = null;
	PreparedStatement ps = null;
	String driver = "oracle.jdbc.driver.OracleDriver";

	void connectDB() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}

	public WorkConnectionDAO() throws Exception {
		connectDB();
	}

	public ArrayList showProjectlist() throws Exception {
		ArrayList list = new ArrayList();
		String sql = "select project_num, wp_name, wp_bgdate, wp_enddate from workconnection_project";
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		System.out.println("rs : " + rs);
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("PROJECT_NUM"));
			temp.add(rs.getString("WP_NAME"));
			temp.add(rs.getString("WP_BGDATE"));
			temp.add(rs.getString("WP_ENDDATE"));
			list.add(temp);
		}
		rs.close();
		ps.close();
		return list;
	}

	// 특정 관리자 번호에 맞는 프로젝트 리스트를 불러옴
	public ArrayList showManagerProjectlist(String ho_id) throws Exception {
		ArrayList list = new ArrayList();
		String sql = "select wp.project_num, wp.wp_name, wp.wp_bgdate, wp.wp_enddate, wp.farm_num, wp.participants, wp.qualify, wp_content "
				+ "from workconnection_project wp, host_organization_wc how, host_organization ho "
				+ "where wp.project_num = how.project_num and ho.ho_num = how.ho_num and ho.ho_num=(select ho_num from host_organization where ho_id='"
				+ ho_id + "')";
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("PROJECT_NUM"));
			temp.add(rs.getString("WP_NAME"));
			temp.add(rs.getString("WP_BGDATE"));
			temp.add(rs.getString("WP_ENDDATE"));
			temp.add(rs.getString("farm_num"));
			// temp.add(rs.getString("participants"));
			// temp.add(rs.getString("qualify"));
			// content안가져옴
			list.add(temp);
		}
		rs.close();
		ps.close();
		System.out.println(sql);
		return list;
	}

	// 관리자쪽 프로젝트 검색하기
	public ArrayList managerProjectSearch(String ho_id, int sel, String text) throws Exception {
		String[] selCol = { "", "wp_name", "QUALIFY", "wp_content" }; // 나중에 내용 검색을 지역 번호로 변경하기
		ArrayList list = new ArrayList();
		String sql = "select wp.project_num, wp.wp_name, wp.wp_bgdate, wp.wp_enddate, wp.farm_num, wp.participants, wp.qualify, wp_content "
				+ "from workconnection_project wp, host_organization_wc how, host_organization ho "
				+ "where wp.project_num = how.project_num and ho.ho_num = how.ho_num and ho.ho_num=(select ho_num from host_organization where ho_id='"
				+ ho_id + "') and " + selCol[sel] + " like '%" + text + "%'";

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("PROJECT_NUM"));
			temp.add(rs.getString("WP_NAME"));
			temp.add(rs.getString("WP_BGDATE"));
			temp.add(rs.getString("WP_ENDDATE"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
	}

	// 관리자쪽 프로젝트 등록하기
	public void projectRegist(WorkConnectionVO vo, String ho_id, String title) throws Exception {
		String sql = "INSERT INTO WORKCONNECTION_PROJECT "
				+ "(PROJECT_NUM, WP_NAME, WP_BGDATE, WP_ENDDATE, QUALIFY, WP_CONTENT, PARTICIPANTS, FARM_NUM) "
				+ "VALUES(project_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getWs_Title());
		ps.setString(2, vo.getWs_StartDate());
		ps.setString(3, vo.getWs_EndDate());
		ps.setString(4, vo.getWs_Qualify());
		ps.setString(5, vo.getWs_Content());
		ps.setInt(6, vo.getWs_Participants());
		ps.setInt(7, vo.getWs_FarmNum());
		ps.executeUpdate();
		con.commit();

		// host_organiztion_wc 인서트 하는 부분
		String sql2 = "insert into HOST_ORGANIZATION_WC(ho_wc_num, ho_num, project_num) "
				+ "values(ho_wc_seq.nextval, (select ho_num from host_organization where ho_id='" + ho_id
				+ "'), (select project_num from WORKCONNECTION_PROJECT where wp_name = '" + title + "'))";
		ps = con.prepareStatement(sql2);
		ps.executeUpdate();
		con.commit();
		ps.close();
		System.out.println(sql2);
	}

	// 관리자쪽 프로젝트 수정하기
	public void projectModify(WorkConnectionVO vo, int pro_num) throws Exception {
		String sql = "update WORKCONNECTION_PROJECT set WP_NAME=?, WP_BGDATE=?, WP_ENDDATE=?, WP_CONTENT=?, "
				+ "QUALIFY=?, PARTICIPANTS=?, FARM_NUM=? where project_num=" + pro_num;
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getWs_Title());
		ps.setString(2, vo.getWs_StartDate());
		ps.setString(3, vo.getWs_EndDate());
		ps.setString(4, vo.getWs_Content());
		ps.setString(5, vo.getWs_Qualify());
		ps.setInt(6, vo.getWs_Participants());
		ps.setInt(7, vo.getWs_FarmNum());
		ps.executeUpdate();
		con.commit();
		ps.close();
	}

	// 관리자쪽 프로젝트 삭제하기
	public void projectDelete(int pro_num) throws Exception {
		String sql = "delete HOST_ORGANIZATION_WC where project_num = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, pro_num);
		ps.executeUpdate();
		con.commit();

		String sql3 = "delete PROJECT_MATCH_REGIST where project_num = ?";
		ps = con.prepareStatement(sql3);
		ps.setInt(1, pro_num);
		ps.executeUpdate();
		con.commit();

		String sql2 = "delete WORKCONNECTION_PROJECT where project_num = ?";
		ps = con.prepareStatement(sql2);
		ps.setInt(1, pro_num);
		ps.executeUpdate();
		con.commit();

		ps.close();
	}

	// 관리자 페이지 특정 프로젝트 컬럼에 대한 신청자 정보 조회
	public ArrayList showRegisterList(int pro_num) throws Exception {
		ArrayList list = new ArrayList();
		String sql = "select pmr.pmr_num, um.user_id, um.name, um.phonenum from user_manage um, project_match_regist "
				+ "pmr where um.usernum=pmr.usernum and pmr.project_num = " + pro_num;
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString(1));
			temp.add(rs.getString(2));
			temp.add(rs.getString(3));
			temp.add(rs.getString(4));
			list.add(temp);
		}
		rs.close();
		ps.close();
		System.out.println(sql);
		return list;
	}

	// 관리자 페이지 관리자 권한으로 신청자 등록
	public void registRegist(int pro_num, String user_id) throws Exception {
		String sql = "insert into project_match_regist (pmr_num, PROJECT_NUM, USERNUM) "
				+ "values(pmr_seq.nextval, ?, (select usernum from USER_MANAGE where user_id = '" + user_id + "'))";
		ps = con.prepareStatement(sql);
		ps.setInt(1, pro_num);
		ps.executeUpdate();
		con.commit();
		ps.close();
	}

	// 관리자 View 신청자 정보 수정 - 안됨
	public void registModify(String name, String phoneNum, String user_id) throws Exception {
		try {
			String sql = "update user_manage set name= ?, PHONENUM= ? "
					+ "where usernum= (select usernum from user_manage where user_id = '" + user_id + "')";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phoneNum);
			ps.executeUpdate();
			con.commit();
			System.out.println(sql);
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "정보 수정 실패 : " + e.getMessage());
		}
	}

	// 관리자 페이지 신정차 정보 삭제
	public void registDelete(int pro_num, String user_id) throws Exception {
		String sql = "delete PROJECT_MATCH_REGIST where project_num = ? "
				+ "and usernum = (select USERNUM from user_manage where user_id = '" + user_id + "')";
		ps = con.prepareStatement(sql);
		ps.setInt(1, pro_num);
		ps.executeUpdate();
		con.commit();

		ps.close();
		System.out.println(sql);
	}

	// 관리자페이지 신청자 정보 조회(검색)
	public ArrayList registInfoSearch(int pro_num, int sel, String text) throws Exception {
		String[] selCol = { "", "name", "user_id", "phonenum" }; // 나중에 내용 검색을 지역 번호로 변경하기
		ArrayList list = new ArrayList();
		String sql = "select pmr.pmr_num, um.user_id, um.name, um.phonenum from user_manage um, project_match_regist "
				+ "pmr where um.usernum=pmr.usernum and pmr.project_num = " + pro_num + " and " + selCol[sel]
				+ " like '%" + text + "%'";
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString(1));
			temp.add(rs.getString(2));
			temp.add(rs.getString(3));
			temp.add(rs.getString(4));
			list.add(temp);
		}
		rs.close();
		ps.close();
		return list;
	}

	// 관리자 페이지 농가 정보 조회
	public ArrayList showFarmlist() throws Exception {
		ArrayList list = new ArrayList();
		String sql = "select f.farm_num, f.farm_name, (select ad.area_dong_name from area_dong ad where ad.area_dong_no = "
				+ "(select tf.area_dong_no from farm tf where tf.farm_num = f.farm_num)), f.FARM_USE, f.farm_state, f.farm_date from farm f";
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		System.out.println("rs : " + rs);
		while (rs.next()) {
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
		ps.close();
		return list;
	}

	// 관리자페이지 농가 정보 검색
	public ArrayList farmInfoSearch(int sel, String text) throws Exception {
		String[] selCol = { "FARM_NAME", "FARM_USE" }; // 나중에 내용 검색을 지역 번호로 변경하기
		ArrayList list = new ArrayList();
		String sql = "select f.farm_num, f.farm_name, (select ad.area_dong_name from area_dong ad where ad.area_dong_no = "
				+ "(select tf.area_dong_no from farm tf where tf.farm_num = f.farm_num)), f.FARM_USE, f.farm_state, f.farm_date from farm f where "
				+ selCol[sel] + " like '%" + text + "%'";

		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt(1));
			temp.add(rs.getString(2));
			temp.add(rs.getString(3));
			temp.add(rs.getString(4));
			temp.add(rs.getString(5));
			list.add(temp);
		}
		rs.close();
		ps.close();
		return list;
	}

	// 관리자 페이지 농가 수락
	public void farmAssign(int farm_num) throws Exception {
		try {
			String sql = "update farm set FARM_STATE= '신청완료' where farm_num= ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, farm_num);
			ps.executeUpdate();
			con.commit();
			System.out.println(sql);
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "정보 수정 실패 : " + e.getMessage());
		}
	}

	// 관리자 페이지 농가 정보 수정
	public void farmInfoModify(int farm_num, String farm_name, String area_dong_name, String farm_use)
			throws Exception {
		try {
			String sql = "update farm f set f.FARM_NAME = ?, "
					+ "f.area_dong_no = (select area_dong_no from area_dong where area_dong_name = ?), "
					+ "f.FARM_USE = ? where f.farm_num = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, farm_name);
			ps.setString(2, area_dong_name);
			ps.setString(3, farm_use);
			ps.setInt(4, farm_num);
			ps.executeUpdate();
			con.commit();
			System.out.println(sql);
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "정보 수정 실패 : " + e.getMessage());
		}
	}

	// 관리자 페이지 농가 반려
	public void farmDisagree(int farm_num) throws Exception {
		try {
			String sql = "update farm set FARM_STATE= '반려' where farm_num= ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, farm_num);
			ps.executeUpdate();
			con.commit();
			System.out.println(sql);
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "정보 수정 실패 : " + e.getMessage());
		}
	}

	// 사용자 View
	public ArrayList projectSearch(int sel, String text) throws Exception {
		String[] selCol = { "wp_name", "QUALIFY", "wp_content" }; // 나중에 내용 검색을 지역 번호로 변경하기
		ArrayList list = new ArrayList();

		String sql = "select " + "project_num, wp_name, wp_bgdate, wp_enddate " + "from workconnection_project "
				+ "where " + selCol[sel] + " like '%" + text + "%'";

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("PROJECT_NUM"));
			temp.add(rs.getString("WP_NAME"));
			temp.add(rs.getString("WP_BGDATE"));
			temp.add(rs.getString("WP_ENDDATE"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
	}

	public WorkConnectionVO findByNum(int vnum) throws Exception {
		WorkConnectionVO vo = new WorkConnectionVO();
		stmt = con.createStatement();
		String sql = "select wp.PROJECT_NUM, wp.WP_NAME, wp.WP_BGDATE, wp.WP_ENDDATE, wp.QUALIFY, wp.WP_CONTENT, wp.PARTICIPANTS, "
				+ "(select ad.area_dong_name from area_dong ad, farm f where ad.area_dong_no = f.area_dong_no and f.farm_num = wp.farm_num), "
				+ "wf.farm_name, wp.farm_num from workconnection_project wp, farm wf where wp.farm_num = wf.farm_num and wp.project_num = "+ vnum;

		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			// vo.setProject_num(rs.getInt("PROJECT_NUM"));
			vo.setProject_num(rs.getInt(1));
			vo.setWs_Title(rs.getString(2));
			vo.setWs_StartDate(rs.getString(3));
			vo.setWs_EndDate(rs.getString(4));
			vo.setWs_Qualify(rs.getString(5));
			vo.setWs_Content(rs.getString(6));
			vo.setWs_Participants(rs.getInt(7));
			vo.setWs_Location(rs.getString(8));
			vo.setWs_FarmName(rs.getString(9));
			vo.setWs_FarmNum(rs.getInt(10));
		}
		rs.close();
		stmt.close();
		return vo;
	}

	// 사용자쪽 신청하기 버튼
	public void WorkConnectProjetAssign(String userName, int projectNum) throws Exception {
		// String userNum = "select usernum from user_manage where user_id = '" +
		// userName + "'";
		// String managerNum = "select how.ho_num from host_organization_wc how,
		// workconnection_project wp where how.project_num = wp.project_num and
		// wp.project_num = " + projectNum;
		// String sql = "INSERT INTO PROJECT_MATCH_REGIST (pmr_num, userNum,
		// project_num) VALUES (pmr_seq.nextval, ?, ?)";

		String sql = "INSERT INTO PROJECT_MATCH_REGIST (pmr_num, userNum, project_num) "
				+ "VALUES (pmr_seq.nextval, (select usernum from user_manage where user_id = ?), " + projectNum + ")";
		ps = con.prepareStatement(sql);
		ps.setString(1, userName);
		ps.executeUpdate(); // 이걸 해줘야 디비에 반영이 된다.
		con.commit();
		ps.close();
	}
}
