package model.rec;

public class LoginVO {
	private String id, pwd, name, jumin, phoneNum, gi, gibun;
    private int position;
	public LoginVO(){
		
	}
	public LoginVO(String id,String pwd, String name, String jumin, String phoneNum, int position) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.jumin = jumin;
		this.phoneNum = phoneNum;
		this.position = position;
	}
	public LoginVO(String id, String pwd, String gi, String gibun) {
		this.id = id;
		this.pwd = pwd;
		this.gi = gi;
		this.gibun =gibun;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getGi() {
		return gi;
	}
	public void setGi(String gi) {
		this.gi = gi;
	}
	public String getGibun() {
		return gibun;
	}
	public void setGibun(String gibun) {
		this.gibun = gibun;
	}

}
