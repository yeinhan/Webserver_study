package com.login.dto;

//멤버번호, 아이디, 비밀번호, 이름, 주소, 전화번호, 
//이메일, 가입여부(가입/탈퇴), 등급(ADMIN,USER,..)
public class MyMemberDto {
	private int myno;
	private String myid;
	private String mypw;
	private String myname;
	private String myaddr;
	private String myphone;
	private String myemail;
	private String enabled;
	private String myrole;
	
	public MyMemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyMemberDto(int myno, String myid, String mypw, String myname, String myaddr, String myphone, String myemail,
			String enabled, String myrole) {
		super();
		this.myno = myno;
		this.myid = myid;
		this.mypw = mypw;
		this.myname = myname;
		this.myaddr = myaddr;
		this.myphone = myphone;
		this.myemail = myemail;
		this.enabled = enabled;
		this.myrole = myrole;
	}

	public int getMyno() {
		return myno;
	}

	public void setMyno(int myno) {
		this.myno = myno;
	}

	public String getMyid() {
		return myid;
	}

	public void setMyid(String myid) {
		this.myid = myid;
	}

	public String getMypw() {
		return mypw;
	}

	public void setMypw(String mypw) {
		this.mypw = mypw;
	}

	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}

	public String getMyaddr() {
		return myaddr;
	}

	public void setMyaddr(String myaddr) {
		this.myaddr = myaddr;
	}

	public String getMyphone() {
		return myphone;
	}

	public void setMyphone(String myphone) {
		this.myphone = myphone;
	}

	public String getMyemail() {
		return myemail;
	}

	public void setMyemail(String myemail) {
		this.myemail = myemail;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getMyrole() {
		return myrole;
	}

	public void setMyrole(String myrole) {
		this.myrole = myrole;
	}
	
	
	
	
}
