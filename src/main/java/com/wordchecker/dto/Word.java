package com.wordchecker.dto;

/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
+------------+---------------+------+-----+---------+----------------+
| Field      | Type          | Null | Key | Default | Extra          |
+------------+---------------+------+-----+---------+----------------+
| no         | int(9)        | NO   | PRI | NULL    | auto_increment |
| speling    | varchar(2000) | NO   |     | NULL    |                |
| meaning    | varchar(2000) | NO   |     | NULL    |                |
| member_no  | int(4)        | NO   | MUL | NULL    |                |
| write_date | datetime      | NO   |     | NULL    |                |
| state      | int(1)        | NO   |     | NULL    |                |
+------------+---------------+------+-----+---------+----------------+
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
public class Word {
	private int no;
	private String speling;
	private String meaning;
	private int memberNo;
	private String writeDate;
	private int state;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSpeling() {
		return speling;
	}
	public void setSpeling(String speling) {
		this.speling = speling;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Word [no=" + no + ", speling=" + speling + ", meaning=" + meaning + ", memberNo=" + memberNo
				+ ", writeDate=" + writeDate + ", state=" + state + "]";
	}
}
