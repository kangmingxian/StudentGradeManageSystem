//jz
package com.wenr.model;

public class Course {
	private int cid;		// �γ̺�
	private String cname;	// �γ���
	private int t_no;		// ��ʦ��
	private int credit;		// ѧ��
	private int chour; 		// ѧʱ
	private double score;	// �ɼ�
	
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
