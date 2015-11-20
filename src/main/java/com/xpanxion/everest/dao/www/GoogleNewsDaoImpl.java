package com.xpanxion.everest.dao.www;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpanxion.everest.dao.NewsDao;

@Repository
public class GoogleNewsDaoImpl implements NewsDao {

	public static final Logger LOG = LoggerFactory.getLogger(GoogleNewsDaoImpl.class);
	public static final String SPACE = " ";
	public static final String PLUS = "+";
	public static final String QUOTATION = "\"";
	public static final String OR_CONCATENATION = "+OR+";
	
	public static final String GOOGLE_NEWS_API_FMT = "";
	public static final String GOOGLE_FEED_API_NEWS_FMT = "https://ajax.googleapis.com/ajax/services/feed/find?v=1.0&q=\"news\"+AND+%s";
	
	private final String newsUrlFormat = GOOGLE_FEED_API_NEWS_FMT;
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	static {
		mapper.configure(MapperFeature.USE_ANNOTATIONS, true);
	}
	
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
		final List<String> news = new ArrayList<>();
		
		final StringBuilder builder = new StringBuilder();
		String line;
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream()))) {
			while((line = reader.readLine()) != null) {
				builder.append(line);
			} 
		} catch (MalformedURLException e) {
			LOG.error("Problem pulling news feed", e);
		} catch (IOException e) {
			LOG.error("Problem pulling news feed", e);
		}
		
		try {
			final JSONObject jsonResponse = new JSONObject(builder.toString());
			if (HttpServletResponse.SC_OK == jsonResponse.getInt("responseStatus")) {
				final JSONArray newsEntriesJson = jsonResponse.getJSONObject("responseData").getJSONArray("entries");
				for(int i = 0; i < newsEntriesJson.length(); i++) {
					news.add(newsEntriesJson.getJSONObject(i).toString());
				}
			}
		} catch (Exception e) {
			LOG.error("Error extracting news content from json content {}", builder, e);
		}
		
		return news;
	}
	
	
}
