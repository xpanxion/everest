package com.xpanxion.everest.dao;

import java.util.List;

import com.xpanxion.everest.dto.news.NewsContent;

public interface NewsDao {
	
	/**
	 * 
	 * @param keywords
	 * @return
	 */
	List<NewsContent> getNews(List<String> keywords);
}
