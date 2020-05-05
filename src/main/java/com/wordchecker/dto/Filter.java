package com.wordchecker.dto;

public class Filter {
	private int start;
	private int length;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	@Override
	public String toString() {
		return "Filter [start=" + start + ", length=" + length + "]";
	}
}
