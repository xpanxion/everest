/**
 * 
 */
package com.xpanxion.wallboard.rest.dto.news;

import java.util.ArrayList;
import java.util.List;

import com.xpanxion.wallboard.rest.dto.news.GoogleNewsConstants.Edition;
import com.xpanxion.wallboard.rest.dto.news.GoogleNewsConstants.Language;
import com.xpanxion.wallboard.rest.dto.news.GoogleNewsConstants.OutputType;
import com.xpanxion.wallboard.rest.dto.news.GoogleNewsConstants.PzType;
import com.xpanxion.wallboard.rest.dto.news.GoogleNewsConstants.RetrieveType;

/**
 * <p> Simple Google News Client Request Builder to clean up code and allow future flexibility if needed.
 * 
 *  Using 'http://blog.slashpoundbang.com/post/12975232033/google-news-search-parameters-the-missing-manual'
 *  as a reference, as well as what was previously in place in the Wallboard application prior to the
 *  introduction of this class.  Additional features may always be added, if necessary.
 *
 * @author tony
 *
 */
public class GoogleNewsRequest {

	public static final String DEFAULT_NEWS_DOMAIN = "news.google.com";
	public static final String DEFAULT_NEWS_PAGE_LOCATION = "/news";
	public static final int DEFAULT_NUM_ARTICLES = 4;
	private static final String HTTP = "http://";
	private static final String HTTPS = "https://";
	private static final String EQUALS = "=";
	private static final String PLUS = "+";
	private static final String OR = "OR";
	private static final String SPACE = " ";
	private static final String QUESTION_MARK = "?";
	private static final String AMPERSAND = "&";
	private static final String QUOTE = "%22";
	
	private String endpoint =  DEFAULT_NEWS_DOMAIN + DEFAULT_NEWS_PAGE_LOCATION;
	
	private PzType pzType = GoogleNewsConstants.PzType.ONE;
	private RetrieveType retrieveType = GoogleNewsConstants.RetrieveType.ALL;
	private Edition edition = GoogleNewsConstants.Edition.US;
	private OutputType output = GoogleNewsConstants.OutputType.RSS;
	private Language language = GoogleNewsConstants.Language.EN;
	private int numArticles = DEFAULT_NUM_ARTICLES;
	private List<String> searchQueries = new ArrayList<>();
	private boolean secure = false;
	
	/**
	 * @return Returns the URL request to retrieve news content.
	 */
	public String getRequest() {
		final StringBuilder requestBuilder = new StringBuilder();
		requestBuilder.append(this.secure ? HTTPS : HTTP);
		requestBuilder.append(this.endpoint).append(QUESTION_MARK);
		requestBuilder.append(pzType.getKey()).append(EQUALS).append(pzType.getValue()).append(AMPERSAND);
		requestBuilder.append(retrieveType.getKey()).append(EQUALS).append(retrieveType.getValue()).append(AMPERSAND);
		requestBuilder.append(edition.getKey()).append(EQUALS).append(edition.getValue()).append(AMPERSAND);
		requestBuilder.append(output.getKey()).append(EQUALS).append(output.getValue()).append(AMPERSAND);
		requestBuilder.append(language.getKey()).append(EQUALS).append(language.getValue()).append(AMPERSAND);
		requestBuilder.append(GoogleNewsConstants.NUM_ARTICLES_KEY).append(EQUALS).append(numArticles).append(AMPERSAND);
		requestBuilder.append(GoogleNewsConstants.QUERY_KEY).append(EQUALS).append(this.getFormattedQuery());
		return requestBuilder.toString();
	}
	
	/**
	 * @return A formatted query string from <tt>searchQueries</tt>, in accordance to Google's query parameter ruleset.
	 */
	private String getFormattedQuery() {
		final StringBuilder formattedQuery = new StringBuilder();
		for(int queryIdx = 0; queryIdx < this.searchQueries.size(); queryIdx++) {
			formattedQuery.append(this.formatQuery(this.searchQueries.get(queryIdx)));
			if (queryIdx != this.searchQueries.size() - 1) {
				formattedQuery.append(PLUS).append(OR).append(PLUS);
			}
		}
		return formattedQuery.toString();
	}
	
	/**
	 * @return An individual formatted query string from <tt>searchQueries</tt>, in accordance to Google's query parameter ruleset.
	 */
	private String formatQuery(String query) {
		final StringBuilder queryBuilder = new StringBuilder();
		final String[] words = query.split(SPACE);
		queryBuilder.append(QUOTE);
		for(int i = 0; i < words.length; i++) {
			queryBuilder.append(words[i]);
			if (i != words.length - 1) {
				queryBuilder.append(PLUS);
			}
		}
		queryBuilder.append(QUOTE);
		return queryBuilder.toString();
	}
	
	/**
	 * <p> Helper method to add a search query to the list of items to query for. 
	 *
	 * @param query The <tt>String</tt> query to search for.
	 * @return This <tt>GoogleNewsRequest</tt> instance.
	 */
	public GoogleNewsRequest addSearchQuery(String query) {
		this.searchQueries.add(query);
		return this;
	}
	
	public List<String> getSearchQueries() {
		return searchQueries;
	}

	public void setSearchQueries(List<String> searchQueries) {
		this.searchQueries = searchQueries;
	}

	public String getEndpoint() {
		return endpoint;
	}
	
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public PzType getPzType() {
		return pzType;
	}
	
	public void setPzType(PzType pzType) {
		this.pzType = pzType;
	}
	
	public RetrieveType getRetrieveType() {
		return retrieveType;
	}
	
	public void setRetrieveType(RetrieveType retrieveType) {
		this.retrieveType = retrieveType;
	}
	
	public Edition getEdition() {
		return edition;
	}
	
	public void setEdition(Edition edition) {
		this.edition = edition;
	}
	
	public OutputType getOutput() {
		return output;
	}
	
	public void setOutput(OutputType output) {
		this.output = output;
	}
	
	public Language getLanguage() {
		return language;
	}
	
	public void setLanguage(Language language) {
		this.language = language;
	}
	
	public int getNumArticles() {
		return numArticles;
	}
	
	public void setNumArticles(int numArticles) {
		this.numArticles = numArticles;
	}
	
	public boolean isSecure() {
		return secure;
	}
	
	public void setSecure(boolean secure) {
		this.secure = secure;
	}
}