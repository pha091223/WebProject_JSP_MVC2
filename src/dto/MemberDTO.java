package dto;

public class MemberDTO {
	private String name = null;
	private String resident = null;
	private String id = null;
	private String pwd = null;
	private String phone = null;
	private String email = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResident() {
		return resident;
	}
	public void setResident(String resident) {
		this.resident = resident;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
//	create table web_member(
//			name varchar2(5) not null,
//			resident varchar2(13) not null unique,
//			id varchar2(15) primary key,
//			pwd varchar2(15) not null,
//			phone varchar2(11) not null,
//			email varchar2(30));

}
