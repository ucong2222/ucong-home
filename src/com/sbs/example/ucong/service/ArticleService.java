package com.sbs.example.ucong.service;

import java.util.List;

import com.sbs.example.ucong.container.Container;
import com.sbs.example.ucong.dao.ArticleDao;
import com.sbs.example.ucong.dto.Article;
import com.sbs.example.ucong.dto.ArticleReply;
import com.sbs.example.ucong.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public int delete(int id) {
		return articleDao.delete(id);
	}

	public int modify(int id, String title, String body) {
		return articleDao.modify(id, title, body);
	}

	public int write(int memberId, int boardId, String title, String body) {
		return articleDao.add(memberId,boardId,title, body);
	}

	public int makeBoard(String name) {
		return articleDao.makeBoard(name);
	}



	public int WrtieReply(int articleId, int memberId,String body) {
		return articleDao.addReply(articleId,memberId, body);
	}

	public List<ArticleReply> getArticleReplysByArticleId(int articleId) {
		return articleDao.getArticleReplysByArticleId(articleId);
	}

	public ArticleReply getArticleReply(int id) {
		return articleDao.getArticleReply(id);
	}

	public int replyModify(int id, String body) {
		return articleDao.replyModify(id, body);
		
	}

	public int replyDelete(int id) {
		return articleDao.replyDelete(id);
		
	}
	public List<Article> getForPrintArticles(int boardId) {
		return articleDao.getForPrintArticles(boardId);
	}

	public int hitCount(int inputedId) {
		return articleDao.hitCount(inputedId);
		
	}

	public boolean recommand(int articleId, int memberId) {
		int affectedLows = articleDao.recommand(articleId,memberId);
		if(affectedLows==1) {
			return true;
		}
		return false;
	}

	public boolean cancleRecommand(int articleId, int memberId) {
		int affectedRows =articleDao.cancleRecommand(articleId,memberId);
		if(affectedRows == 1) {
			return true;
		}
		return false;
	}

	public int getRecommandCount(int inputedId) {
		return articleDao.getRecommandCount(inputedId);
	}

	public Board getBoardByBoardCode(String boardCode) {
		return articleDao.getBoardByBoardCode(boardCode);
	}



}
