package com.xpanxion.everest.dto.news;

public class GoogleNewsEntry implements NewsContent {

	private String url;
	private String title;
	private String contentSnippet;
	private String link;
	
	/**
	 * @return the url
	 */
	@Override
	public String getUrl() {
		return url;
	}
	
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return the title
	 */
	@Override
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the contentSnippet
	 */
	public String getContentSnippet() {
		return contentSnippet;
	}
	
	/**
	 * @param contentSnippet the contentSnippet to set
	 */
	public void setContentSnippet(String contentSnippet) {
		this.contentSnippet = contentSnippet;
	}
	
	/**
	 * @return the link
	 */
	@Override
	public String getLink() {
		return link;
	}
	
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String getShortDescription() {
		return this.contentSnippet;
	}
	
}
