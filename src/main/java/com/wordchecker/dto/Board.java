package com.wordchecker.dto;

/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
+------------+---------------+------+-----+---------+----------------+
| Field      | Type          | Null | Key | Default | Extra          |
+------------+---------------+------+-----+---------+----------------+
| no         | int(9)        | NO   | PRI | NULL    | auto_increment |
| title      | varchar(1000) | NO   |     | NULL    |                |
| content    | varchar(2000) | NO   |     | NULL    |                |
| member_no  | int(4)        | NO   | MUL | NULL    |                |
| wrtie_date | datetime      | NO   |     | NULL    |                |
| state      | int(1)        | NO   |     | NULL    |                |
+------------+---------------+------+-----+---------+----------------+
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
public class Board {
	private int no;
	private String title;
	private String content;
	private int memberNo;
	private String writeDate;
	private int state;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
		return "Board [no=" + no + ", title=" + title + ", content=" + content + ", memberNo=" + memberNo
				+ ", writeDate=" + writeDate + ", state=" + state + "]";
	}
}
