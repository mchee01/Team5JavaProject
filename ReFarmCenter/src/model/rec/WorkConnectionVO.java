package model.rec;

/**
 * 파일 명 : WorkConnectionVO
 * 
 * @author : 주민지
 * @date : 2022.09.05
 * @description : WorkConnectionVO 파일
 */

public class WorkConnectionVO {
	String ws_Title, ws_StartDate, ws_EndDate, ws_Content, ws_Qualify, ws_Location, ws_FarmName;
	int project_num, ws_Participants, ws_FarmNum;

	public WorkConnectionVO() {
		
	}
	
	public WorkConnectionVO(String ws_Title, String ws_StartDate, String ws_EndDate, String ws_Content, String ws_Qualify, int ws_Participants, int ws_FarmNum) {
		this.ws_Title = ws_Title;
		this.ws_StartDate = ws_StartDate;
		this.ws_EndDate = ws_EndDate;
		this.ws_Content = ws_Content;
		this.ws_Qualify = ws_Qualify;
		this.ws_Participants = ws_Participants;
		this.ws_FarmNum = ws_FarmNum;
	}
	
	public int getProject_num() {
		return project_num;
	}

	public void setProject_num(int project_num) {
		this.project_num = project_num;
	}

	public String getWs_Title() {
		return ws_Title;
	}

	public void setWs_Title(String ws_Title) {
		this.ws_Title = ws_Title;
	}

	public String getWs_StartDate() {
		return ws_StartDate;
	}

	public void setWs_StartDate(String ws_StartDate) {
		this.ws_StartDate = ws_StartDate;
	}

	public String getWs_EndDate() {
		return ws_EndDate;
	}

	public void setWs_EndDate(String ws_EndDate) {
		this.ws_EndDate = ws_EndDate;
	}

	public String getWs_Content() {
		return ws_Content;
	}

	public void setWs_Content(String ws_Content) {
		this.ws_Content = ws_Content;
	}

	public String getWs_Qualify() {
		return ws_Qualify;
	}

	public void setWs_Qualify(String ws_Qualify) {
		this.ws_Qualify = ws_Qualify;
	}

	public String getWs_Location() {
		return ws_Location;
	}

	public void setWs_Location(String ws_Location) {
		this.ws_Location = ws_Location;
	}

	public int getWs_Participants() {
		return ws_Participants;
	}

	public void setWs_Participants(int ws_Participants) {
		this.ws_Participants = ws_Participants;
	}

	public int getWs_FarmNum() {
		return ws_FarmNum;
	}

	public void setWs_FarmNum(int ws_FarmNum) {
		this.ws_FarmNum = ws_FarmNum;
	}

	public String getWs_FarmName() {
		return ws_FarmName;
	}

	public void setWs_FarmName(String ws_FarmName) {
		this.ws_FarmName = ws_FarmName;
	}
}
