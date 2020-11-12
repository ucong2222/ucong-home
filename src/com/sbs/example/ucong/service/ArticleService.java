package com.sbs.example.ucong.service;

import java.util.List;

import com.sbs.example.ucong.container.Container;
import com.sbs.example.ucong.dao.ArticleDao;
import com.sbs.example.ucong.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

}
