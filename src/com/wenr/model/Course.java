//jz
package com.wenr.model;

public class Course {
	private int cid;		// 课程号
	private String cname;	// 课程名
	private int t_no;		// 教师号
	private int credit;		// 学分
	private int chour; 		// 学时
	private double score;	// 成绩
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	/* jz */
	public int getChour() {
		return chour;
	}
	public void setChour(int chour) {
		this.chour = chour;
	}
	public int getTno() {
		return t_no;
	}
	public void setTno(int tno) {
		this.t_no = tno;
	}
	/* end */
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}
