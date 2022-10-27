package model.rec;

public class ReservationVO {
	private int reNum;
	private String id;
	private int rea_num;
	private String redate;
	private String contents;
	
	public ReservationVO(int reNum,String id, int rea_num, String redate, String contents) {
		this.reNum = reNum;
		this.id=id;
		this.rea_num = rea_num;
		this.redate = redate;
		this.contents=contents;
	}
	public int getReNum() {
		return reNum;
	}
	public void setReNum(int reNum) {
		this.reNum = reNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRea_num() {
		return rea_num;
	}
	public void setRea_num(int rea_num) {
		this.rea_num = rea_num;
	}
	public String getRedate() {
		return redate;
	}
	public void setRedate(String redate) {
		this.redate = redate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
	
	
}
