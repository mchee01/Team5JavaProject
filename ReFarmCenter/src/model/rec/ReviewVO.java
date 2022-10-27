package model.rec;

public class ReviewVO {
	
	int reviewnum, usernum, projectnum ;
	String reviewcontent, reviewtitle, grade;
	
	public ReviewVO() {
	}
	
	public ReviewVO(String reviewcontent, String reviewtitle, String grade) {
		this.grade = grade;
		this.reviewcontent = reviewcontent;
		this.reviewtitle = reviewtitle;
	}
	
	public ReviewVO(String reviewcontent, String reviewtitle, String grade, String userName, String prjName) {
		this.grade = grade;
		this.reviewcontent = reviewcontent;
		this.reviewtitle = reviewtitle;
	}
	

	public int getReviewnum() {
		return reviewnum;
	}


	public void setReviewnum(int reviewnum) {
		this.reviewnum = reviewnum;
	}


	public int getUsernum() {
		return usernum;
	}


	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}


	public int getProjectnum() {
		return projectnum;
	}


	public void setProjectnum(int projectnum) {
		this.projectnum = projectnum;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getReviewcontent() {
		return reviewcontent;
	}


	public void setReviewcontent(String reviewcontent) {
		this.reviewcontent = reviewcontent;
	}


	public String getReviewtitle() {
		return reviewtitle;
	}


	public void setReviewtitle(String reviewtitle) {
		this.reviewtitle = reviewtitle;
	}


	
	
	

}
