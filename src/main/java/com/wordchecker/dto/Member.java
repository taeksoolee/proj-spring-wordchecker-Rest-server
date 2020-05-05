package com.wordchecker.dto;

/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
+------------+---------------+------+-----+---------+----------------+
| Field      | Type          | Null | Key | Default | Extra          |
+------------+---------------+------+-----+---------+----------------+
| no         | int(4)        | NO   | PRI | NULL    | auto_increment |
| email      | varchar(100)  | YES  |     | NULL    |                |
| password   | varchar(1000) | NO   |     | NULL    |                |
| nickname   | varchar(100)  | YES  |     | NULL    |                |
| birthday   | date          | YES  |     | NULL    |                |
| last_login | datetime      | YES  |     | NULL    |                |
| join_date  | datetime      | NO   |     | NULL    |                |
| state      | int(1)        | NO   |     | NULL    |                |
+------------+---------------+------+-----+---------+----------------+
 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
public class Member {
	private int no;
	private String email;
	private String password;
	private String nickname;
	private String birthday;
	private String lastLogin;
	private String joinDate;
	private int state;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Word [no=" + no + ", email=" + email + ", password=" + password + ", nickname=" + nickname
				+ ", birthday=" + birthday + ", lastLogin=" + lastLogin + ", joinDate=" + joinDate + ", state=" + state
				+ "]";
	}
}
