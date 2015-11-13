package com.xpanxion.wallboard.rest.dao.www;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xpanxion.wallboard.rest.dao.NewsDao;

@Repository
public class GoogleNewsDaoImpl implements NewsDao {

	public static final Logger LOG = LoggerFactory.getLogger(GoogleNewsDaoImpl.class);
	public static final String SPACE = " ";
	public static final String PLUS = "+";
	public static final String QUOTATION = "\"";
	public static final String OR_CONCATENATION = "+OR+";
	
	private final String newsUrlFormat = "http://news.google.com/news?pz=1&cf=all&ned=us&output=rss&hl=en&num=4&q=%s";
	
	@Override
	public List<String> getNews(List<String> keywords) {
		final StringBuilder newsQueryString = new StringBuilder();
		for(int i = 0; i < keywords.size(); i++) {
			final String formattedKeyword = keywords.get(i).replaceAll(SPACE, PLUS);
			newsQueryString.append(QUOTATION).append(formattedKeyword).append(QUOTATION);
			if (i != keywords.size() - 1) {
				newsQueryString.append(OR_CONCATENATION);
			}
		}
		final String newsUrl = String.format(this.newsUrlFormat, newsQueryString);
		return this.fetchNewsFromGoogle(newsUrl);
	}
	
	private List<String> fetchNewsFromGoogle(String url) {
		final List<String> news = new ArrayList<String>();
		final String feedURL = url + "&nocache=" + System.currentTimeMillis();
		LOG.debug("Pulling news data from '{}'.", url);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(feedURL).openStream()))) {
			String line = reader.readLine();
			while (line != null) {
				news.add(line);
				line = reader.readLine();
			}
			LOG.debug("{} in retrieving news data. Data set was {} empty.",
					news.isEmpty() ? "Failed" : "Succeeded",
					news.isEmpty() ? "" : "not");
		} catch (MalformedURLException e) {
			LOG.error("Problem pulling news feed", e);
		} catch (IOException e) {
			LOG.error("Problem pulling news feed", e);
		}
		return news;
	}
}
