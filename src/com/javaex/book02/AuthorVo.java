package com.javaex.book02;

public class AuthorVo {
	//필드
	int author_id ;
	String author_name;
	String author_desc;
	//생성자
	public AuthorVo() { }
	public AuthorVo(int author_id) {
		this.author_id = author_id;
	}
	public AuthorVo(String author_name, String author_desc) {
		this.author_name = author_name;
		this.author_desc = author_desc;
	}
	public AuthorVo(int author_id, String author_name, String author_desc) {
		this.author_id = author_id;
		this.author_name = author_name;
		this.author_desc = author_desc;
	}
	//메소드 g/s
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getAuthor_desc() {
		return author_desc;
	}
	public void setAuthor_desc(String author_desc) {
		this.author_desc = author_desc;
	}
	//메소드 일반
	@Override
	public String toString() {
		return "auhtorVo [author_id=" + author_id + ", author_name=" + author_name + ", author_desc=" + author_desc
				+ "]";
	}
	
	
	
	
}
