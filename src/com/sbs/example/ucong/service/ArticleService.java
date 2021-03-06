package com.sbs.example.ucong.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.ucong.container.Container;
import com.sbs.example.ucong.dao.ArticleDao;
import com.sbs.example.ucong.dto.Article;
import com.sbs.example.ucong.dto.ArticleReply;
import com.sbs.example.ucong.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;
	private TagService tagService;

	public ArticleService() {
		articleDao = Container.articleDao;
		tagService = Container.tagService;
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
		Map<String, Object> modifyArgs = new HashMap<>();
		modifyArgs.put("id", id);
		modifyArgs.put("title", title);
		modifyArgs.put("body", body);

		return modify(modifyArgs);
	}

	public int write(int memberId, int boardId, String title, String body) {
		return articleDao.add(memberId, boardId, title, body);
	}

	public int makeBoard(String code, String name) {
		return articleDao.makeBoard(code, name);
	}

	public int WrtieReply(int articleId, int memberId, String body) {
		return articleDao.addReply(articleId, memberId, body);
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
		int affectedLows = articleDao.recommand(articleId, memberId);
		if (affectedLows == 1) {
			return true;
		}
		return false;
	}

	public boolean cancleRecommand(int articleId, int memberId) {
		int affectedRows = articleDao.cancleRecommand(articleId, memberId);
		if (affectedRows == 1) {
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

	public boolean isMakeBoardAbvailalbeName(String name) {
		Board board = articleDao.getBoardByName(name);
		return board == null;
	}

	public boolean isMakeBoardAbvailalbeCode(String code) {
		Board board = articleDao.getBoardByBoardCode(code);
		return board == null;
	}

	public List<Board> getForPrintBoards() {
		return articleDao.getForPrintBoards();
	}

	public int getArticlesCountByBoardId(int boardId) {

		return articleDao.getArticlesCountByBoardId(boardId);
	}

	public List<Board> getBoards() {
		return articleDao.getForPrintBoards();
	}

	public List<Article> getArticlesByBoardId(int boardId) {
		return articleDao.getArticlesByBoardId(boardId);
	}

	public String getBoardCodeById(int boardId) {
		return articleDao.getBoardCodeById(boardId);
	}

	public int getArticlesCount() {
		return articleDao.getArticlesCount();
	}

	public int getArticlesHitCount() {
		return articleDao.getArticlesHitCount();
	}

	public int getBoardArticlesHitCountByBoardId(int boardId) {
		List<Article> articles = articleDao.getArticlesByBoardId(boardId);
		if (articles.size() == 0) {
			return 0;
		}
		return articleDao.getBoardArticlesHitCountByBoardId(boardId);
	}

	public List<Article> getForPrintArticles() {
		return articleDao.getForPrintArticles(0);
	}

	public int modify(Map<String, Object> args) {
		return articleDao.modify(args);
	}

	public void updatePageHits() {
		articleDao.updatePageHits();

	}

	public Map<String, List<Article>> getArticlesByTagMap() {
		Map<String, List<Article>> map = new LinkedHashMap<>();
		List<String> tagBodies = tagService.getDedupTagBodiesByRelTypeCode("article");

		for (String tagBody : tagBodies) {
			List<Article> articles = getForPrintArticlesByTag(tagBody);

			map.put(tagBody, articles);
		}
		return map;
	}

	private List<Article> getForPrintArticlesByTag(String tagBody) {
		return articleDao.getForPrintArticlesByTag(tagBody);
	}

	public List<Article> getForPrintRatestArticles() {
		return articleDao.getForPrintRatestArticles();
	}

	public List<Article> getForPrintbestArticles() {
		return articleDao.getForPrintbestArticles();
	}

}
