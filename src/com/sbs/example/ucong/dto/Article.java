package com.sbs.example.ucong.dto;

import java.util.Map;

public class Article {
	public int id;
	public String regDate;
	public String updateDate;
	public String title;
	public String body;
	public int memberId;
	public int boardId;

	public Article(Map<String, Object> articleMap) {
		this.id=(int)articleMap.get("id");
		this.regDate=(String)articleMap.get("regDate");
		this.updateDate=(String)articleMap.get("updateDate");
		this.title=(String)articleMap.get("title");
		this.body=(String)articleMap.get("body");
		this.memberId=(int)articleMap.get("memberId");
		this.boardId=(int)articleMap.get("boardId");
	}
}
