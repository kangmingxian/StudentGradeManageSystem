package com.wenr.model;

public class Teacher {
	private int t_no;//��ʦ���
	private String t_pwd;	// ����
	private String t_name;	// ����
	
	public int getTid() {
		return t_no;
	}
	public void setTid(int sid) {
		this.t_no = sid;
	}
	public String getTpwd() {
		return t_pwd;
	}
	public void setTpwd(String tpwd) {
		this.t_pwd = tpwd;
	}
	public String getTname() {
		return t_name;
	}
	public void setTname(String tname) {
		this.t_name = tname;
	}

}
