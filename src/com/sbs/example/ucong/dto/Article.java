package com.sbs.example.ucong.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int memberId;
	private int boardId;
	private int hitCount;
	private int likesCount;
	private int commentsCount;

	private String extra__memberName;
	private String extra__boardName;
	private String extra__boardCode;

	public Article(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
		this.title = (String) map.get("title");
		this.body = (String) map.get("body");
		this.memberId = (int) map.get("memberId");
		this.boardId = (int) map.get("boardId");
		this.likesCount = (int) map.get("likesCount");
		this.commentsCount = (int) map.get("commentsCount");
		this.hitCount = (int) map.get("hitCount");

		if (map.containsKey("extra__memberName")) {
			this.extra__memberName = (String) map.get("extra__memberName");
		}
		if (map.containsKey("extra__boardCode")) {
			this.extra__boardCode = (String) map.get("extra__boardCode");
		}
		if (map.containsKey("extra__boardName")) {
			this.extra__boardName = (String) map.get("extra__boardName");
		}

	}

}
