package com.wordchecker.dto;

public class BoardMember {
	private Board board;
	private Member member;
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	@Override
	public String toString() {
		return "BoardMember [board=" + board + ", member=" + member + "]";
	}
}
